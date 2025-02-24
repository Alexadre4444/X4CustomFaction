package io.tbbc.cf.turret.computation;

import io.tbbc.cf.bullet.Bullet;
import io.tbbc.cf.bullet.skin.BulletSkin;
import io.tbbc.cf.customizer.Customizer;
import io.tbbc.cf.production.ProductionMethod;
import io.tbbc.cf.property.FinalPropValue;
import io.tbbc.cf.property.FinalPropValueComputed;
import io.tbbc.cf.property.FinalProperties;
import io.tbbc.cf.research.Research;
import io.tbbc.cf.turret.chassis.TurretChassis;
import io.tbbc.cf.turret.chassis.skin.ChassisSkin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.Properties.*;
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
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME_NAME);
        FinalPropValue lifeTime = baseProperties.property(LIFE_TIME_NAME);
        BigDecimal baseValue = computeShootPerSecondForBeam(reloadTime.getBaseValue(), lifeTime.getBaseValue());
        BigDecimal finalValue = computeShootPerSecondForBeam(reloadTime.getFinalValue(), lifeTime.getFinalValue());
        return new FinalPropValueComputed(SHOOT_PER_SECOND, baseValue, finalValue);
    }

    public static FinalPropValue computeAcceleration(FinalProperties baseProperties) {
        FinalPropValue rotationSpeed = baseProperties.property(ROTATION_SPEED_NAME);
        BigDecimal baseValue = computeAcceleration(rotationSpeed.getBaseValue());
        BigDecimal finalValue = computeAcceleration(rotationSpeed.getFinalValue());
        return new FinalPropValueComputed(ROTATION_ACCELERATION, baseValue, finalValue);
    }

    private static BigDecimal computeAcceleration(BigDecimal rotationSpeed) {
        return rotationSpeed.multiply(new BigDecimal(2));
    }

    public static FinalPropValue computeRange(FinalProperties baseProperties) {
        FinalPropValue speed = baseProperties.property(SPEED_NAME);
        FinalPropValue lifeTime = baseProperties.property(LIFE_TIME_NAME);
        BigDecimal baseValue = computeRange(speed.getBaseValue(), lifeTime.getBaseValue());
        BigDecimal finalValue = computeRange(speed.getFinalValue(), lifeTime.getFinalValue());
        return new FinalPropValueComputed(RANGE, baseValue, finalValue);
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
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME_NAME);
        BigDecimal baseValue = reloadTime.getBaseValue();
        BigDecimal finalValue = reloadTime.getFinalValue();
        return new FinalPropValueComputed(BURST_TIME, baseValue, finalValue);
    }

    private static FinalPropValue computeBurstTimeForRaffle(FinalProperties baseProperties) {
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME_NAME);
        FinalPropValue amount = baseProperties.property(AMOUNT_NAME);
        FinalPropValue fireRate = baseProperties.property(FIRE_RATE_NAME);
        BigDecimal baseValue = computeBurstTimeForRaffle(reloadTime.getBaseValue(),
                amount.getBaseValue(), fireRate.getBaseValue());
        BigDecimal finalValue = computeBurstTimeForRaffle(reloadTime.getFinalValue(),
                amount.getFinalValue(), fireRate.getFinalValue());
        return new FinalPropValueComputed(BURST_TIME, baseValue, finalValue);
    }

    private static BigDecimal computeBurstTimeForRaffle(BigDecimal reloadTime, BigDecimal amount, BigDecimal fireRate) {
        return reloadTime.add(amount.divide(fireRate, BURST_TIME.decimal(), RoundingMode.HALF_UP));
    }

    public static FinalPropValue computeShootPerSecond(TurretChassis turretChassis, FinalProperties baseProperties, FinalPropValue burstTime) {
        return switch (turretChassis.type()) {
            case BEAM -> throw new IllegalArgumentException("Wrong calculation method for beam turret");
            case STANDARD -> computeShootPerSecondForStandard(burstTime);
            case RAFFLE -> computeShootPerSecondForRaffle(baseProperties, burstTime);
        };
    }

    private static BigDecimal computeShootPerSecondForBeam(BigDecimal reloadTime, BigDecimal lifeTime) {
        return lifeTime.divide(reloadTime, SHOOT_PER_SECOND.decimal(), RoundingMode.HALF_UP);
    }

    private static FinalPropValue computeShootPerSecondForStandard(FinalPropValue burstTime) {
        BigDecimal baseValue = computeShootPerSecond(new BigDecimal(1), burstTime.getBaseValue());
        BigDecimal finalValue = computeShootPerSecond(new BigDecimal(1), burstTime.getFinalValue());
        return new FinalPropValueComputed(SHOOT_PER_SECOND, baseValue, finalValue);
    }

    private static FinalPropValue computeShootPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue burstTime) {
        FinalPropValue amount = baseProperties.property(AMOUNT_NAME);
        BigDecimal baseValue = computeShootPerSecond(amount.getBaseValue(), burstTime.getBaseValue());
        BigDecimal finalValue = computeShootPerSecond(amount.getFinalValue(), burstTime.getFinalValue());
        return new FinalPropValueComputed(SHOOT_PER_SECOND, baseValue, finalValue);
    }

    private static BigDecimal computeShootPerSecond(BigDecimal amount, BigDecimal burstTime) {
        return amount.divide(burstTime, SHOOT_PER_SECOND.decimal(), RoundingMode.HALF_UP);
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
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD_NAME);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseValue(), new BigDecimal(1), shootPerSecond.getBaseValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalValue(), new BigDecimal(1), shootPerSecond.getFinalValue());
        return new FinalPropValueComputed(DAMAGE_PER_SECOND_SHIELD, baseValue, finalValue);
    }

    private static FinalPropValue computeDamageHullPerSecondForBeam(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL_NAME);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseValue(), new BigDecimal(1),
                shootPerSecond.getBaseValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalValue(), new BigDecimal(1),
                shootPerSecond.getFinalValue());
        return new FinalPropValueComputed(DAMAGE_PER_SECOND_HULL, baseValue, finalValue);
    }

    private static FinalPropValue computeDamageHullPerSecondForStandard(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL_NAME);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT_NAME);
        FinalPropValue amount = baseProperties.property(AMOUNT_NAME);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseValue(),
                barrelAmount.getBaseValue().multiply(amount.getBaseValue()),
                shootPerSecond.getBaseValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalValue(),
                barrelAmount.getFinalValue().multiply(amount.getFinalValue()),
                shootPerSecond.getFinalValue());
        return new FinalPropValueComputed(DAMAGE_PER_SECOND_HULL, baseValue, finalValue);
    }

    private static FinalPropValue computeDamageShieldPerSecondForStandard(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD_NAME);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT_NAME);
        FinalPropValue amount = baseProperties.property(AMOUNT_NAME);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseValue(),
                barrelAmount.getBaseValue().multiply(amount.getBaseValue()),
                shootPerSecond.getBaseValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalValue(),
                barrelAmount.getFinalValue().multiply(amount.getFinalValue()),
                shootPerSecond.getFinalValue());
        return new FinalPropValueComputed(DAMAGE_PER_SECOND_SHIELD, baseValue, finalValue);
    }

    private static FinalPropValue computeDamageHullPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL_NAME);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT_NAME);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseValue(), barrelAmount.getBaseValue(),
                shootPerSecond.getBaseValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalValue(), barrelAmount.getFinalValue(),
                shootPerSecond.getFinalValue());
        return new FinalPropValueComputed(DAMAGE_PER_SECOND_HULL, baseValue, finalValue);
    }

    private static FinalPropValue computeDamageShieldPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD_NAME);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT_NAME);
        BigDecimal baseValue = computeDamagePerSecond(damage.getBaseValue(), barrelAmount.getBaseValue(),
                shootPerSecond.getBaseValue());
        BigDecimal finalValue = computeDamagePerSecond(damage.getFinalValue(), barrelAmount.getFinalValue(),
                shootPerSecond.getFinalValue());
        return new FinalPropValueComputed(DAMAGE_PER_SECOND_SHIELD, baseValue, finalValue);
    }

    private static BigDecimal computeDamagePerSecond(BigDecimal damage, BigDecimal barrelAmount, BigDecimal shootPerSecond) {
        return damage.multiply(barrelAmount).multiply(shootPerSecond);
    }

    public static FinalPropValue computeDamageBonusShield(FinalProperties baseProperties) {
        FinalPropValue damageHull = baseProperties.property(DAMAGE_HULL_NAME);
        FinalPropValue damageShield = baseProperties.property(DAMAGE_SHIELD_NAME);
        BigDecimal baseValue = computeDamageBonusShield(damageHull.getBaseValue(),
                damageShield.getBaseValue());
        BigDecimal finalValue = computeDamageBonusShield(damageHull.getFinalValue(),
                damageShield.getFinalValue());
        return new FinalPropValueComputed(DAMAGE_BONUS_SHIELD, baseValue, finalValue);
    }

    public static FinalPropValue computeAreaDamageBonusShield(FinalProperties baseProperties) {
        FinalPropValue areaDamageHull = baseProperties.property(AREA_DAMAGE_HULL_NAME);
        FinalPropValue areaDamageShield = baseProperties.property(AREA_DAMAGE_SHIELD_NAME);
        BigDecimal baseValue = computeDamageBonusShield(areaDamageHull.getBaseValue(),
                areaDamageShield.getBaseValue());
        BigDecimal finalValue = computeDamageBonusShield(areaDamageHull.getFinalValue(),
                areaDamageShield.getFinalValue());
        return new FinalPropValueComputed(AREA_DAMAGE_BONUS_SHIELD,
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
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS_NAME);

        BigDecimal baseValue = costTurretComps.getBaseValue()
                .divide(new BigDecimal("10"), COST_TER_CARBIDE.decimal(), RoundingMode.HALF_UP).multiply(new BigDecimal("0.2"));
        BigDecimal finalValue = costTurretComps.getFinalValue()
                .divide(new BigDecimal("10"), COST_TER_CARBIDE.decimal(), RoundingMode.HALF_UP).multiply(new BigDecimal("0.2"));
        return new FinalPropValueComputed(COST_TER_CARBIDE, baseValue, finalValue);
    }

    private static FinalPropValue computeTerMicrolatticeCost(FinalProperties baseProperties) {
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS_NAME);

        BigDecimal baseValue = costTurretComps.getBaseValue()
                .divide(new BigDecimal("10"), COST_TER_MICROLATICE.decimal(), RoundingMode.HALF_UP).multiply(new BigDecimal("0.8"));
        BigDecimal finalValue = costTurretComps.getFinalValue()
                .divide(new BigDecimal("10"), COST_TER_MICROLATICE.decimal(), RoundingMode.HALF_UP).multiply(new BigDecimal("0.8"));
        return new FinalPropValueComputed(COST_TER_MICROLATICE, baseValue, finalValue);
    }

    private static FinalPropValue computeTerComputronicCost(FinalProperties baseProperties) {
        FinalPropValue costAdvancedElectronics = baseProperties.property(COST_CW_ADVANCED_ELECTRONICS_NAME);

        BigDecimal baseValue = costAdvancedElectronics.getBaseValue();
        BigDecimal finalValue = costAdvancedElectronics.getFinalValue();
        return new FinalPropValueComputed(COST_TER_COMPUTRONIC, baseValue, finalValue);
    }

    private static FinalPropValue computeTerEcCost(FinalProperties baseProperties) {
        FinalPropValue costEnergyCells = baseProperties.property(COST_CW_ENERGY_CELLS_NAME);

        BigDecimal baseValue = costEnergyCells.getBaseValue().multiply(new BigDecimal(7));
        BigDecimal finalValue = costEnergyCells.getFinalValue().multiply(new BigDecimal(7));
        return new FinalPropValueComputed(COST_TER_ENERGY_CELLS, baseValue, finalValue);
    }

    private static FinalPropValue computeCLHullPartCost(FinalProperties baseProperties) {
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS_NAME);

        BigDecimal baseValue = costTurretComps.getBaseValue();
        BigDecimal finalValue = costTurretComps.getFinalValue();
        return new FinalPropValueComputed(COST_CL_HULL_PART, baseValue, finalValue);
    }

    private static FinalPropValue computeCLClaytronicsCost(FinalProperties baseProperties) {
        FinalPropValue costAdvancedElectronics = baseProperties.property(COST_CW_ADVANCED_ELECTRONICS_NAME);

        BigDecimal baseValue = costAdvancedElectronics.getBaseValue().multiply(new BigDecimal(3));
        BigDecimal finalValue = costAdvancedElectronics.getFinalValue().multiply(new BigDecimal(3));
        return new FinalPropValueComputed(COST_CL_CLAYTRONICS, baseValue, finalValue);
    }

    private static FinalPropValue computeCLEcCost(FinalProperties baseProperties) {
        FinalPropValue costEnergyCells = baseProperties.property(COST_CW_ENERGY_CELLS_NAME);

        BigDecimal baseValue = costEnergyCells.getBaseValue().multiply(new BigDecimal(10));
        BigDecimal finalValue = costEnergyCells.getFinalValue().multiply(new BigDecimal(10));
        return new FinalPropValueComputed(COST_CL_ENERGY_CELLS, baseValue, finalValue);
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
