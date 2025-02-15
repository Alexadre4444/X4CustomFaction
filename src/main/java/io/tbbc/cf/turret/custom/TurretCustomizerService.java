package io.tbbc.cf.turret.custom;

import io.tbbc.cf.customizer.CustomizerComponent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TurretCustomizerService implements ITurretCustomizerService {

    @Inject
    ITurretCustomizerRepository baseTurretRepository;

    @Override
    public List<CustomizerComponent> getAll() {
        return baseTurretRepository.getAll();
    }

    @Override
    public CustomizerComponent getByName(String name) {
        return baseTurretRepository.getByName(name);
    }
}
