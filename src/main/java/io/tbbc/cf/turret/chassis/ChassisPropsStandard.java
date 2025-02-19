package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.property.Properties;
import io.tbbc.cf.property.Property;
import io.tbbc.cf.property.PropertyName;

import java.util.List;
import java.util.Optional;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class ChassisPropsStandard implements IChassisProps {

    private final Properties properties;

    public ChassisPropsStandard(long rotationSpeed, long hull, long barrelAmount, long amount, double reloadTime,
                                long speed, double lifeTime, long damageHull, long damageShield, long maxHits, double ricochet, double timeDiff,
                                double accuracy, long costAdvancedElectronics, long costEnergyCells,
                                long costTurretsComps) {
        properties = new Properties(List.of(
                new Property(ROTATION_SPEED, rotationSpeed),
                new Property(HULL, hull),
                new Property(BARREL_AMOUNT, barrelAmount),
                new Property(AMOUNT, amount),
                new Property(RELOAD_TIME, reloadTime),
                new Property(SPEED, speed),
                new Property(LIFE_TIME, lifeTime),
                new Property(DAMAGE_HULL, damageHull),
                new Property(DAMAGE_SHIELD, damageShield),
                new Property(MAX_HITS, maxHits),
                new Property(RICOCHET, ricochet),
                new Property(TIME_DIFF, timeDiff),
                new Property(ACCURACY, accuracy),
                new Property(COST_CW_ADVANCED_ELECTRONICS, costAdvancedElectronics),
                new Property(COST_CW_ENERGY_CELLS, costEnergyCells),
                new Property(COST_CW_TURRET_COMPS, costTurretsComps)
        ));
    }

    @Override
    public List<Property> getProperties() {
        return properties.properties();
    }

    @Override
    public Optional<Property> property(PropertyName name) {
        return properties.property(name);
    }
}
