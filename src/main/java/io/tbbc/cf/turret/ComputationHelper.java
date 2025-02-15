package io.tbbc.cf.turret;

import io.tbbc.cf.bullet.skin.BulletSkin;
import io.tbbc.cf.customizer.Customizer;
import io.tbbc.cf.modifier.Modifier;
import io.tbbc.cf.production.ProductionMethod;
import io.tbbc.cf.property.*;
import io.tbbc.cf.research.Research;
import io.tbbc.cf.turret.chassis.TurretChassis;
import io.tbbc.cf.turret.chassis.TurretChassisInstances;
import io.tbbc.cf.turret.chassis.skin.ChassisSkin;

import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class ComputationHelper {
    private ComputationHelper() {
    }

    static FinalPropValue computeShootPerSecondForBeam(FinalProperties baseProperties) {
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME);
        FinalPropValue lifeTime = baseProperties.property(LIFE_TIME);
        double baseValue = computeShootPerSecondForBeam(reloadTime.getBaseDoubleValue(), lifeTime.getBaseDoubleValue());
        double finalValue = computeShootPerSecondForBeam(reloadTime.getFinalDoubleValue(), lifeTime.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(SHOOT_PER_SECOND, baseValue), TurretChassisInstances.Properties.SHOOT_PER_SECOND,
                List.of(new Modifier(SHOOT_PER_SECOND, modifier)));
    }

    static FinalPropValue computeAcceleration(FinalProperties baseProperties) {
        FinalPropValue rotationSpeed = baseProperties.property(ROTATION_SPEED);
        double baseValue = computeAcceleration(rotationSpeed.getBaseDoubleValue());
        double finalValue = computeAcceleration(rotationSpeed.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(ROTATION_ACCELERATION, baseValue), TurretChassisInstances.Properties.ROTATION_ACCELERATION,
                List.of(new Modifier(ROTATION_ACCELERATION, modifier)));
    }

    private static double computeAcceleration(double rotationSpeed) {
        return rotationSpeed * 2;
    }

    static FinalPropValue computeRange(FinalProperties baseProperties) {
        FinalPropValue speed = baseProperties.property(SPEED);
        FinalPropValue lifeTime = baseProperties.property(LIFE_TIME);
        double baseValue = computeRange(speed.getBaseDoubleValue(), lifeTime.getBaseDoubleValue());
        double finalValue = computeRange(speed.getFinalDoubleValue(), lifeTime.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(RANGE, baseValue), TurretChassisInstances.Properties.RANGE,
                List.of(new Modifier(RANGE, modifier)));
    }

    private static double computeRange(double speed, double lifeTime) {
        return speed * lifeTime;
    }

    static FinalPropValue computeBurstTime(TurretChassis chassis, FinalProperties baseProperties) {
        return switch (chassis.type()) {
            case STANDARD, BEAM -> computeBurstTimeForStandard(baseProperties);
            case RAFFLE -> computeBurstTimeForRaffle(baseProperties);
        };
    }

    private static FinalPropValue computeBurstTimeForStandard(FinalProperties baseProperties) {
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME);
        double baseValue = reloadTime.getBaseDoubleValue();
        double finalValue = reloadTime.getFinalDoubleValue();
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(BURST_TIME, baseValue), TurretChassisInstances.Properties.BURST_TIME,
                List.of(new Modifier(BURST_TIME, modifier)));
    }

    private static FinalPropValue computeBurstTimeForRaffle(FinalProperties baseProperties) {
        FinalPropValue reloadTime = baseProperties.property(RELOAD_TIME);
        FinalPropValue amount = baseProperties.property(AMOUNT);
        FinalPropValue fireRate = baseProperties.property(FIRE_RATE);
        double baseValue = computeBurstTimeForRaffle(reloadTime.getBaseDoubleValue(),
                amount.getBaseDoubleValue(), fireRate.getBaseDoubleValue());
        double finalValue = computeBurstTimeForRaffle(reloadTime.getFinalDoubleValue(),
                amount.getFinalDoubleValue(), fireRate.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(BURST_TIME, baseValue), TurretChassisInstances.Properties.BURST_TIME,
                List.of(new Modifier(BURST_TIME, modifier)));
    }

    private static double computeBurstTimeForRaffle(double reloadTime, double amount, double fireRate) {
        return reloadTime + (amount / fireRate);
    }

    private static int computeModifier(double baseValue, double finalValue) {
        return (int) Math.round((finalValue - baseValue) / baseValue * 100);
    }

    public static FinalPropValue computeShootPerSecond(TurretChassis turretChassis, FinalProperties baseProperties, FinalPropValue burstTime) {
        return switch (turretChassis.type()) {
            case BEAM -> throw new IllegalArgumentException("Wrong calculation method for beam turret");
            case STANDARD -> computeShootPerSecondForStandard(burstTime);
            case RAFFLE -> computeShootPerSecondForRaffle(baseProperties, burstTime);
        };
    }

    private static double computeShootPerSecondForBeam(double reloadTime, double lifeTime) {
        return lifeTime / reloadTime;
    }

    private static FinalPropValue computeShootPerSecondForStandard(FinalPropValue burstTime) {
        double baseValue = computeShootPerSecond(1, burstTime.getBaseDoubleValue());
        double finalValue = computeShootPerSecond(1, burstTime.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(SHOOT_PER_SECOND, baseValue), TurretChassisInstances.Properties.SHOOT_PER_SECOND,
                List.of(new Modifier(SHOOT_PER_SECOND, modifier)));
    }

    private static FinalPropValue computeShootPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue burstTime) {
        FinalPropValue amount = baseProperties.property(AMOUNT);
        double baseValue = computeShootPerSecond(amount.getBaseDoubleValue(), burstTime.getBaseDoubleValue());
        double finalValue = computeShootPerSecond(amount.getFinalDoubleValue(), burstTime.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(SHOOT_PER_SECOND, baseValue), TurretChassisInstances.Properties.SHOOT_PER_SECOND,
                List.of(new Modifier(SHOOT_PER_SECOND, modifier)));
    }

    private static double computeShootPerSecond(double amount, double burstTime) {
        return amount / burstTime;
    }

    static FinalPropValue computeDamageHullPerSecond(TurretChassis chassis, FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        return switch (chassis.type()) {
            case STANDARD -> computeDamageHullPerSecondForStandard(baseProperties, shootPerSecond);
            case RAFFLE -> computeDamageHullPerSecondForRaffle(baseProperties, shootPerSecond);
            case BEAM -> computeDamageHullPerSecondForBeam(baseProperties, shootPerSecond);
        };
    }

    static FinalPropValue computeDamageShieldPerSecond(TurretChassis chassis, FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        return switch (chassis.type()) {
            case STANDARD -> computeDamageShieldPerSecondForStandard(baseProperties, shootPerSecond);
            case RAFFLE -> computeDamageShieldPerSecondForRaffle(baseProperties, shootPerSecond);
            case BEAM -> computeDamageShieldPerSecondForBeam(baseProperties, shootPerSecond);
        };
    }

    private static FinalPropValue computeDamageShieldPerSecondForBeam(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD);
        double baseValue = computeDamagePerSecond(damage.getBaseDoubleValue(), 1, shootPerSecond.getBaseDoubleValue());
        double finalValue = computeDamagePerSecond(damage.getFinalDoubleValue(), 1, shootPerSecond.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_SHIELD, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_SHIELD,
                List.of(new Modifier(DAMAGE_PER_SECOND_SHIELD, modifier)));
    }

    private static FinalPropValue computeDamageHullPerSecondForBeam(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL);
        double baseValue = computeDamagePerSecond(damage.getBaseDoubleValue(), 1,
                shootPerSecond.getBaseDoubleValue());
        double finalValue = computeDamagePerSecond(damage.getFinalDoubleValue(), 1,
                shootPerSecond.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_HULL, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_HULL,
                List.of(new Modifier(DAMAGE_PER_SECOND_HULL, modifier)));
    }

    private static FinalPropValue computeDamageHullPerSecondForStandard(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        FinalPropValue amount = baseProperties.property(AMOUNT);
        double baseValue = computeDamagePerSecond(damage.getBaseDoubleValue(),
                barrelAmount.getBaseDoubleValue() * amount.getBaseDoubleValue(),
                shootPerSecond.getBaseDoubleValue());
        double finalValue = computeDamagePerSecond(damage.getFinalDoubleValue(),
                barrelAmount.getFinalDoubleValue() * amount.getFinalDoubleValue(),
                shootPerSecond.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_HULL, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_HULL,
                List.of(new Modifier(DAMAGE_PER_SECOND_HULL, modifier)));
    }

    private static FinalPropValue computeDamageShieldPerSecondForStandard(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        FinalPropValue amount = baseProperties.property(AMOUNT);
        double baseValue = computeDamagePerSecond(damage.getBaseDoubleValue(),
                barrelAmount.getBaseDoubleValue() * amount.getBaseDoubleValue(),
                shootPerSecond.getBaseDoubleValue());
        double finalValue = computeDamagePerSecond(damage.getFinalDoubleValue(),
                barrelAmount.getFinalDoubleValue() * amount.getFinalDoubleValue(),
                shootPerSecond.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_SHIELD, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_SHIELD,
                List.of(new Modifier(DAMAGE_PER_SECOND_SHIELD, modifier)));
    }

    private static FinalPropValue computeDamageHullPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_HULL);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        double baseValue = computeDamagePerSecond(damage.getBaseDoubleValue(), barrelAmount.getBaseDoubleValue(),
                shootPerSecond.getBaseDoubleValue());
        double finalValue = computeDamagePerSecond(damage.getFinalDoubleValue(), barrelAmount.getFinalDoubleValue(),
                shootPerSecond.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_HULL, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_HULL,
                List.of(new Modifier(DAMAGE_PER_SECOND_HULL, modifier)));
    }

    private static FinalPropValue computeDamageShieldPerSecondForRaffle(FinalProperties baseProperties, FinalPropValue shootPerSecond) {
        FinalPropValue damage = baseProperties.property(DAMAGE_SHIELD);
        FinalPropValue barrelAmount = baseProperties.property(BARREL_AMOUNT);
        double baseValue = computeDamagePerSecond(damage.getBaseDoubleValue(), barrelAmount.getBaseDoubleValue(),
                shootPerSecond.getBaseDoubleValue());
        double finalValue = computeDamagePerSecond(damage.getFinalDoubleValue(), barrelAmount.getFinalDoubleValue(),
                shootPerSecond.getFinalDoubleValue());
        int modifier = computeModifier(baseValue, finalValue);
        return new FinalPropValueBase(new Property(DAMAGE_PER_SECOND_SHIELD, baseValue), TurretChassisInstances.Properties.DAMAGE_PER_SECOND_SHIELD,
                List.of(new Modifier(DAMAGE_PER_SECOND_SHIELD, modifier)));
    }

    private static double computeDamagePerSecond(double damage, double barrelAmount, double shootPerSecond) {
        return damage * barrelAmount * shootPerSecond;
    }

    public static FinalPropValue computeDamageBonusShield(FinalProperties baseProperties) {
        FinalPropValue damageHull = baseProperties.property(DAMAGE_HULL);
        FinalPropValue damageShield = baseProperties.property(DAMAGE_SHIELD);
        double baseValue = computeDamageBonusShield(damageHull.getBaseDoubleValue(),
                damageShield.getBaseDoubleValue());
        double finalValue = computeDamageBonusShield(damageHull.getFinalDoubleValue(),
                damageShield.getFinalDoubleValue());
        return new FinalPropValueComputed(TurretChassisInstances.Properties.DAMAGE_BONUS_SHIELD, baseValue, finalValue);
    }

    private static double computeDamageBonusShield(double damageHull, double damageShield) {
        return damageShield - damageHull;
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

        double baseValue = costTurretComps.getBaseDoubleValue() / 10d * 0.2d;
        double finalValue = costTurretComps.getFinalDoubleValue() / 10d * 0.2d;
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_CARBIDE, baseValue, finalValue);
    }

    private static FinalPropValue computeTerMicrolatticeCost(FinalProperties baseProperties) {
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS);

        double baseValue = costTurretComps.getBaseDoubleValue() / 10d * 0.8d;
        double finalValue = costTurretComps.getFinalDoubleValue() / 10d * 0.8d;
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_MICROLATICE, baseValue, finalValue);
    }

    private static FinalPropValue computeTerComputronicCost(FinalProperties baseProperties) {
        FinalPropValue costAdvancedElectronics = baseProperties.property(COST_CW_ADVANCED_ELECTRONICS);

        double baseValue = costAdvancedElectronics.getBaseDoubleValue();
        double finalValue = costAdvancedElectronics.getFinalDoubleValue();
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_COMPUTRONIC, baseValue, finalValue);
    }

    private static FinalPropValue computeTerEcCost(FinalProperties baseProperties) {
        FinalPropValue costEnergyCells = baseProperties.property(COST_CW_ENERGY_CELLS);

        double baseValue = costEnergyCells.getBaseDoubleValue() * 7d;
        double finalValue = costEnergyCells.getFinalDoubleValue() * 7d;
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_TER_ENERGY_CELLS, baseValue, finalValue);
    }

    private static FinalPropValue computeCLHullPartCost(FinalProperties baseProperties) {
        FinalPropValue costTurretComps = baseProperties.property(COST_CW_TURRET_COMPS);

        double baseValue = costTurretComps.getBaseDoubleValue();
        double finalValue = costTurretComps.getFinalDoubleValue();
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_CL_HULL_PART, baseValue, finalValue);
    }

    private static FinalPropValue computeCLClaytronicsCost(FinalProperties baseProperties) {
        FinalPropValue costAdvancedElectronics = baseProperties.property(COST_CW_ADVANCED_ELECTRONICS);

        double baseValue = costAdvancedElectronics.getBaseDoubleValue() / 3d;
        double finalValue = costAdvancedElectronics.getFinalDoubleValue() / 3d;
        return new FinalPropValueComputed(TurretChassisInstances.Properties.COST_CL_CLAYTRONICS, baseValue, finalValue);
    }

    private static FinalPropValue computeCLEcCost(FinalProperties baseProperties) {
        FinalPropValue costEnergyCells = baseProperties.property(COST_CW_ENERGY_CELLS);

        double baseValue = costEnergyCells.getBaseDoubleValue() * 10d;
        double finalValue = costEnergyCells.getFinalDoubleValue() * 10d;
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
                .flatMap(research -> Stream.concat(Stream.of(research), research.parents().stream()))
                .distinct()
                .toList();
    }
}
