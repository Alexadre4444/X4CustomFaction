package io.tbbc.cf.bullet;

import io.tbbc.cf.bullet.effect.BoronDisruptorEffect;
import io.tbbc.cf.bullet.effect.BoronFlakEffect;
import io.tbbc.cf.bullet.effect.FlakEffect;
import io.tbbc.cf.bullet.effect.SeismicEffect;
import io.tbbc.cf.bullet.skin.BulletSkin;
import io.tbbc.cf.common.Size;
import io.tbbc.cf.modifier.Modifier;
import io.tbbc.cf.modifier.Modifiers;
import io.tbbc.cf.turret.chassis.ChassisType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.bullet.influence.InfluenceInstances.*;
import static io.tbbc.cf.bullet.skin.BulletSkinInstances.*;
import static io.tbbc.cf.property.CategoryInstances.COST;
import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class BulletInstances {
    // Medium
    public static final Bullet M_PULSE = new Bullet("m_cw_pulse", "Pulse",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of()), List.of(M_PULSE_BLUE, M_PULSE_RED),
            List.of(), null);
    public static final Bullet M_PULSE_ION = new Bullet("m_cw_pulse_ion", "Ionic pulse",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(DAMAGE_SHIELD_NAME, 80),
                    new Modifier(DAMAGE_HULL_NAME, -60),
                    new Modifier(SPEED_NAME, 40),
                    new Modifier(LIFE_TIME_NAME, -40)
            )), List.of(M_PULSE_ION_BLUE, M_PULSE_BORON),
            List.of(), null);
    public static final Bullet M_PLASMA = new Bullet("m_plasma", "Plasma",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(ROTATION_SPEED_NAME, -20),
                    new Modifier(RELOAD_TIME_NAME, 10),
                    new Modifier(SPEED_NAME, -10),
                    new Modifier(LIFE_TIME_NAME, 30),
                    new Modifier(DAMAGE_HULL_NAME, 30),
                    new Modifier(DAMAGE_SHIELD_NAME, 30),
                    new Modifier(COST.name(), 20))),
            List.of(M_PLASMA_CW),
            List.of(), null);
    public static final Bullet M_GATLING = new Bullet("m_cw_gatling", "Bolt",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(RELOAD_TIME_NAME, -25),
                    new Modifier(SPEED_NAME, 20),
                    new Modifier(LIFE_TIME_NAME, -20),
                    new Modifier(ACCURACY_NAME, 10),
                    new Modifier(DAMAGE_HULL_NAME, -20),
                    new Modifier(DAMAGE_SHIELD_NAME, -20),
                    new Modifier(COST.name(), -10))),
            Stream.of(M_GATLING_CW, M_GATLING_XEN, M_GATLING_TER)
                    .sorted(Comparator.comparing(BulletSkin::label)).toList(),
            List.of(), null);
    public static final Bullet M_SHOTGUN = new Bullet("m_cw_shotgun", "Shard",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(SPEED_NAME, 20),
                    new Modifier(LIFE_TIME_NAME, -10),
                    new Modifier(ACCURACY_NAME, 10),
                    new Modifier(DAMAGE_HULL_NAME, -10),
                    new Modifier(DAMAGE_SHIELD_NAME, -10),
                    new Modifier(COST.name(), -10)
            )),
            List.of(M_SHOTGUN_CW),
            List.of(), null);
    public static final Bullet M_BEAM = new Bullet("m_cw_beam", "Beam",
            Size.MEDIUM, List.of(ChassisType.BEAM),
            new Modifiers(List.of()),
            Stream.of(M_BEAM_CW, M_BEAM_CW_MINING, M_BEAM_KHA, M_BEAM_TER)
                    .sorted(Comparator.comparing(BulletSkin::label)).toList(),
            List.of(), null);
    public static final Bullet M_ELECTROMAGNETIC = new Bullet("m_ter_electromagnetic", "Electromagnetic",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(ROTATION_SPEED_NAME, -10),
                    new Modifier(DAMAGE_SHIELD_NAME, 20),
                    new Modifier(DAMAGE_HULL_NAME, 20),
                    new Modifier(LIFE_TIME_NAME, 20),
                    new Modifier(SPEED_NAME, -10),
                    new Modifier(RELOAD_TIME_NAME, 10)
            )),
            List.of(M_ELECTROMAGNETIC_TER),
            List.of(), null);

    public static final Bullet M_FLAK = new Bullet("m_cw_flak", "Flak",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(SPEED_NAME, -10),
                    new Modifier(DAMAGE_HULL_NAME, 10),
                    new Modifier(DAMAGE_SHIELD_NAME, 10)
            )),
            List.of(M_FLAK_CW),
            List.of(new FlakEffect()), null);

    public static final Bullet M_ARC = new Bullet("m_bor_arc", "Arc",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(DAMAGE_HULL_NAME, -20),
                    new Modifier(DAMAGE_SHIELD_NAME, 20),
                    new Modifier(LIFE_TIME_NAME, -30),
                    new Modifier(SPEED_NAME, 30)
            )),
            List.of(M_ARC_BOR),
            List.of(), LIGHT_THRUSTER_DISRUPT);

    public static final Bullet M_PHASE = new Bullet("m_bor_phase", "Phase",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(DAMAGE_HULL_NAME, 10),
                    new Modifier(DAMAGE_SHIELD_NAME, -10)
            )),
            List.of(M_PHASE_BOR),
            List.of(), null);

    // Large
    public static final Bullet L_PULSE = new Bullet("l_pulse", "Pulse",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of()),
            Stream.of(L_PULSE_BLUE, L_PULSE_YELLOW, L_PULSE_VIOLET, L_PULSE_RED, L_PULSE_ORANGE, L_PULSE_WHITE)
                    .sorted(Comparator.comparing(BulletSkin::label)).toList(),
            List.of(), null);
    public static final Bullet L_GATLING = new Bullet("l_gatling", "Bolt",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(DAMAGE_HULL_NAME, 20),
                    new Modifier(DAMAGE_SHIELD_NAME, 20),
                    new Modifier(LIFE_TIME_NAME, 20),
                    new Modifier(SPEED_NAME, -20)
            )),
            Stream.of(L_GATLING_TER)
                    .sorted(Comparator.comparing(BulletSkin::label)).toList(),
            List.of(), null);
    public static final Bullet L_PLASMA = new Bullet("l_plasma", "Plasma",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(ROTATION_SPEED_NAME, -20),
                    new Modifier(RELOAD_TIME_NAME, 10),
                    new Modifier(SPEED_NAME, -10),
                    new Modifier(LIFE_TIME_NAME, 30),
                    new Modifier(DAMAGE_HULL_NAME, 30),
                    new Modifier(DAMAGE_SHIELD_NAME, 30),
                    new Modifier(COST.name(), 20))),
            Stream.of(L_PLASMA_BLUE, L_PLASMA_YELLOW, L_PLASMA_VIOLET, L_PLASMA_ORANGE)
                    .sorted(Comparator.comparing(BulletSkin::label)).toList(),
            List.of(), null);
    public static final Bullet L_BEAM = new Bullet("l_beam", "Beam",
            Size.LARGE, List.of(ChassisType.BEAM),
            new Modifiers(List.of()),
            Stream.of(L_BEAM_BLUE, L_BEAM_YELLOW, L_BEAM_VIOLET, L_BEAM_VIOLET_KHA, L_BEAM_ORANGE, L_BEAM_WHITE)
                    .sorted(Comparator.comparing(BulletSkin::label)).toList(),
            List.of(), null);
    public static final Bullet L_SEISMIC = new Bullet("l_seismic", "Seismic",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
            )),
            List.of(L_SEISMIC_XEN),
            List.of(new SeismicEffect()), null);
    public static final Bullet L_PHASE = new Bullet("l_bor_phase", "Phase",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(DAMAGE_HULL_NAME, 30),
                    new Modifier(DAMAGE_SHIELD_NAME, -30),
                    new Modifier(SPEED_NAME, 20),
                    new Modifier(LIFE_TIME_NAME, -10)
            )),
            List.of(L_PHASE_BOR),
            List.of(), null);
    public static final Bullet L_FLAK = new Bullet("l_bor_flak", "Ion Flak",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(SPEED_NAME, 50),
                    new Modifier(LIFE_TIME_NAME, -50),
                    new Modifier(DAMAGE_HULL_NAME, -80),
                    new Modifier(DAMAGE_SHIELD_NAME, -30)
            )),
            List.of(L_FLAK_BOR),
            List.of(new BoronFlakEffect()), LIGHT_DRIVE_DISRUPT_FLAK);
    public static final Bullet L_DISRUPTOR = new Bullet("l_disruptor", "Ion Net",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(LIFE_TIME_NAME, -40),
                    new Modifier(SPEED_NAME, 50),
                    new Modifier(DAMAGE_HULL_NAME, -30),
                    new Modifier(DAMAGE_SHIELD_NAME, 50),
                    new Modifier(ACCURACY_NAME, 50)
            )),
            List.of(L_DISRUPTOR_BOR),
            List.of(new BoronDisruptorEffect()), HEAVY_MAM_THRUSTER_DISRUPT);

    private BulletInstances() {
    }
}
