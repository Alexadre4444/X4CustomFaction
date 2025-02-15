package io.tbbc.cf.bullet;

import io.tbbc.cf.property.FinalPropValue;
import io.tbbc.cf.property.FinalPropValueBase;
import io.tbbc.cf.property.FinalProperties;
import io.tbbc.cf.property.Property;

import java.util.List;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.Properties.*;


public class FlakEffect implements IBulletEffect {
    private String name = "FlakEffect";

    @Override
    public FinalProperties getNewProperties(FinalProperties properties) {
        FinalPropValue damageHull = properties.property(DAMAGE_HULL.name());
        FinalPropValue damageShield = properties.property(DAMAGE_SHIELD.name());
        FinalPropValue areaDamageHull = new FinalPropValueBase(new Property(AREA_DAMAGE_HULL.name(),
                damageHull.getBaseDoubleValue()),
                AREA_DAMAGE_HULL, damageHull.getModifiers());
        FinalPropValue areaDamageShield = new FinalPropValueBase(new Property(AREA_DAMAGE_SHIELD.name(),
                damageShield.getBaseDoubleValue()),
                AREA_DAMAGE_SHIELD, damageShield.getModifiers());
        FinalPropValue selfDestruct = new FinalPropValueBase(new Property(SELF_DESTRUCT.name(), 1),
                SELF_DESTRUCT, List.of());
        FinalPropValue areaTime = new FinalPropValueBase(new Property(AREA_TIME.name(), 1),
                AREA_TIME, List.of());
        FinalPropValue areaLifeTime = new FinalPropValueBase(new Property(AREA_LIFE_TIME.name(), 1),
                AREA_LIFE_TIME, List.of());
        return new FinalProperties(List.of(damageHull, damageShield, areaDamageHull, areaDamageShield,
                selfDestruct, areaTime, areaLifeTime));
    }

    public String getName() {
        return name;
    }
}
