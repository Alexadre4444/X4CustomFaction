package io.tbbc.cf.turret.custom;

import io.tbbc.cf.common.customizer.CustomizerComponent;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import static io.tbbc.cf.turret.custom.TurretCustomizerInstances.*;

@ApplicationScoped
public class TurretCustomizerRepository implements ITurretCustomizerRepository {

    private static final List<CustomizerComponent> INSTANCES = List.of(
            CANNON, TURRET_ENGINE, ENERGY_SOURCE
    );

    @Override
    public List<CustomizerComponent> getAll() {
        return INSTANCES;
    }

    @Override
    public CustomizerComponent getByName(String name) {
        return getAll().stream()
                .filter(customizerCategory -> customizerCategory.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}
