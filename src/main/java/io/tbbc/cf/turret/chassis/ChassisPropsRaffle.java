package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.property.Properties;
import io.tbbc.cf.property.Property;
import io.tbbc.cf.property.PropertyName;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class ChassisPropsRaffle implements IChassisProps {

    private final Properties properties;

    public ChassisPropsRaffle(BigDecimal rotationSpeed, BigDecimal hull, BigDecimal barrelAmount, BigDecimal amount,
                              BigDecimal reloadTime, BigDecimal fireRate, BigDecimal speed, BigDecimal lifeTime,
                              BigDecimal damageHull, BigDecimal damageShield, BigDecimal maxHits, BigDecimal ricochet, BigDecimal timeDiff,
                              BigDecimal accuracy, BigDecimal costAdvancedElectronics, BigDecimal costEnergyCells, BigDecimal costTurretsComps) {
        properties = new Properties(List.of(
                new Property(ROTATION_SPEED_NAME, rotationSpeed),
                new Property(HULL_NAME, hull),
                new Property(BARREL_AMOUNT_NAME, barrelAmount),
                new Property(AMOUNT_RAFFLE_NAME, amount),
                new Property(RELOAD_TIME_NAME, reloadTime),
                new Property(FIRE_RATE_NAME, fireRate),
                new Property(SPEED_NAME, speed),
                new Property(LIFE_TIME_NAME, lifeTime),
                new Property(DAMAGE_HULL_NAME, damageHull),
                new Property(DAMAGE_SHIELD_NAME, damageShield),
                new Property(MAX_HITS_NAME, maxHits),
                new Property(RICOCHET_NAME, ricochet),
                new Property(ACCURACY_NAME, accuracy),
                new Property(COST_CW_ADVANCED_ELECTRONICS_NAME, costAdvancedElectronics),
                new Property(COST_CW_ENERGY_CELLS_NAME, costEnergyCells),
                new Property(COST_CW_TURRET_COMPS_NAME, costTurretsComps),
                new Property(TIME_DIFF_NAME, timeDiff)
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
