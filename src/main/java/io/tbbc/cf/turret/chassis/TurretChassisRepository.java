package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.common.property.PropertyDefinition;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.*;
import static io.tbbc.cf.turret.chassis.TurretChassisInstances.Properties.*;

@ApplicationScoped
public class TurretChassisRepository implements ITurretChassisRepository {
    private final List<TurretChassis> baseTurrets = Stream.of(
            M_PULSE, M_GATLING, M_PLASMA, M_SHOTGUN, M_BEAM,
            L_PULSE, L_PLASMA, L_BASE_BEAM, L_PULSE_BEAM, L_GATLING
    ).sorted(Comparator.comparing(TurretChassis::label)).toList();

    private final List<PropertyDefinition> properties = Stream.of(
            DAMAGE_HULL, DAMAGE_SHIELD, FIRE_RATE, RELOAD_TIME, ROTATION_SPEED, LIFE_TIME, SPEED,
            AMOUNT, MAX_HITS, RICOCHET, BARREL_AMOUNT, HULL, ACCURACY, COST_ADVANCED_ELECTRONICS,
            COST_ENERGY_CELLS, COST_TURRET_COMPS, RANGE, BURST_TIME, SHOOT_PER_SECOND, DAMAGE_PER_SECOND_HULL,
            DAMAGE_PER_SECOND_SHIELD, ROTATION_ACCELERATION, TIME_DIFF, DAMAGE_BONUS_SHIELD
    ).sorted(Comparator.comparing(PropertyDefinition::label)).toList();

    @Override
    public List<PropertyDefinition> getProperties() {
        return properties;
    }

    @Override
    public List<TurretChassis> getAll() {
        return baseTurrets;
    }

    @Override
    public Optional<TurretChassis> getByName(String name) {
        return getAll().stream()
                .filter(baseTurret -> baseTurret.name().equals(name))
                .findFirst();
    }
}
