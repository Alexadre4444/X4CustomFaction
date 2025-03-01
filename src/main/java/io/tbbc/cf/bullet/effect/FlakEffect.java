package io.tbbc.cf.bullet.effect;

import io.tbbc.cf.property.FinalPropValue;
import io.tbbc.cf.property.FinalPropValueComputed;
import io.tbbc.cf.property.FinalProperties;

import java.math.BigDecimal;
import java.util.List;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.Properties.*;


public class FlakEffect implements IBulletEffect {

    @Override
    public FinalProperties getNewProperties(FinalProperties properties) {
        FinalPropValue damageHull = properties.property(DAMAGE_HULL.name());
        FinalPropValue damageShield = properties.property(DAMAGE_SHIELD.name());
        FinalPropValue areaDamageHull = new FinalPropValueComputed(AREA_DAMAGE_HULL,
                damageHull.getBaseValue(), damageHull.getFinalValue());
        FinalPropValue areaDamageShield = new FinalPropValueComputed(AREA_DAMAGE_SHIELD,
                damageShield.getBaseValue(), damageShield.getFinalValue());
        FinalPropValue selfDestruct = new FinalPropValueComputed(SELF_DESTRUCT, new BigDecimal(1), new BigDecimal(1));
        FinalPropValue areaTime = new FinalPropValueComputed(AREA_TIME, new BigDecimal(1), new BigDecimal(1));
        FinalPropValue areaLifeTime = new FinalPropValueComputed(AREA_LIFE_TIME, new BigDecimal(1), new BigDecimal(1));
        return new FinalProperties(List.of(damageHull, damageShield, areaDamageHull, areaDamageShield,
                selfDestruct, areaTime, areaLifeTime));
    }

    public String getName() {
        return "FlakEffect";
    }
}
