package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.common.Size;
import io.tbbc.cf.property.CategoryInstances;
import io.tbbc.cf.property.PropertyDefinition;
import io.tbbc.cf.property.PropertyName;
import io.tbbc.cf.turret.chassis.skin.ChassisSkin;

import java.util.Comparator;
import java.util.stream.Stream;

import static io.tbbc.cf.property.Accessibility.ADVANCED;
import static io.tbbc.cf.property.Accessibility.BASIC;
import static io.tbbc.cf.property.CategoryInstances.*;
import static io.tbbc.cf.turret.chassis.skin.ChassisSkinInstances.*;

public class TurretChassisInstances {
    // Medium
    public static final TurretChassis M_PULSE = new TurretChassis("m_pulse", "Pulse",
            Size.MEDIUM, ChassisType.RAFFLE,
            new ChassisPropsRaffle(140, 660, 2, 3,
                    0.5, 6, 1855, 1.844, 12, 12, 1, 0,
                    0.019, 0.32, 1, 5, 10),
            Stream.of(ARG_M_PULSE, TEL_M_PULSE, PAR_M_PULSE, XEN_M_PULSE, SPL_M_PULSE, TER_M_PULSE)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_GATLING = new TurretChassis("m_gatling", "Gatling",
            Size.MEDIUM, ChassisType.RAFFLE,
            new ChassisPropsRaffle(120, 660, 2, 15,
                    1.3, 10, 895, 3.8, 11, 11, 2, 0.01,
                    0.032, 0.49, 1, 6, 13),
            Stream.of(ARG_M_GATLING, TEL_M_GATLING, PAR_M_GATLING, SPL_M_GATLING, TER_M_GATLING)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_PLASMA = new TurretChassis("m_plasma", "Plasma",
            Size.MEDIUM, ChassisType.STANDARD,
            new ChassisPropsStandard(50, 660, 2, 1, 4.2,
                    610, 6.2, 570, 570, 1, 0f, 0, 0.01, 2,
                    8, 10),
            Stream.of(ARG_M_PLASMA, TEL_M_PLASMA, PAR_M_PLASMA, SPL_M_PLASMA)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_SHOTGUN = new TurretChassis("m_shotgun", "Shotgun",
            Size.MEDIUM, ChassisType.STANDARD,
            new ChassisPropsStandard(108, 660, 2, 5, 1.2,
                    1070, 2.2, 32, 32, 3, 0.5, 0.003, 1.10,
                    2, 6, 9),
            Stream.of(ARG_M_SHOTGUN, TEL_M_SHOTGUN, PAR_M_SHOTGUN, SPL_M_SHOTGUN)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_BEAM = new TurretChassis("m_beam", "Beam",
            Size.MEDIUM, ChassisType.BEAM,
            new ChassisPropsBeam(80, 660, 3, 1, 72, 72,
                    2550, 6, 10, 10),
            Stream.of(ARG_M_BEAM, TEL_M_BEAM, PAR_M_BEAM, XEN_M_BEAM, SPL_M_BEAM, TER_M_BEAM)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_ELECTROMAGNETIC = new TurretChassis("m_electromagnetic", "Electromagnetic",
            Size.MEDIUM, ChassisType.STANDARD,
            new ChassisPropsStandard(120, 800, 1, 1, 0.8,
                    2000, 1.8, 165, 165, 1, 0f, 0.003, 0.06, 2,
                    10, 30),
            Stream.of(TER_M_ELECTROMAGNETIC)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    // Large
    public static final TurretChassis L_PULSE = new TurretChassis("l_pulse", "Pulse",
            Size.LARGE, ChassisType.STANDARD,
            new ChassisPropsStandard(30, 3000, 2, 1,
                    2, 2041, 3.219, 367, 367, 1, 0,
                    0.015, 0.025, 4, 10, 20),
            Stream.of(ARG_L_PULSE, TEL_L_PULSE, PAR_L_PULSE, XEN_L_PULSE, KHA_L_PULSE, SPL_L_PULSE, TER_L_PULSE)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_GATLING = new TurretChassis("l_gatling", "Gatling",
            Size.LARGE, ChassisType.RAFFLE,
            new ChassisPropsRaffle(30, 3000, 2, 4,
                    3, 2, 2500, 2.5, 250, 250, 1, 0,
                    0.015, 0.1, 4, 10, 20),
            Stream.of(ARG_L_PULSE, TEL_L_PULSE, PAR_L_PULSE, XEN_L_PULSE, KHA_L_PULSE, SPL_L_PULSE, TER_L_GATLING)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_PLASMA = new TurretChassis("l_plasma", "Plasma",
            Size.LARGE, ChassisType.STANDARD,
            new ChassisPropsStandard(33, 3000, 2, 1, 4.2,
                    537, 14, 2000, 2000, 1, 0f, 0, 0, 5,
                    8, 32),
            Stream.of(ARG_L_PLASMA, TEL_L_PLASMA, PAR_L_PLASMA, SPL_L_PLASMA, XEN_L_PULSE)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_BASE_BEAM = new TurretChassis("l_base_beam", "Beam",
            Size.LARGE, ChassisType.BEAM,
            new ChassisPropsBeam(26, 3000, 5, 3, 147, 147,
                    5000, 6, 10, 39),
            Stream.of(ARG_L_BEAM, TEL_L_BEAM, PAR_L_BEAM, KHA_L_BEAM, SPL_L_BEAM, TER_L_BEAM)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_PULSE_BEAM = new TurretChassis("l_pulse_beam", "Pulse beam",
            Size.LARGE, ChassisType.BEAM,
            new ChassisPropsBeam(26, 3000, 2, 0.75, 250, 250,
                    5000, 6, 10, 39),
            Stream.of(ARG_L_BEAM, TEL_L_BEAM, PAR_L_BEAM, KHA_L_BEAM, SPL_L_BEAM, TER_L_BEAM)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());


    private TurretChassisInstances() {
    }

    public static class Properties {
        public static final PropertyDefinition DAMAGE_HULL = new PropertyDefinition(PropertyNames.DAMAGE_HULL, "Damage (hull)",
                "The amount of damage each bullet deals to unshielded target.", false,
                DAMAGE.name(), 0.0, null, "MJ", 0, ADVANCED);
        public static final PropertyDefinition DAMAGE_SHIELD = new PropertyDefinition(PropertyNames.DAMAGE_SHIELD, "Damage (shield)",
                "The amount of damage each bullet deals to shielded target.", false,
                DAMAGE.name(), 0.0, null, "MJ", 0, ADVANCED);
        public static final PropertyDefinition MAX_HITS = new PropertyDefinition(PropertyNames.MAX_HITS, "Max hits",
                "The amount of target if the bullet ricochets.", false,
                BULLET.name(), 1.0, null, null, 0, ADVANCED);
        public static final PropertyDefinition RICOCHET = new PropertyDefinition(PropertyNames.RICOCHET, "Ricochet chance",
                "The ricochet chance when a target is hit.", false,
                BULLET.name(), 0.0, 1.0, null, 2, ADVANCED);
        public static final PropertyDefinition LIFE_TIME = new PropertyDefinition(PropertyNames.LIFE_TIME, "Life Time",
                "The amount of time each bullet exists before disappearing.", false,
                BULLET.name(), 0.1, null, "s", 2, ADVANCED);
        public static final PropertyDefinition SPEED = new PropertyDefinition(PropertyNames.SPEED, "Speed",
                "The speed of the bullet.", false,
                BULLET.name(), 1.0, null, "m/s", 0, ADVANCED);
        public static final PropertyDefinition FIRE_RATE = new PropertyDefinition(PropertyNames.FIRE_RATE, "Fire Rate",
                "The rate at which the turret fires bullets.", false,
                TURRET.name(), 0.01, null, "shot/s", 2, ADVANCED);
        public static final PropertyDefinition RELOAD_TIME = new PropertyDefinition(PropertyNames.RELOAD_TIME, "Reload Time",
                "The time it takes to reload the turret.", true,
                TURRET.name(), 0.1, null, "s", 2, ADVANCED);
        public static final PropertyDefinition AMOUNT = new PropertyDefinition(PropertyNames.AMOUNT, "Amount",
                "The amount of bullets fired per shot.", false,
                TURRET.name(), 1.0, null, "bullet/s", 0, ADVANCED);
        public static final PropertyDefinition BARREL_AMOUNT = new PropertyDefinition(PropertyNames.BARREL_AMOUNT, "Barrel Amount",
                "The amount of barrels the turret has.", false,
                TURRET.name(), 1.0, null, "bullet/shot", 0, ADVANCED);
        public static final PropertyDefinition HULL =
                new PropertyDefinition(PropertyNames.HULL, "Hull",
                        "The amount of health the turret has.", false,
                        TURRET.name(), 1.0, null, null, 0, BASIC);
        public static final PropertyDefinition ROTATION_SPEED =
                new PropertyDefinition(PropertyNames.ROTATION_SPEED, "Rotation Speed",
                        "The speed at which the turret rotates.", false,
                        TURRET.name(), 1.0, null, "°/s", 0, BASIC);
        public static final PropertyDefinition ROTATION_ACCELERATION =
                new PropertyDefinition(PropertyNames.ROTATION_ACCELERATION, "Rotation Acceleration",
                        "The acceleration at which the turret rotates.", false,
                        TURRET.name(), 1.0, null, "°/s²", 0, ADVANCED);
        public static final PropertyDefinition ACCURACY =
                new PropertyDefinition(PropertyNames.ACCURACY, "Accuracy",
                        "The accuracy of the turret.", true,
                        TURRET.name(), 0.01, 3.0, "°", 2, BASIC);

        public static final PropertyDefinition RANGE =
                new PropertyDefinition(PropertyNames.RANGE, "Range",
                        "The bullet range.", false,
                        BULLET.name(), 1.0, null, "m", 0, BASIC);
        public static final PropertyDefinition BEAM_RANGE =
                new PropertyDefinition(PropertyNames.BEAM_RANGE, "Range",
                        "The beam range.", false,
                        BULLET.name(), 1.0, null, "m", 0, BASIC);
        public static final PropertyDefinition BURST_TIME =
                new PropertyDefinition(PropertyNames.BURST_TIME, "Burst Time",
                        "Time between two burst.", true,
                        TURRET.name(), 0.1, null, "s", 2, ADVANCED);
        public static final PropertyDefinition SHOOT_PER_SECOND =
                new PropertyDefinition(PropertyNames.SHOOT_PER_SECOND, "Shoot Per Second",
                        "The amount of shoot fired per second.", false,
                        TURRET.name(), null, null, "shoot/s", 2, BASIC);
        public static final PropertyDefinition DAMAGE_PER_SECOND_HULL =
                new PropertyDefinition(PropertyNames.DAMAGE_PER_SECOND_HULL, "Damage Per Second (hull)",
                        "The amount of damage dealt per second to unshielded target.", false,
                        DAMAGE.name(), null, null, "MJ/s", 0, BASIC);
        public static final PropertyDefinition DAMAGE_PER_SECOND_SHIELD =
                new PropertyDefinition(PropertyNames.DAMAGE_PER_SECOND_SHIELD, "Damage Per Second (shield)",
                        "The amount of damage dealt per second to shielded target.", false,
                        DAMAGE.name(), null, null, "MJ/s", 0, BASIC);
        public static final PropertyDefinition TIME_DIFF =
                new PropertyDefinition(PropertyNames.TIME_DIFF, "Time between bullet",
                        "Time between two bullet", true,
                        TURRET.name(), 0.00001, null, "s", 2, ADVANCED);
        public static final PropertyDefinition DAMAGE_BONUS_SHIELD =
                new PropertyDefinition(PropertyNames.DAMAGE_BONUS_SHIELD, "Damage Bonus (shield)",
                        "The bonus damage dealt to shielded target.", false,
                        DAMAGE.name(), 0.0, null, "MJ", 0, ADVANCED);
        // Cost
        public static final PropertyDefinition COST_CW_ADVANCED_ELECTRONICS =
                new PropertyDefinition(PropertyNames.COST_CW_ADVANCED_ELECTRONICS, "Common-wealth - Advanced Electronics",
                        "The advanced electronics cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_CW_ENERGY_CELLS =
                new PropertyDefinition(PropertyNames.COST_CW_ENERGY_CELLS, "Common-wealth - Energy Cells",
                        "The energy cells cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_CW_TURRET_COMPS =
                new PropertyDefinition(PropertyNames.COST_CW_TURRET_COMPS, "Common-wealth - Turret Comps",
                        "The turret comps cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_CL_ENERGY_CELLS =
                new PropertyDefinition(PropertyNames.COST_CL_ENERGY_CELLS, "Closed loop - Energy Cells",
                        "The energy cells cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_CL_CLAYTRONICS =
                new PropertyDefinition(PropertyNames.COST_CL_CLAYTRONICS, "Closed loop - Claytronics",
                        "The claytronics cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_CL_HULL_PART =
                new PropertyDefinition(PropertyNames.COST_CL_HULL_PART, "Closed loop - Hull part",
                        "The hull part cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_TER_ENERGY_CELLS =
                new PropertyDefinition(PropertyNames.COST_TER_ENERGY_CELLS, "Terran - Energy Cells",
                        "The energy cells cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_TER_COMPUTRONIC =
                new PropertyDefinition(PropertyNames.COST_TER_COMPUTRONIC, "Terran - Computronic Substrate",
                        "The computronic substrate cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_TER_MICROLATICE =
                new PropertyDefinition(PropertyNames.COST_TER_MICROLATICE, "Terran - Metallic Micro lattice",
                        "The metallic micro lattice cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);
        public static final PropertyDefinition COST_TER_CARBIDE =
                new PropertyDefinition(PropertyNames.COST_TER_CARBIDE, "Terran - Silicon Carbide",
                        "The silicon carbide cost of the turret.", true,
                        CategoryInstances.COST.name(), 0.0, null, null, 0, BASIC);

        Properties() {
        }
    }

    public static class PropertyNames {
        //Base
        public static PropertyName ROTATION_SPEED = new PropertyName("rotationSpeed");
        public static PropertyName HULL = new PropertyName("hull");
        public static PropertyName BARREL_AMOUNT = new PropertyName("barrelAmount");
        public static PropertyName AMOUNT = new PropertyName("amount");
        public static PropertyName RELOAD_TIME = new PropertyName("reloadTime");
        public static PropertyName FIRE_RATE = new PropertyName("fireRate");
        public static PropertyName SPEED = new PropertyName("speed");
        public static PropertyName LIFE_TIME = new PropertyName("lifeTime");
        public static PropertyName DAMAGE_HULL = new PropertyName("damageHull");
        public static PropertyName DAMAGE_SHIELD = new PropertyName("damageShield");
        public static PropertyName MAX_HITS = new PropertyName("maxHits");
        public static PropertyName RICOCHET = new PropertyName("ricochet");
        public static PropertyName ACCURACY = new PropertyName("accuracy");
        public static PropertyName COST_CW_ADVANCED_ELECTRONICS = new PropertyName("costCwAdvancedElectronics");
        public static PropertyName COST_CW_ENERGY_CELLS = new PropertyName("costCwEnergyCells");
        public static PropertyName COST_CW_TURRET_COMPS = new PropertyName("costCwTurretComps");
        public static PropertyName TIME_DIFF = new PropertyName("timeDiff");
        public static PropertyName BEAM_RANGE = new PropertyName("beamRange");
        // Computed
        public static PropertyName RANGE = new PropertyName("range");
        public static PropertyName BURST_TIME = new PropertyName("burstTime");
        public static PropertyName SHOOT_PER_SECOND = new PropertyName("shootPerSecond");
        public static PropertyName DAMAGE_PER_SECOND_HULL = new PropertyName("damagePerSecondHull");
        public static PropertyName DAMAGE_PER_SECOND_SHIELD = new PropertyName("damagePerSecondShield");
        public static PropertyName DAMAGE_BONUS_SHIELD = new PropertyName("damageBonusShield");
        public static PropertyName ROTATION_ACCELERATION = new PropertyName("rotationAcceleration");
        // Computed cost
        public static PropertyName COST_CL_ENERGY_CELLS = new PropertyName("costClEnergyCells");
        public static PropertyName COST_CL_CLAYTRONICS = new PropertyName("costClClaytronics");
        public static PropertyName COST_CL_HULL_PART = new PropertyName("costClHullPart");
        public static PropertyName COST_TER_ENERGY_CELLS = new PropertyName("costTerEnergyCells");
        public static PropertyName COST_TER_COMPUTRONIC = new PropertyName("costTerComputronic");
        public static PropertyName COST_TER_MICROLATICE = new PropertyName("costTerMicrolattice");
        public static PropertyName COST_TER_CARBIDE = new PropertyName("costTerCarbide");

        PropertyNames() {
        }
    }
}
