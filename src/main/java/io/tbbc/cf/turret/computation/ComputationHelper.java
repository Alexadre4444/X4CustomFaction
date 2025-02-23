package io.tbbc.cf.turret.computation;

import io.tbbc.cf.bullet.Bullet;
import io.tbbc.cf.bullet.skin.BulletSkin;
import io.tbbc.cf.customizer.Customizer;
import io.tbbc.cf.modifier.Modifier;
import io.tbbc.cf.production.ProductionMethod;
import io.tbbc.cf.property.*;
import io.tbbc.cf.research.Research;
import io.tbbc.cf.turret.chassis.TurretChassis;
import io.tbbc.cf.turret.chassis.TurretChassisInstances;
import io.tbbc.cf.turret.chassis.skin.ChassisSkin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class ComputationHelper {
    private ComputationHelper() {
    }

    public static FinalProperties computeBulletEffectProperties(FinalProperties properties, Bullet bullet) {
        return bullet.effects().stream()
                .reduce(new FinalProperties(List.of()),
                        (finalProperties, effect) -> effect.getNewProperties(properties),
                        FinalProperties::concat);
    }

    public static FinalPropValue computeShootPerSecondForBeam(FinalProperties baseProperties) {
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME);
        FinalPropValue lifeTime = baseProperties.property(LIFE_TIME);
        BigDecimal baseValue = computeShootPerSecondForBeam(reloadTime.getBaseBigDecimalValue(), lifeTime.getBaseBigDecimalValue());
        BigDecimal finalValue = computeShootPerSecondForBeam(reloadTime.getFinalBigDecimalValue(), lifeTime.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(SHOOT_PER_SECOND, baseValue), TurretChassisInstances.Properties.SHOOT_PER_SECOND,
                List.of(new Modifier(SHOOT_PER_SECOND, modifier)));
    }

    public static FinalPropValue computeAcceleration(FinalProperties baseProperties) {
        FinalPropValue rotationSpeed = baseProperties.property(ROTATION_SPEED);
        BigDecimal baseValue = computeAcceleration(rotationSpeed.getBaseBigDecimalValue());
        BigDecimal finalValue = computeAcceleration(rotationSpeed.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(ROTATION_ACCELERATION, baseValue), TurretChassisInstances.Properties.ROTATION_ACCELERATION,
                List.of(new Modifier(ROTATION_ACCELERATION, modifier)));
    }

    private static BigDecimal computeAcceleration(BigDecimal rotationSpeed) {
        return rotationSpeed.multiply(new BigDecimal(2));
    }

    public static FinalPropValue computeRange(FinalProperties baseProperties) {
        FinalPropValue speed = baseProperties.property(SPEED);
        FinalPropValue lifeTime = baseProperties.property(LIFE_TIME);
        BigDecimal baseValue = computeRange(speed.getBaseBigDecimalValue(), lifeTime.getBaseBigDecimalValue());
        BigDecimal finalValue = computeRange(speed.getFinalBigDecimalValue(), lifeTime.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(RANGE, baseValue), TurretChassisInstances.Properties.RANGE,
                List.of(new Modifier(RANGE, modifier)));
    }

    private static BigDecimal computeRange(BigDecimal speed, BigDecimal lifeTime) {
        return speed.multiply(lifeTime);
    }

    public static FinalPropValue computeBurstTime(TurretChassis chassis, FinalProperties baseProperties) {
        return switch (chassis.type()) {
            case STANDARD, BEAM -> computeBurstTimeForStandard(baseProperties);
            case RAFFLE -> computeBurstTimeForRaffle(baseProperties);
        };
    }

    private static FinalPropValue computeBurstTimeForStandard(FinalProperties baseProperties) {
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME);
        BigDecimal baseValue = reloadTime.getBaseBigDecimalValue();
        BigDecimal finalValue = reloadTime.getFinalBigDecimalValue();
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(BURST_TIME, baseValue), TurretChassisInstances.Properties.BURST_TIME,
                List.of(new Modifier(BURST_TIME, modifier)));
    }

    private static FinalPropValue computeBurstTimeForRaffle(FinalProperties baseProperties) {
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME);
        FinalPropValue amount = baseProperties.property(AMOUNT);
        FinalPropValue fireRate = baseProperties.property(FIRE_RATE);
        BigDecimal baseValue = computeBurstTimeForRaffle(reloadTime.getBaseBigDecimalValue(),
                amount.getBaseBigDecimalValue(), fireRate.getBaseBigDecimalValue());
        BigDecimal finalValue = computeBurstTimeForRaffle(reloadTime.getFinalBigDecimalValue(),
                amount.getFinalBigDecimalValue(), fireRate.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(BURST_TIME, baseValue), TurretChassisInstances.Properties.BURST_TIME,
                List.of(new Modifier(BURST_TIME, modifier)));
    }

    private static BigDecimal computeBurstTimeForRaffle(BigDecimal reloadTime, BigDecimal amount, BigDecimal fireRate) {
        return reloadTime.add(amount.divide(fireRate, RoundingMode.HALF_UP));
    }

    private static int computeModifier(BigDecimal baseValue, BigDecimal finalValue) {
        return (finalValue.subtract(baseValue)).divide(baseValue, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
    }

    public static FinalPropValue computeShootPerSecond(TurretChassis turretChassis, FinalProperties baseProperties, FinalPropValue burstTime) {
        return switch (turretChassis.type()) {
            case BEAM -> throw new IllegalArgumentException("Wrong calculation method for beam turret");
            case STANDARD -> computeShootPerSecondForStandard(burstTime);
            case RAFFLE -> computeShootPerSecondForRaffle(baseProperties, burstTime);
        };
    }

    private static BigDecimal computeShootPerSecondForBeam(BigDecimal reloadTime, BigDecimal lifeTime) {
        return lifeTime.divide(reloadTime, RoundingMode.HALF_UP);
    }

    private static FinalPropValue computeShootPerSecondForStandard(FinalPropValue burstTime) {
        BigDecimal baseValue = computeShootPerSecond(new BigDecimal(1), burstTime.getBaseBigDecimalValue());
        BigDecimal finalValue = computeShootPerSecond(new BigDecimal(1), burstTime.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(SHOOT_PER_SECOND, baseValue), TurretChassisInstances.Properties.SHOOT_PER_SECOND,
                List.of(new Modifier(SHOOT_PER_SECOND, modifier)));
    }

    private static FinalPropValue computeShootPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue burstTime) {
        FinalPropValue amount = baseProperties.property(AMOUNT);
        BigDecimal baseValue = computeShootPerSecond(amount.getBaseBigDecimalValue(), burstTime.getBaseBigDecimalValue());
        BigDecimal finalValue = computeShootPerSecond(amount.getFinalBigDecimalValue(), burstTime.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(SHOOT_PER_SECOND, baseValue), TurretChassisInstances.Properties.SHOOT_PER_SECOND,
                List.of(new Modifier(SHOOT_PER_SECOND, modifier)));
    }

    private static BigDecimal computeShootPerSecond(BigDecimal amount, BigDecimal burstTime) {
        return amount.divide(burstTime, RoundingMode.HALF_UP);
    }

    public static FinalPropValue computeDamageHullPerSecond(TurretChassis chassis, FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        return switch (chassis.type()) {
            case STANDARD -> computeDamageHullPerSecondForStandard(baseProperties, shootPerSecond);
            case RAFFLE -> computeDamageHullPerSecondForRaffle(baseProperties, shootPerSecond);
            case BEAM -> computeDamageHullPerSecondForBeam(baseProperties, shootPerSecond);
        };
    }

    public static FinalPropValue computeDamageShieldPerSecond(TurretChassis chassis, FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        return switch (chassis.type()) {
            case STANDARD -> computeDamageShieldPerSecondForStandard(baseProperties, shootPerSecond);
            case RAFFLE -> computeDamageShieldPerSecondForRaffle(baseProperties, shootPerSecond);
            case BEAM -> computeDamageShieldPerSecondForBeam(baseProperties, shootPerSecond);
        };
    }

    private static FinalPropValue computeDamageShieldPerSecondForBeam(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseBigDecimalValue(), new BigDecimal(1), shootPerSecond.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getBaseBigDecimalValue(), new BigDecimal(1), shootPerSecond.getBaseBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_SHIELD, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_SHIELD,
                List.of(new Modifier(DAMAGE_PER_SECOND_SHIELD, modifier)));
    }

    private static FinalPropValue computeDamageHullPerSecondForBeam(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseBigDecimalValue(), new BigDecimal(1),
                shootPerSecond.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalBigDecimalValue(), new BigDecimal(1),
                shootPerSecond.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_HULL, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_HULL,
                List.of(new Modifier(DAMAGE_PER_SECOND_HULL, modifier)));
    }

    private static FinalPropValue computeDamageHullPerSecondForStandard(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        FinalPropValue amount = baseProperties.property(AMOUNT);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseBigDecimalValue(),
                barrelAmount.getBaseBigDecimalValue().multiply(amount.getBaseBigDecimalValue()),
                shootPerSecond.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalBigDecimalValue(),
                barrelAmount.getFinalBigDecimalValue().multiply(amount.getFinalBigDecimalValue()),
                shootPerSecond.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_HULL, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_HULL,
                List.of(new Modifier(DAMAGE_PER_SECOND_HULL, modifier)));
    }

    private static FinalPropValue computeDamageShieldPerSecondForStandard(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        FinalPropValue amount = baseProperties.property(AMOUNT);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseBigDecimalValue(),
                barrelAmount.getBaseBigDecimalValue().multiply(amount.getBaseBigDecimalValue()),
                shootPerSecond.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalBigDecimalValue(),
                barrelAmount.getFinalBigDecimalValue().multiply(amount.getFinalBigDecimalValue()),
                shootPerSecond.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_SHIELD, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_SHIELD,
                List.of(new Modifier(DAMAGE_PER_SECOND_SHIELD, modifier)));
    }

    private static FinalPropValue computeDamageHullPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseBigDecimalValue(), barrelAmount.getBaseBigDecimalValue(),
                shootPerSecond.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalBigDecimalValue(), barrelAmount.getFinalBigDecimalValue(),
                shootPerSecond.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_HULL, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_HULL,
                List.of(new Modifier(DAMAGE_PER_SECOND_HULL, modifier)));
    }

    private static FinalPropValue computeDamageShieldPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseBigDecimalValue(), barrelAmount.getBaseBigDecimalValue(),
                shootPerSecond.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalBigDecimalValue(), barrelAmount.getFinalBigDecimalValue(),
                shootPerSecond.getFinalBigDecimalValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_SHIELD, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_SHIELD,
                List.of(new Modifier(DAMAGE_PER_SECOND_SHIELD, modifier)));
    }

    private static BigDecimal computeDamagePerSecond(BigDecimal damage, BigDecimal barrelAmount, BigDecimal shootPerSecond) {
        return damage.multiply(barrelAmount).multiply(shootPerSecond);
    }

    public static FinalPropValue computeDamageBonusShield(FinalProperties baseProperties) {
        FinalPropValue damageHull = baseProperties.property(DAMAGE_HULL);
        FinalPropValue damageShield = baseProperties.property(DAMAGE_SHIELD);
        BigDecimal baseValue = computeDamageBonusShield(damageHull.getBaseBigDecimalValue(),
                damageShield.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamageBonusShield(damageHull.getFinalBigDecimalValue(),
                damageShield.getFinalBigDecimalValue());
        return new FinalPropValueComputed(TurretChassisInstances.Properties.DAMAGE_BONUS_SHIELD, baseValue, finalValue);
    }

    public static FinalPropValue computeAreaDamageBonusShield(FinalProperties baseProperties) {
        FinalPropValue areaDamageHull = baseProperties.property(AREA_DAMAGE_HULL);
        FinalPropValue areaDamageShield = baseProperties.property(AREA_DAMAGE_SHIELD);
        BigDecimal baseValue = computeDamageBonusShield(areaDamageHull.getBaseBigDecimalValue(),
                areaDamageShield.getBaseBigDecimalValue());
        BigDecimal finalValue = computeDamageBonusShield(areaDamageHull.getFinalBigDecimalValue(),
                areaDamageShield.getFinalBigDecimalValue());
        return new FinalPropValueComputed(TurretChassisInstances.Properties.AREA_DAMAGE_BONUS_SHIELD,
                baseValue, finalValue);
    }

    private static BigDecimal computeDamageBonusShield(BigDecimal damageHull, BigDecimal damageShield) {
        return damageShield.subtract(damageHull);
    }

    public static List<FinalPropValue> computeCLCost(FinalProperties baseProperties) {
        return List.of(
                computeCLClaytronicsCost(baseProperties),
                computeCLEcCost(baseProperties),
                computeCLHullPartCost(baseProperties)
        );
    }

    public static List<FinalPropValue> computeTerCost(FinalProperties baseProperties) {
        return List.of(
                computeTerEcCost(baseProperties),
                computeTerComputronicCost(baseProperties),
                computeTerMicrolatticeCost(baseProperties),
                computeTerCarbideCost(baseProperties)
        );
    }

    private static FinalPropValue computeTerCarbideCost(FinalProperties baseProperties) {
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS);

        BigDecimal baseValue = costTurretComps.getBaseBigDecimalValue()
                .divide(new BigDecimal("10"), RoundingMode.HALF_UP).multiply(new BigDecimal("0.2"));
        BigDecimal finalValue = costTurretComps.getFinalBigDecimalValue()
                .divide(new BigDecimal("10"), RoundingMode.HALF_UP).multiply(new BigDecimal("0.2"));
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_CARBIDE, baseValue, finalValue);
    }

    private static FinalPropValue computeTerMicrolatticeCost(FinalProperties baseProperties) {
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS);

        BigDecimal baseValue = costTurretComps.getBaseBigDecimalValue()
                .divide(new BigDecimal("10"), RoundingMode.HALF_UP).multiply(new BigDecimal("0.8"));
        BigDecimal finalValue = costTurretComps.getFinalBigDecimalValue()
                .divide(new BigDecimal("10"), RoundingMode.HALF_UP).multiply(new BigDecimal("0.8"));
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_MICROLATICE, baseValue, finalValue);
    }

    private static FinalPropValue computeTerComputronicCost(FinalProperties baseProperties) {
        FinalPropValue costAdvancedElectronics = baseProperties.property(COST_CW_ADVANCED_ELECTRONICS);

        BigDecimal baseValue = costAdvancedElectronics.getBaseBigDecimalValue();
        BigDecimal finalValue = costAdvancedElectronics.getFinalBigDecimalValue();
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_COMPUTRONIC, baseValue, finalValue);
    }

    private static FinalPropValue computeTerEcCost(FinalProperties baseProperties) {
        FinalPropValue costEnergyCells = baseProperties.property(COST_CW_ENERGY_CELLS);

        BigDecimal baseValue = costEnergyCells.getBaseBigDecimalValue().multiply(new BigDecimal(7));
        BigDecimal finalValue = costEnergyCells.getFinalBigDecimalValue().multiply(new BigDecimal(7));
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_ENERGY_CELLS, baseValue, finalValue);
    }

    private static FinalPropValue computeCLHullPartCost(FinalProperties baseProperties) {
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS);

        BigDecimal baseValue = costTurretComps.getBaseBigDecimalValue();
        BigDecimal finalValue = costTurretComps.getFinalBigDecimalValue();
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_CL_HULL_PART, baseValue, finalValue);
    }

    private static FinalPropValue computeCLClaytronicsCost(FinalProperties baseProperties) {
        FinalPropValue costAdvancedElectronics = baseProperties.property(COST_CW_ADVANCED_ELECTRONICS);

        BigDecimal baseValue = costAdvancedElectronics.getBaseBigDecimalValue().multiply(new BigDecimal(3));
        BigDecimal finalValue = costAdvancedElectronics.getFinalBigDecimalValue().multiply(new BigDecimal(3));
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_CL_CLAYTRONICS, baseValue, finalValue);
    }

    private static FinalPropValue computeCLEcCost(FinalProperties baseProperties) {
        FinalPropValue costEnergyCells = baseProperties.property(COST_CW_ENERGY_CELLS);

        BigDecimal baseValue = costEnergyCells.getBaseBigDecimalValue().multiply(new BigDecimal(10));
        BigDecimal finalValue = costEnergyCells.getFinalBigDecimalValue().multiply(new BigDecimal(10));
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_CL_ENERGY_CELLS, baseValue, finalValue);
    }

    public static List<Research> computeRequiredResearch(ChassisSkin chassisSkin, BulletSkin bulletSkin,
                                                         List<ProductionMethod> productionMethods,
                                                         List<Customizer> customizers) {
        return Stream.of(chassisSkin.requiredResearch().stream(),
                        bulletSkin.requiredResearch().stream(),
                        productionMethods.stream()
                                .flatMap(productionMethod -> productionMethod.requiredResearch().stream()),
                        customizers.stream().map(Customizer::requiredResearch))
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .flatMap(research -> getAllParentResearch(research).stream())
                .distinct()
                .toList();
    }

    public static List<Research> getAllParentResearch(Research research) {
        List<Research> result = new ArrayList<>();
        result.add(research);
        for (Research parent : research.parents()) {
            result.addAll(getAllParentResearch(parent));
        }
        return result;
    }
}
