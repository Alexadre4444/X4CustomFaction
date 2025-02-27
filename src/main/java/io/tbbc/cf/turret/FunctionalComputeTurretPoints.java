package io.tbbc.cf.turret;

import io.tbbc.cf.bullet.Bullet;
import io.tbbc.cf.property.PropertyDefinition;
import io.tbbc.cf.turret.chassis.TurretChassis;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface FunctionalComputeTurretPoints {
    int computeCost(TurretChassis chassis, Bullet bullet,
                    List<PropertyDefinition> propertyDefinitions,
                    Map<String, Integer> customizers);

}
