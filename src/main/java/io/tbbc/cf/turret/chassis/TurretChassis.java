package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.common.Size;
import io.tbbc.cf.turret.chassis.skin.ChassisSkin;

import java.util.List;

public record TurretChassis(String name,
                            String label,
                            Size size,
                            ChassisType type,
                            IChassisProps props,
                            List<ChassisSkin> availableSkins) {
}
