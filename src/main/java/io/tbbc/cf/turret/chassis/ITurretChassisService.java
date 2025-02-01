package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.common.property.PropertyDefinition;

import java.util.List;
import java.util.Optional;

public interface ITurretChassisService {
    List<PropertyDefinition> getProperties();

    List<TurretChassis> getAll();

    Optional<TurretChassis> getByName(String name);
}
