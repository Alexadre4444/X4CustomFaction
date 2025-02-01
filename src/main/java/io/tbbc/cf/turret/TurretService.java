package io.tbbc.cf.turret;

import io.tbbc.cf.bullet.Bullet;
import io.tbbc.cf.bullet.IBulletService;
import io.tbbc.cf.bullet.skin.BulletSkin;
import io.tbbc.cf.common.*;
import io.tbbc.cf.common.customizer.Customizer;
import io.tbbc.cf.common.modifier.Modifiers;
import io.tbbc.cf.common.property.FinalPropValue;
import io.tbbc.cf.common.property.FinalProperties;
import io.tbbc.cf.common.property.PropertyDefinition;
import io.tbbc.cf.common.property.PropsHelper;
import io.tbbc.cf.mod.IModInfosService;
import io.tbbc.cf.turret.chassis.ChassisType;
import io.tbbc.cf.turret.chassis.ITurretChassisService;
import io.tbbc.cf.turret.chassis.TurretChassis;
import io.tbbc.cf.turret.chassis.skin.ChassisEgoSkinProps;
import io.tbbc.cf.turret.chassis.skin.ChassisSkin;
import io.tbbc.cf.turret.custom.ITurretCustomizerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class TurretService implements ITurretService {
    public static final int EGO_TURRET_LABEL_SECTION = 142000;
    public static final int EGO_TURRET_DESCRIPTION_SECTION = 142001;

    @Inject
    ITurretRepository turretRepository;
    @Inject
    ITurretChassisService turretChassisService;
    @Inject
    IBulletService bulletService;
    @Inject
    TurretValidator turretValidator;
    @Inject
    ITurretCustomizerService turretCustomizerService;
    @Inject
    IModInfosService modInfosService;

    @Override
    public List<Turret> getAll() {
        return turretRepository.getAll();
    }

    @Override
    @Transactional
    public void insert(TurretCreate turret) {
        turret.setLabel(StringUtil.normalizeLabel(turret.getLabel()));
        turretValidator.validateCreateEmpty(turret);
        Turret emptyTurret = new Turret();
        emptyTurret.setLabel(turret.getLabel());
        emptyTurret.setDescription("No description.");
        emptyTurret.setSize(turret.getSize());
        emptyTurret.setState(State.DRAFT);
        turretRepository.insert(emptyTurret);
    }

    @Override
    @Transactional
    public void update(Turret turret) {
        turret.setLabel(StringUtil.normalizeLabel(turret.getLabel()));
        turret.setDescription(StringUtil.normalizeDescription(turret.getDescription()));

        turretValidator.validateUpdate(turret, this::getById, turretChassisService::getByName,
                bulletService::getByName, turretCustomizerService::getByName);
        turret.setState(State.VALID);
        turretRepository.update(turret);
    }

    @Override
    public Optional<Turret> getById(long id) {
        return turretRepository.getById(id);
    }

    @Override
    public List<TurretEgoProps> getTurretEgoProps() {
        List<TurretEgoProps> result = new ArrayList<>();
        List<Turret> validTurrets = getAll().stream()
                .filter(turret -> turret.getState() != State.DRAFT)
                .toList();
        String factionTrigram = modInfosService.getActualModInfos().getFactionTrigram();

        for (int i = 0; i < validTurrets.size(); i++) {
            Turret turret = validTurrets.get(i);
            TurretChassis chassis = turretChassisService.getByName(turret.getChassisName()).orElseThrow();
            Bullet bullet = bulletService.getByName(turret.getBulletName()).orElseThrow();
            result.add(getTurretEgoPropsMain(turret, chassis, i, bullet, factionTrigram));
            if (turret.getSize().equals(Size.MEDIUM)) {
                result.add(getTurretEgoPropsAlias(turret, chassis, i, bullet, factionTrigram));
            }
        }

        return result;
    }

    private TurretEgoProps getTurretEgoPropsMain(Turret turret, TurretChassis chassis, int i, Bullet bullet,
                                                 String factionTrigram) {
        return new TurretEgoProps(getEgoTurretName(turret, chassis),
                EGO_TURRET_LABEL_SECTION, EGO_TURRET_DESCRIPTION_SECTION, i,
                chassis.size().egoInitial(), getEgoTurretMacroName(turret, chassis), getEgoTurretAliasMacroName(turret, chassis),
                getTurretFullLabel(turret, chassis), turret.getDescription(),
                getChassisEgoSkinProps(turret, chassis).egoComponentName(),
                getChassisEgoSkinProps(turret, chassis).egoComponentNameAlias(),
                getEgoBulletMacroName(turret, bullet), computeAllFinalValues(chassis, bullet, turretChassisService.getProperties(),
                getCustomizers(turret.getCustomizers())),
                getBulletSkin(turret, bullet).skinProps(),
                chassis.type(), TurretEgoType.MAIN, getTurretNameEntry(turret, chassis, i, factionTrigram),
                getTurretBaseNameEntry(turret, i), getTurretShortNameEntry(turret, i));
    }

    private TurretEgoProps getTurretEgoPropsAlias(Turret turret, TurretChassis chassis, int i, Bullet bullet,
                                                  String factionTrigram) {
        return new TurretEgoProps(getEgoTurretAliasName(turret, chassis),
                EGO_TURRET_LABEL_SECTION, EGO_TURRET_DESCRIPTION_SECTION, i,
                chassis.size().egoInitial(), getEgoTurretAliasMacroName(turret, chassis), null,
                getTurretFullLabel(turret, chassis), turret.getDescription(),
                getChassisEgoSkinProps(turret, chassis).egoComponentNameAlias(), null,
                getEgoBulletMacroName(turret, bullet), computeAllFinalValues(chassis, bullet, turretChassisService.getProperties(),
                getCustomizers(turret.getCustomizers())),
                getBulletSkin(turret, bullet).skinProps(),
                chassis.type(), TurretEgoType.ALIAS, getTurretNameEntry(turret, chassis, i, factionTrigram),
                getTurretBaseNameEntry(turret, i), getTurretShortNameEntry(turret, i));
    }

    private FinalProperties computeAllFinalValues(TurretChassis chassis, Bullet bullet,
                                                  List<PropertyDefinition> propertyDefinitions,
                                                  List<Customizer> customizers) {
        Modifiers bulletModifiers = bullet != null ? bullet.modifiers() : new Modifiers(List.of());
        FinalProperties baseProps = PropsHelper.computeFinalProps(chassis.props().getProperties(),
                propertyDefinitions,
                Stream.concat(Stream.of(bulletModifiers),
                        customizers.stream().map(Customizer::modifiers)).toList());
        return computeFinalValuesWithComputed(chassis, baseProps);
    }

    private FinalProperties computeFinalValuesWithComputed(TurretChassis chassis, FinalProperties baseProperties) {
        List<FinalPropValue> computedProps = new ArrayList<>();
        FinalPropValue shootPerSecond;
        if (chassis.type() != ChassisType.BEAM) {
            FinalPropValue range = ComputationHelper.computeRange(baseProperties);
            computedProps.add(range);
            FinalPropValue burstTime = ComputationHelper.computeBurstTime(chassis, baseProperties);
            computedProps.add(burstTime);
            shootPerSecond = ComputationHelper.computeShootPerSecond(chassis, baseProperties, burstTime);
        } else {
            shootPerSecond = ComputationHelper.computeShootPerSecondForBeam(baseProperties);
        }
        FinalPropValue damageHullPerSecond = ComputationHelper.computeDamageHullPerSecond(chassis, baseProperties, shootPerSecond);
        FinalPropValue damageShieldPerSecond = ComputationHelper.computeDamageShieldPerSecond(chassis, baseProperties, shootPerSecond);
        FinalPropValue rotationAcceleration = ComputationHelper.computeAcceleration(baseProperties);
        FinalPropValue damageBonusShield = ComputationHelper.computeDamageBonusShield(baseProperties);
        computedProps.add(shootPerSecond);
        computedProps.add(damageHullPerSecond);
        computedProps.add(damageShieldPerSecond);
        computedProps.add(rotationAcceleration);
        computedProps.add(damageBonusShield);
        return new FinalProperties(
                Stream.concat(
                                baseProperties.properties().stream(),
                                computedProps.stream())
                        .toList());
    }

    @Override
    public FinalProperties computeFinalValues(String chassisName, String bulletName, Map<String, String> customizers) {
        TurretChassis chassis = turretChassisService.getByName(chassisName)
                .orElseThrow(() -> new BadArgumentException("Chassis not found"));
        Bullet bullet = bulletService.getByName(bulletName).orElse(null);
        List<Customizer> customizersList = getCustomizers(customizers);
        return computeAllFinalValues(chassis, bullet, turretChassisService.getProperties(), customizersList);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Turret turret = getById(id).orElseThrow(() -> new NotFoundException("Turret with id %s not found".formatted(id)));
        turretValidator.validateDelete(turret);
        turretRepository.delete(id);
    }

    @Override
    @Transactional
    public void deployTurrets() {
        getAll().stream()
                .filter(turret -> turret.getState() == State.VALID)
                .forEach(this::deployTurret);
    }

    private void deployTurret(Turret turret) {
        turret.setState(State.DEPLOYED);
        turretRepository.update(turret);
    }

    private List<Customizer> getCustomizers(List<CustomizerValue> customizers) {
        Map<String, String> customizersMap = customizers.stream()
                .collect(Collectors.toMap(CustomizerValue::getCategoryName, CustomizerValue::getCustomizerName));
        return getCustomizers(customizersMap);
    }

    private List<Customizer> getCustomizers(Map<String, String> customizers) {
        return turretCustomizerService.getAll().stream()
                .filter(customizerCategory -> customizers.containsKey(customizerCategory.name()))
                .map(customizerCategory -> customizerCategory.customizers().stream()
                        .filter(customizer -> customizer.name().equals(customizers.get(customizerCategory.name())))
                        .findFirst()
                        .orElseThrow(() -> new BadArgumentException("Customizer %s not found"
                                .formatted(customizers.get(customizerCategory.name())))))
                .toList();
    }

    private LangEntry getTurretNameEntry(Turret turret, TurretChassis chassis, int index, String factionTrigram) {
        int entryIndex = index * 3;
        String mkVersion = "Mk1";
        String name = "(%s %s %s Turret %s)%s (%s)%s %s Turret %s".formatted(
                factionTrigram, chassis.size().egoInitial().toUpperCase(), turret.getLabel(), mkVersion, factionTrigram,
                chassis.size().egoFullString(), chassis.size().egoInitial().toUpperCase(), turret.getLabel(), mkVersion);
        return new LangEntry(entryIndex, name);
    }

    private LangEntry getTurretBaseNameEntry(Turret turret, int index) {
        int entryIndex = index * 3 + 1;
        String name = "%s Turret".formatted(turret.getLabel());
        return new LangEntry(entryIndex, name);
    }

    private LangEntry getTurretShortNameEntry(Turret turret, int index) {
        int entryIndex = index * 3 + 2;
        String mkVersion = "Mk1";
        String name = "(%s Turret)%s Turret %s".formatted(turret.getLabel(), turret.getLabel(), mkVersion);
        return new LangEntry(entryIndex, name);
    }

    private String getTurretFullLabel(Turret turret, TurretChassis chassis) {
        return "%s".formatted(turret.getLabel());
    }

    private String getEgoBulletName(Turret turret, Bullet bullet) {
        return "bullet_cf_%s_%s".formatted(bullet.size().egoInitial(), turret.getName());
    }

    private String getEgoBulletMacroName(Turret turret, Bullet bullet) {
        return "%s_macro".formatted(getEgoBulletName(turret, bullet));
    }

    private String getEgoTurretName(Turret turret, TurretChassis chassis) {
        return "turret_cf_%s_%s_1".formatted(chassis.size().egoInitial(), turret.getName());
    }

    private String getEgoTurretAliasName(Turret turret, TurretChassis chassis) {
        return "turret_cf_%s_%s_2".formatted(chassis.size().egoInitial(), turret.getName());
    }

    private String getEgoTurretMacroName(Turret turret, TurretChassis chassis) {
        return "%s_macro".formatted(getEgoTurretName(turret, chassis));
    }

    private String getEgoTurretAliasMacroName(Turret turret, TurretChassis chassis) {
        String result = null;
        if (turret.getSize().equals(Size.MEDIUM)) {
            result = "%s_macro".formatted(getEgoTurretAliasName(turret, chassis));
        }
        return result;
    }

    private ChassisEgoSkinProps getChassisEgoSkinProps(Turret turret, TurretChassis chassis) {
        return chassis.availableSkins().stream()
                .filter(skin -> skin.name().equals(turret.getChassisSkinName()))
                .map(ChassisSkin::egoSkinProps)
                .findFirst()
                .orElseThrow(() -> new InternalException("Skin %s not found".formatted(turret.getChassisSkinName())));
    }

    private BulletSkin getBulletSkin(Turret turret, Bullet bullet) {
        return bullet.availableSkins().stream()
                .filter(skin -> skin.name().equals(turret.getBulletSkinName()))
                .findFirst()
                .orElseThrow(() -> new InternalException("Skin %s not found".formatted(turret.getBulletSkinName())));
    }
}
