package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.property.PropertyDefinition;
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
            M_PULSE, M_GATLING, M_PLASMA, M_SHOTGUN, M_BEAM, M_FLAK,
            M_ELECTROMAGNETIC, M_NEEDLER,
            L_PULSE, L_PLASMA, L_BASE_BEAM, L_PULSE_BEAM, L_GATLING,
            L_SEISMIC
    ).sorted(Comparator.comparing(TurretChassis::label)).toList();

    private final List<PropertyDefinition> properties = Stream.of(
            DAMAGE_HULL, DAMAGE_SHIELD, FIRE_RATE, RELOAD_TIME, ROTATION_SPEED, LIFE_TIME, SPEED,
            AMOUNT_RAFFLE, AMOUNT_STANDARD, MAX_HITS, RICOCHET, BARREL_AMOUNT, HULL, ACCURACY, COST_CW_ADVANCED_ELECTRONICS,
            COST_CW_ENERGY_CELLS, COST_CW_TURRET_COMPS, RANGE, BEAM_RANGE, BURST_TIME, SHOOT_PER_SECOND, DAMAGE_PER_SECOND_HULL,
            DAMAGE_PER_SECOND_SHIELD, ROTATION_ACCELERATION, TIME_DIFF, DAMAGE_BONUS_SHIELD, DAMAGE_BONUS_HULL, DAMAGE_BASE,
            COST_CL_CLAYTRONICS, COST_CL_ENERGY_CELLS, COST_CL_HULL_PART,
            COST_TER_CARBIDE, COST_TER_ENERGY_CELLS, COST_TER_MICROLATICE, COST_TER_COMPUTRONIC,
            SELF_DESTRUCT, AREA_DAMAGE_HULL, AREA_DAMAGE_SHIELD, AREA_DAMAGE_BASE, AREA_DAMAGE_BONUS_SHIELD, AREA_DAMAGE_BONUS_HULL, AREA_TIME, AREA_LIFE_TIME,
            PLANNED_SELF_DESTRUCT, SELF_DESTRUCT_TIME_DIFF, SELF_DESTRUCT_MIN_TIME
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
