package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.property.PropertyDefinition;

import java.util.List;
import java.util.Optional;

public interface ITurretChassisRepository {
    List<PropertyDefinition> getProperties();

    List<TurretChassis> getAll();

    Optional<TurretChassis> getByName(String name);
}
