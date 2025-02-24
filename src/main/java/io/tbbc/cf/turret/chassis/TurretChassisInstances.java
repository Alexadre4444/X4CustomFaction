package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.common.Size;
import io.tbbc.cf.property.CategoryInstances;
import io.tbbc.cf.property.PropertyDefinition;
import io.tbbc.cf.property.PropertyName;
import io.tbbc.cf.turret.chassis.skin.ChassisSkin;

import java.math.BigDecimal;
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
            new ChassisPropsRaffle(
                    new BigDecimal(140), new BigDecimal(660), new BigDecimal(2), new BigDecimal(3),
                    new BigDecimal("0.5"), new BigDecimal(6), new BigDecimal(1855), new BigDecimal("1.844"),
                    new BigDecimal(12), new BigDecimal(12), new BigDecimal(1), new BigDecimal(0),
                    new BigDecimal("0.019"), new BigDecimal("0.32"), new BigDecimal(1), new BigDecimal(5),
                    new BigDecimal(10)),
            Stream.of(ARG_M_PULSE, TEL_M_PULSE, PAR_M_PULSE, XEN_M_PULSE, SPL_M_PULSE, TER_M_PULSE)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_GATLING = new TurretChassis("m_gatling", "Bolt",
            Size.MEDIUM, ChassisType.RAFFLE,
            new ChassisPropsRaffle(new BigDecimal(120), new BigDecimal(660), new BigDecimal(2), new BigDecimal(15),
                    new BigDecimal("1.3"), new BigDecimal(10), new BigDecimal(895), new BigDecimal("3.8"),
                    new BigDecimal(11), new BigDecimal(11), new BigDecimal(2), new BigDecimal("0.01"),
                    new BigDecimal("0.032"), new BigDecimal("0.49"), new BigDecimal(1), new BigDecimal(6),
                    new BigDecimal(13)),
            Stream.of(ARG_M_GATLING, TEL_M_GATLING, PAR_M_GATLING, SPL_M_GATLING, TER_M_GATLING)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_PLASMA = new TurretChassis("m_plasma", "Plasma",
            Size.MEDIUM, ChassisType.STANDARD,
            new ChassisPropsStandard(
                    new BigDecimal(50), new BigDecimal(660), new BigDecimal(2), new BigDecimal(1), new BigDecimal("4.2"),
                    new BigDecimal(610), new BigDecimal("6.2"), new BigDecimal(570), new BigDecimal(570), new BigDecimal(1),
                    new BigDecimal("0.0"), new BigDecimal(0), new BigDecimal("0.01"), new BigDecimal(2),
                    new BigDecimal(8), new BigDecimal(10)),
            Stream.of(ARG_M_PLASMA, TEL_M_PLASMA, PAR_M_PLASMA, SPL_M_PLASMA)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_SHOTGUN = new TurretChassis("m_shotgun", "Shard",
            Size.MEDIUM, ChassisType.STANDARD,
            new ChassisPropsStandard(
                    new BigDecimal(108), new BigDecimal(660), new BigDecimal(2), new BigDecimal(5), new BigDecimal("1.2"),
                    new BigDecimal(1070), new BigDecimal("2.2"), new BigDecimal(32), new BigDecimal(32), new BigDecimal(3),
                    new BigDecimal("0.5"), new BigDecimal("0.003"), new BigDecimal("1.10"), new BigDecimal(2), new BigDecimal(6), new BigDecimal(9)),
            Stream.of(ARG_M_SHOTGUN, TEL_M_SHOTGUN, PAR_M_SHOTGUN, SPL_M_SHOTGUN)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_BEAM = new TurretChassis("m_beam", "Beam",
            Size.MEDIUM, ChassisType.BEAM,
            new ChassisPropsBeam(
                    new BigDecimal(80), new BigDecimal(660), new BigDecimal(3), new BigDecimal(1), new BigDecimal(72), new BigDecimal(72),
                    new BigDecimal(2550), new BigDecimal(6), new BigDecimal(10), new BigDecimal(10)),
            Stream.of(ARG_M_BEAM, TEL_M_BEAM, PAR_M_BEAM, XEN_M_BEAM, SPL_M_BEAM, TER_M_BEAM)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_ELECTROMAGNETIC = new TurretChassis("m_electromagnetic", "Electromagnetic",
            Size.MEDIUM, ChassisType.STANDARD,
            new ChassisPropsStandard(new BigDecimal(120), new BigDecimal(800), new BigDecimal(1), new BigDecimal(1), new BigDecimal("0.8"),
                    new BigDecimal(2000), new BigDecimal("1.8"), new BigDecimal(165), new BigDecimal(165), new BigDecimal(1), new BigDecimal("0.0"), new BigDecimal("0.003"), new BigDecimal("0.06"), new BigDecimal(2),
                    new BigDecimal(10), new BigDecimal(30)),
            Stream.of(TER_M_ELECTROMAGNETIC)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_FLAK = new TurretChassis("m_flak", "Flak",
            Size.MEDIUM, ChassisType.RAFFLE,
            new ChassisPropsRaffle(
                    new BigDecimal(140), new BigDecimal(660), new BigDecimal(1), new BigDecimal(3),
                    new BigDecimal("0.3"), new BigDecimal(3), new BigDecimal(1660), new BigDecimal(2), new BigDecimal(50), new BigDecimal(50), new BigDecimal(1), new BigDecimal(0),
                    new BigDecimal("0.005"), new BigDecimal("0.09"), new BigDecimal(5), new BigDecimal(18), new BigDecimal(21)),
            Stream.of(ARG_M_FLAK, SPL_M_FLAK)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis M_NEEDLER = new TurretChassis("m_needler", "Needler",
            Size.MEDIUM, ChassisType.RAFFLE,
            new ChassisPropsRaffle(
                    new BigDecimal(60), new BigDecimal(800), new BigDecimal(1), new BigDecimal(8),
                    new BigDecimal("0.5"), new BigDecimal(4), new BigDecimal(2500), new BigDecimal("1.3"), new BigDecimal(50), new BigDecimal(50), new BigDecimal(1),
                    new BigDecimal(0), new BigDecimal("0.1"),
                    new BigDecimal("0.21"), new BigDecimal(10), new BigDecimal(20), new BigDecimal(33)),
            Stream.of(XEN_M_NEEDLER)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());

    // Large
    public static final TurretChassis L_PULSE = new TurretChassis("l_pulse", "Pulse",
            Size.LARGE, ChassisType.STANDARD,
            new ChassisPropsStandard(
                    new BigDecimal(30), new BigDecimal(3000), new BigDecimal(2), new BigDecimal(1),
                    new BigDecimal(2), new BigDecimal(2041), new BigDecimal("3.219"), new BigDecimal(367), new BigDecimal(367), new BigDecimal(1), new BigDecimal(0),
                    new BigDecimal("0.015"), new BigDecimal("0.025"), new BigDecimal(4), new BigDecimal(10), new BigDecimal(20)),
            Stream.of(ARG_L_PULSE, TEL_L_PULSE, PAR_L_PULSE, XEN_L_PULSE, KHA_L_PULSE, SPL_L_PULSE, TER_L_PULSE)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_GATLING = new TurretChassis("l_gatling", "Bolt",
            Size.LARGE, ChassisType.RAFFLE,
            new ChassisPropsRaffle(
                    new BigDecimal(30), new BigDecimal(3000), new BigDecimal(2), new BigDecimal(4),
                    new BigDecimal(3), new BigDecimal(2), new BigDecimal(2500), new BigDecimal("2.5"), new BigDecimal(250), new BigDecimal(250), new BigDecimal(1), new BigDecimal(0),
                    new BigDecimal("0.015"), new BigDecimal("0.1"), new BigDecimal(4), new BigDecimal(10), new BigDecimal(20)),
            Stream.of(ARG_L_PULSE, TEL_L_PULSE, PAR_L_PULSE, XEN_L_PULSE, KHA_L_PULSE, SPL_L_PULSE, TER_L_GATLING)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_PLASMA = new TurretChassis("l_plasma", "Plasma",
            Size.LARGE, ChassisType.STANDARD,
            new ChassisPropsStandard(
                    new BigDecimal(33), new BigDecimal(3000), new BigDecimal(2), new BigDecimal(1), new BigDecimal("4.2"),
                    new BigDecimal(537), new BigDecimal(14), new BigDecimal(2000), new BigDecimal(2000), new BigDecimal(1),
                    new BigDecimal("0.0"), new BigDecimal(0), new BigDecimal("0.0"), new BigDecimal(5),
                    new BigDecimal(8), new BigDecimal(32)),
            Stream.of(ARG_L_PLASMA, TEL_L_PLASMA, PAR_L_PLASMA, SPL_L_PLASMA, XEN_L_PULSE)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_BASE_BEAM = new TurretChassis("l_base_beam", "Beam",
            Size.LARGE, ChassisType.BEAM,
            new ChassisPropsBeam(
                    new BigDecimal(26), new BigDecimal(3000), new BigDecimal(5), new BigDecimal(3), new BigDecimal(147), new BigDecimal(147),
                    new BigDecimal(5000), new BigDecimal(6), new BigDecimal(10), new BigDecimal(39)),
            Stream.of(ARG_L_BEAM, TEL_L_BEAM, PAR_L_BEAM, KHA_L_BEAM, SPL_L_BEAM, TER_L_BEAM)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_PULSE_BEAM = new TurretChassis("l_pulse_beam", "Pulse beam",
            Size.LARGE, ChassisType.BEAM,
            new ChassisPropsBeam(
                    new BigDecimal(26), new BigDecimal(3000), new BigDecimal(2), new BigDecimal("0.75"), new BigDecimal(250), new BigDecimal(250),
                    new BigDecimal(5000), new BigDecimal(6), new BigDecimal(10), new BigDecimal(39)),
            Stream.of(ARG_L_BEAM, TEL_L_BEAM, PAR_L_BEAM, KHA_L_BEAM, SPL_L_BEAM, TER_L_BEAM)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());
    public static final TurretChassis L_SEISMIC = new TurretChassis("l_seismic", "Seismic",
            Size.LARGE, ChassisType.STANDARD,
            new ChassisPropsStandard(
                    new BigDecimal(25), new BigDecimal(3500), new BigDecimal(1), new BigDecimal(1), new BigDecimal(4),
                    new BigDecimal(1100), new BigDecimal(5), new BigDecimal(3500), new BigDecimal(3500), new BigDecimal(1), new BigDecimal(0),
                    new BigDecimal("0.001"), new BigDecimal("0.15"), new BigDecimal(20), new BigDecimal(50), new BigDecimal(33)),
            Stream.of(XEN_L_SEISMIC)
                    .sorted(Comparator.comparing(ChassisSkin::label)).toList());


    private TurretChassisInstances() {
    }

    public static class Properties {
        public static final PropertyDefinition DAMAGE_HULL = new PropertyDefinition(PropertyNames.DAMAGE_HULL_NAME, "Damage (hull)",
                "The amount of damage each bullet deals to unshielded target.", false,
                DAMAGE.name(), new BigDecimal(0), null, "MJ", 0, ADVANCED, true);
        public static final PropertyDefinition DAMAGE_SHIELD = new PropertyDefinition(PropertyNames.DAMAGE_SHIELD_NAME, "Damage (shield)",
                "The amount of damage each bullet deals to shielded target.", false,
                DAMAGE.name(), new BigDecimal(0), null, "MJ", 0, ADVANCED, true);
        public static final PropertyDefinition MAX_HITS = new PropertyDefinition(PropertyNames.MAX_HITS_NAME, "Max hits",
                "The amount of target if the bullet ricochets.", false,
                BULLET.name(), new BigDecimal(1), null, null, 0, ADVANCED, false);
        public static final PropertyDefinition RICOCHET = new PropertyDefinition(PropertyNames.RICOCHET_NAME, "Ricochet chance",
                "The ricochet chance when a target is hit.", false,
                BULLET.name(), new BigDecimal(0), new BigDecimal(1), null, 2, ADVANCED, false);
        public static final PropertyDefinition LIFE_TIME = new PropertyDefinition(PropertyNames.LIFE_TIME_NAME, "Life Time",
                "The amount of time each bullet exists before disappearing.", false,
                BULLET.name(), new BigDecimal("0.1"), null, "s", 2, ADVANCED, true);
        public static final PropertyDefinition SPEED = new PropertyDefinition(PropertyNames.SPEED_NAME, "Speed",
                "The speed of the bullet.", false,
                BULLET.name(), new BigDecimal("1.0"), null, "m/s", 0, ADVANCED, true);
        public static final PropertyDefinition FIRE_RATE = new PropertyDefinition(PropertyNames.FIRE_RATE_NAME, "Fire Rate",
                "The rate at which the turret fires bullets.", false,
                TURRET.name(), new BigDecimal("0.01"), null, "shot/s", 2, ADVANCED, true);
        public static final PropertyDefinition RELOAD_TIME = new PropertyDefinition(PropertyNames.RELOAD_TIME_NAME, "Reload Time",
                "The time it takes to reload the turret.", true,
                TURRET.name(), new BigDecimal("0.1"), null, "s", 2, ADVANCED, true);
        public static final PropertyDefinition AMOUNT = new PropertyDefinition(PropertyNames.AMOUNT_NAME, "Amount",
                "The amount of bullets fired per shot.", false,
                TURRET.name(), new BigDecimal("1.0"), null, "bullet/s", 0, ADVANCED, true);
        public static final PropertyDefinition BARREL_AMOUNT = new PropertyDefinition(PropertyNames.BARREL_AMOUNT_NAME, "Barrel Amount",
                "The amount of barrels the turret has.", false,
                TURRET.name(), new BigDecimal("1.0"), null, "bullet/shot", 0, ADVANCED, false);
        public static final PropertyDefinition HULL =
                new PropertyDefinition(PropertyNames.HULL_NAME, "Hull",
                        "The amount of health the turret has.", false,
                        TURRET.name(), new BigDecimal("1.0"), null, null, 0, BASIC, true);
        public static final PropertyDefinition ROTATION_SPEED =
                new PropertyDefinition(PropertyNames.ROTATION_SPEED_NAME, "Rotation Speed",
                        "The speed at which the turret rotates.", false,
                        TURRET.name(), new BigDecimal("1.0"), null, "°/s", 0, BASIC, true);
        public static final PropertyDefinition ROTATION_ACCELERATION =
                new PropertyDefinition(PropertyNames.ROTATION_ACCELERATION_NAME, "Rotation Acceleration",
                        "The acceleration at which the turret rotates.", false,
                        TURRET.name(), new BigDecimal("1.0"), null, "°/s²", 0, ADVANCED, false);
        public static final PropertyDefinition ACCURACY =
                new PropertyDefinition(PropertyNames.ACCURACY_NAME, "Accuracy",
                        "The accuracy of the turret.", true,
                        TURRET.name(), new BigDecimal("0.01"), new BigDecimal("3.0"), "°", 2, BASIC, true);

        public static final PropertyDefinition RANGE =
                new PropertyDefinition(PropertyNames.RANGE_NAME, "Range",
                        "The bullet range.", false,
                        BULLET.name(), new BigDecimal("1.0"), null, "m", 0, BASIC, false);
        public static final PropertyDefinition BEAM_RANGE =
                new PropertyDefinition(PropertyNames.BEAM_RANGE_NAME, "Range",
                        "The beam range.", false,
                        BULLET.name(), new BigDecimal("1.0"), null, "m", 0, BASIC, true);
        public static final PropertyDefinition BURST_TIME =
                new PropertyDefinition(PropertyNames.BURST_TIME_NAME, "Burst Time",
                        "Time between two burst.", true,
                        TURRET.name(), new BigDecimal("0.1"), null, "s", 2, ADVANCED, false);
        public static final PropertyDefinition SHOOT_PER_SECOND =
                new PropertyDefinition(PropertyNames.SHOOT_PER_SECOND_NAME, "Shoot Per Second",
                        "The amount of shoot fired per second.", false,
                        TURRET.name(), null, null, "shoot/s", 2, BASIC, false);
        public static final PropertyDefinition DAMAGE_PER_SECOND_HULL =
                new PropertyDefinition(PropertyNames.DAMAGE_PER_SECOND_HULL_NAME, "Damage Per Second (hull)",
                        "The amount of damage dealt per second to unshielded target.", false,
                        DAMAGE.name(), null, null, "MJ/s", 0, BASIC, false);
        public static final PropertyDefinition DAMAGE_PER_SECOND_SHIELD =
                new PropertyDefinition(PropertyNames.DAMAGE_PER_SECOND_SHIELD_NAME, "Damage Per Second (shield)",
                        "The amount of damage dealt per second to shielded target.", false,
                        DAMAGE.name(), null, null, "MJ/s", 0, BASIC, false);
        public static final PropertyDefinition TIME_DIFF =
                new PropertyDefinition(PropertyNames.TIME_DIFF_NAME, "Time between bullet",
                        "Time between two bullet", true,
                        TURRET.name(), new BigDecimal("0.00001"), null, "s", 2, ADVANCED, false);
        public static final PropertyDefinition DAMAGE_BONUS_SHIELD =
                new PropertyDefinition(PropertyNames.DAMAGE_BONUS_SHIELD_NAME, "Damage Bonus (shield)",
                        "The bonus damage dealt to shielded target.", false,
                        DAMAGE.name(), new BigDecimal("0.0"), null, "MJ", 0, ADVANCED, false);
        // Cost
        public static final PropertyDefinition COST_CW_ADVANCED_ELECTRONICS =
                new PropertyDefinition(PropertyNames.COST_CW_ADVANCED_ELECTRONICS_NAME, "Common-wealth - Advanced Electronics",
                        "The advanced electronics cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_CW_ENERGY_CELLS =
                new PropertyDefinition(PropertyNames.COST_CW_ENERGY_CELLS_NAME, "Common-wealth - Energy Cells",
                        "The energy cells cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_CW_TURRET_COMPS =
                new PropertyDefinition(PropertyNames.COST_CW_TURRET_COMPS_NAME, "Common-wealth - Turret Comps",
                        "The turret comps cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_CL_ENERGY_CELLS =
                new PropertyDefinition(PropertyNames.COST_CL_ENERGY_CELLS_NAME, "Closed loop - Energy Cells",
                        "The energy cells cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_CL_CLAYTRONICS =
                new PropertyDefinition(PropertyNames.COST_CL_CLAYTRONICS_NAME, "Closed loop - Claytronics",
                        "The claytronics cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_CL_HULL_PART =
                new PropertyDefinition(PropertyNames.COST_CL_HULL_PART_NAME, "Closed loop - Hull part",
                        "The hull part cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_TER_ENERGY_CELLS =
                new PropertyDefinition(PropertyNames.COST_TER_ENERGY_CELLS_NAME, "Terran - Energy Cells",
                        "The energy cells cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_TER_COMPUTRONIC =
                new PropertyDefinition(PropertyNames.COST_TER_COMPUTRONIC_NAME, "Terran - Computronic Substrate",
                        "The computronic substrate cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_TER_MICROLATICE =
                new PropertyDefinition(PropertyNames.COST_TER_MICROLATICE_NAME, "Terran - Metallic Micro lattice",
                        "The metallic micro lattice cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        public static final PropertyDefinition COST_TER_CARBIDE =
                new PropertyDefinition(PropertyNames.COST_TER_CARBIDE_NAME, "Terran - Silicon Carbide",
                        "The silicon carbide cost of the turret.", true,
                        CategoryInstances.COST.name(), new BigDecimal("0.0"), null, null, 0, BASIC, false);
        // Bullet effect
        public static final PropertyDefinition SELF_DESTRUCT =
                new PropertyDefinition(PropertyNames.SELF_DESTRUCT_NAME, "Self destruct",
                        "The bullet self destruct", false,
                        BULLET.name(), new BigDecimal(0), new BigDecimal(1), "boolean", 0, ADVANCED, false);
        public static final PropertyDefinition AREA_DAMAGE_HULL =
                new PropertyDefinition(PropertyNames.AREA_DAMAGE_HULL_NAME, "Area damage (hull)",
                        "The amount of damage dealt to unshielded target in the area.", false,
                        DAMAGE.name(), new BigDecimal(0), null, "MJ", 0, BASIC, false);
        public static final PropertyDefinition AREA_DAMAGE_SHIELD =
                new PropertyDefinition(PropertyNames.AREA_DAMAGE_SHIELD_NAME, "Area damage (shield)",
                        "The amount of damage dealt to shielded target in the area.", false,
                        DAMAGE.name(), new BigDecimal(0), null, "MJ", 0, BASIC, false);
        public static final PropertyDefinition AREA_DAMAGE_BONUS_SHIELD =
                new PropertyDefinition(PropertyNames.AREA_DAMAGE_BONUS_SHIELD_NAME, "Area damage bonus (shield)",
                        "The bonus damage dealt to shielded target in the area.", false,
                        DAMAGE.name(), new BigDecimal(0), null, "MJ", 0, ADVANCED, false);
        public static final PropertyDefinition AREA_TIME =
                new PropertyDefinition(PropertyNames.AREA_TIME_NAME, "Area time",
                        "The time the area effect last.", false,
                        BULLET.name(), new BigDecimal("0.1"), null, "s", 2, ADVANCED, false);
        public static final PropertyDefinition AREA_LIFE_TIME =
                new PropertyDefinition(PropertyNames.AREA_LIFE_TIME_NAME, "Area life time",
                        "The time the area effect last.", false,
                        BULLET.name(), new BigDecimal("0.1"), null, "s", 2, ADVANCED, false);
        public static final PropertyDefinition PLANNED_SELF_DESTRUCT =
                new PropertyDefinition(PropertyNames.PLANNED_SELF_DESTRUCT_NAME, "Self destruct",
                        "The bullet self destruct", false,
                        BULLET.name(), new BigDecimal(0), new BigDecimal(1), "boolean", 0, ADVANCED, false);
        public static final PropertyDefinition SELF_DESTRUCT_TIME_DIFF =
                new PropertyDefinition(PropertyNames.SELF_DESTRUCT_TIME_DIFF_NAME, "Self destruct time diff",
                        "Random time at self destruct time... Maybe?", false,
                        BULLET.name(), new BigDecimal("0.00001"), null, "s", 2, ADVANCED, false);
        public static final PropertyDefinition SELF_DESTRUCT_MIN_TIME =
                new PropertyDefinition(PropertyNames.SELF_DESTRUCT_MIN_TIME_NAME, "Self destruct min time",
                        "Minimum time between bullet explosion.", false,
                        BULLET.name(), new BigDecimal("0.1"), null, "s", 2, ADVANCED, false);

        Properties() {
        }
    }

    public static class PropertyNames {
        //Base
        public static PropertyName ROTATION_SPEED_NAME = new PropertyName("rotationSpeed");
        public static PropertyName HULL_NAME = new PropertyName("hull");
        public static PropertyName BARREL_AMOUNT_NAME = new PropertyName("barrelAmount");
        public static PropertyName AMOUNT_NAME = new PropertyName("amount");
        public static PropertyName RELOAD_TIME_NAME = new PropertyName("reloadTime");
        public static PropertyName FIRE_RATE_NAME = new PropertyName("fireRate");
        public static PropertyName SPEED_NAME = new PropertyName("speed");
        public static PropertyName LIFE_TIME_NAME = new PropertyName("lifeTime");
        public static PropertyName DAMAGE_HULL_NAME = new PropertyName("damageHull");
        public static PropertyName DAMAGE_SHIELD_NAME = new PropertyName("damageShield");
        public static PropertyName MAX_HITS_NAME = new PropertyName("maxHits");
        public static PropertyName RICOCHET_NAME = new PropertyName("ricochet");
        public static PropertyName ACCURACY_NAME = new PropertyName("accuracy");
        public static PropertyName COST_CW_ADVANCED_ELECTRONICS_NAME = new PropertyName("costCwAdvancedElectronics");
        public static PropertyName COST_CW_ENERGY_CELLS_NAME = new PropertyName("costCwEnergyCells");
        public static PropertyName COST_CW_TURRET_COMPS_NAME = new PropertyName("costCwTurretComps");
        public static PropertyName TIME_DIFF_NAME = new PropertyName("timeDiff");
        public static PropertyName BEAM_RANGE_NAME = new PropertyName("beamRange");
        // Computed
        public static PropertyName RANGE_NAME = new PropertyName("range");
        public static PropertyName BURST_TIME_NAME = new PropertyName("burstTime");
        public static PropertyName SHOOT_PER_SECOND_NAME = new PropertyName("shootPerSecond");
        public static PropertyName DAMAGE_PER_SECOND_HULL_NAME = new PropertyName("damagePerSecondHull");
        public static PropertyName DAMAGE_PER_SECOND_SHIELD_NAME = new PropertyName("damagePerSecondShield");
        public static PropertyName DAMAGE_BONUS_SHIELD_NAME = new PropertyName("damageBonusShield");
        public static PropertyName ROTATION_ACCELERATION_NAME = new PropertyName("rotationAcceleration");
        // Computed cost
        public static PropertyName COST_CL_ENERGY_CELLS_NAME = new PropertyName("costClEnergyCells");
        public static PropertyName COST_CL_CLAYTRONICS_NAME = new PropertyName("costClClaytronics");
        public static PropertyName COST_CL_HULL_PART_NAME = new PropertyName("costClHullPart");
        public static PropertyName COST_TER_ENERGY_CELLS_NAME = new PropertyName("costTerEnergyCells");
        public static PropertyName COST_TER_COMPUTRONIC_NAME = new PropertyName("costTerComputronic");
        public static PropertyName COST_TER_MICROLATICE_NAME = new PropertyName("costTerMicrolattice");
        public static PropertyName COST_TER_CARBIDE_NAME = new PropertyName("costTerCarbide");

        // Bullet effect
        public static PropertyName SELF_DESTRUCT_NAME = new PropertyName("selfDestruct");
        public static PropertyName AREA_DAMAGE_HULL_NAME = new PropertyName("areaDamageHull");
        public static PropertyName AREA_DAMAGE_SHIELD_NAME = new PropertyName("areaDamageShield");
        public static PropertyName AREA_DAMAGE_BONUS_SHIELD_NAME = new PropertyName("areaDamageBonusShield");
        public static PropertyName AREA_TIME_NAME = new PropertyName("areaTime");
        public static PropertyName AREA_LIFE_TIME_NAME = new PropertyName("areaLifeTime");
        public static PropertyName PLANNED_SELF_DESTRUCT_NAME = new PropertyName("plannedSelfDestruct");
        public static PropertyName SELF_DESTRUCT_TIME_DIFF_NAME = new PropertyName("selfdestructtimediff");
        public static PropertyName SELF_DESTRUCT_MIN_TIME_NAME = new PropertyName("selfdestructmintime");
    }
}
