package io.tbbc.cf.turret.custom;

import io.tbbc.cf.customizer.CustomizerComponent;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.turret.custom.TurretCustomizerInstances.*;

@ApplicationScoped
public class TurretCustomizerRepository implements ITurretCustomizerRepository {

    private static final List<CustomizerComponent> INSTANCES = Stream.of(
            CANNON, TURRET_ENGINE, ENERGY_SOURCE
    ).sorted(Comparator.comparing(CustomizerComponent::label)).toList();

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
