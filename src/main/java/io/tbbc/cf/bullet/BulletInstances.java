package io.tbbc.cf.bullet;

import io.tbbc.cf.common.Size;
import io.tbbc.cf.common.modifier.Modifier;
import io.tbbc.cf.common.modifier.Modifiers;
import io.tbbc.cf.turret.chassis.ChassisType;

import java.util.List;

import static io.tbbc.cf.bullet.skin.BulletSkinInstances.*;
import static io.tbbc.cf.common.property.CategoryInstances.COST;
import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class BulletInstances {
    // Medium
    public static final Bullet M_PULSE = new Bullet("m_cw_pulse", "Pulse laser",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of()), List.of(M_PULSE_BLUE, M_PULSE_RED));
    public static final Bullet M_PULSE_ION = new Bullet("m_cw_pulse_ion", "Ionic laser",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(DAMAGE_SHIELD, 80),
                    new Modifier(DAMAGE_HULL, -60)
            )), List.of(M_PULSE_ION_BLUE));
    public static final Bullet M_PLASMA = new Bullet("m_plasma", "Plasma",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(ROTATION_SPEED, -20),
                    new Modifier(RELOAD_TIME, 10),
                    new Modifier(SPEED, -10),
                    new Modifier(LIFE_TIME, 30),
                    new Modifier(DAMAGE_HULL, 30),
                    new Modifier(DAMAGE_SHIELD, 30),
                    new Modifier(COST.name(), 20))),
            List.of(M_PLASMA_CW));
    public static final Bullet M_GATLING = new Bullet("m_cw_gatling", "Gatling",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(RELOAD_TIME, -25),
                    new Modifier(SPEED, 20),
                    new Modifier(LIFE_TIME, -20),
                    new Modifier(ACCURACY, 10),
                    new Modifier(DAMAGE_HULL, -20),
                    new Modifier(DAMAGE_SHIELD, -20),
                    new Modifier(COST.name(), -10))),
            List.of(M_GATLING_CW, M_GATLING_XEN));
    public static final Bullet M_SHOTGUN = new Bullet("m_cw_shotgun", "Shotgun",
            Size.MEDIUM, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(SPEED, 20),
                    new Modifier(LIFE_TIME, -10),
                    new Modifier(ACCURACY, 10),
                    new Modifier(DAMAGE_HULL, -10),
                    new Modifier(DAMAGE_SHIELD, -10),
                    new Modifier(COST.name(), -10)
            )),
            List.of(M_SHOTGUN_CW));
    public static final Bullet M_BEAM = new Bullet("m_cw_beam", "Beam",
            Size.MEDIUM, List.of(ChassisType.BEAM),
            new Modifiers(List.of()),
            List.of(M_BEAM_CW, M_BEAM_CW_MINING, M_BEAM_KHA));

    // Large
    public static final Bullet L_PULSE = new Bullet("l_pulse", "Pulse laser",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of()),
            List.of(L_PULSE_BLUE, L_PULSE_YELLOW, L_PULSE_VIOLET, L_PULSE_RED));
    public static final Bullet L_PLASMA = new Bullet("l_plasma", "Plasma",
            Size.LARGE, List.of(ChassisType.STANDARD, ChassisType.RAFFLE),
            new Modifiers(List.of(
                    new Modifier(ROTATION_SPEED, -20),
                    new Modifier(RELOAD_TIME, 10),
                    new Modifier(SPEED, -10),
                    new Modifier(LIFE_TIME, 30),
                    new Modifier(DAMAGE_HULL, 30),
                    new Modifier(DAMAGE_SHIELD, 30),
                    new Modifier(COST.name(), 20))),
            List.of(L_PLASMA_BLUE, L_PLASMA_YELLOW, L_PLASMA_VIOLET));
    public static final Bullet L_BEAM = new Bullet("l_beam", "Beam",
            Size.LARGE, List.of(ChassisType.BEAM),
            new Modifiers(List.of()),
            List.of(L_BEAM_BLUE, L_BEAM_YELLOW, L_BEAM_VIOLET, L_BEAM_VIOLET_KHA));

    private BulletInstances() {
    }
}
