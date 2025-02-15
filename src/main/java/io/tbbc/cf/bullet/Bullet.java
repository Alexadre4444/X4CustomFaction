package io.tbbc.cf.bullet;

import io.tbbc.cf.bullet.skin.BulletSkin;
import io.tbbc.cf.common.Size;
import io.tbbc.cf.modifier.Modifiers;
import io.tbbc.cf.turret.chassis.ChassisType;

import java.util.List;

public record Bullet(String name, String label, Size size, List<ChassisType> compatibleChassis,
                     Modifiers modifiers, List<BulletSkin> availableSkins) {
}
