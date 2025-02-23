package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.property.Properties;
import io.tbbc.cf.property.Property;
import io.tbbc.cf.property.PropertyName;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class ChassisPropsBeam implements IChassisProps {

    private final Properties properties;

    public ChassisPropsBeam(BigDecimal rotationSpeed, BigDecimal hull, BigDecimal reloadTime,
                            BigDecimal lifeTime, BigDecimal damageHull, BigDecimal damageShield,
                            BigDecimal range, BigDecimal costAdvancedElectronics, BigDecimal costEnergyCells,
                            BigDecimal costTurretsComps) {
        properties = new Properties(List.of(
                new Property(ROTATION_SPEED, rotationSpeed),
                new Property(HULL, hull),
                new Property(RELOAD_TIME, reloadTime),
                new Property(LIFE_TIME, lifeTime),
                new Property(DAMAGE_HULL, damageHull),
                new Property(DAMAGE_SHIELD, damageShield),
                new Property(COST_CW_ADVANCED_ELECTRONICS, costAdvancedElectronics),
                new Property(COST_CW_ENERGY_CELLS, costEnergyCells),
                new Property(COST_CW_TURRET_COMPS, costTurretsComps),
                new Property(BEAM_RANGE, range)
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
