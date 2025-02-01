package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.common.property.PropertyDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TurretChassisService implements ITurretChassisService {
    @Inject
    ITurretChassisRepository baseTurretRepository;

    @Override
    public List<PropertyDefinition> getProperties() {
        return baseTurretRepository.getProperties();
    }

    @Override
    public List<TurretChassis> getAll() {
        return baseTurretRepository.getAll();
    }

    @Override
    public Optional<TurretChassis> getByName(String name) {
        return baseTurretRepository.getByName(name);
    }
}
