package io.tbbc.cf.bullet;

import io.tbbc.cf.property.FinalPropValue;
import io.tbbc.cf.property.FinalPropValueComputed;
import io.tbbc.cf.property.FinalProperties;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.Properties.*;


public class SeismicEffect implements IBulletEffect {
    private String name = "SeismicEffect";

    @Override
    public FinalProperties getNewProperties(FinalProperties properties) {
        FinalPropValue damageHull = properties.property(DAMAGE_HULL.name());
        FinalPropValue damageShield = properties.property(DAMAGE_SHIELD.name());
        FinalPropValue areaDamageHull = new FinalPropValueComputed(AREA_DAMAGE_HULL,
                damageHull.getBaseValue().divide(new BigDecimal(3), RoundingMode.HALF_UP),
                damageHull.getFinalValue().divide(new BigDecimal(3), RoundingMode.HALF_UP));
        FinalPropValue areaDamageShield = new FinalPropValueComputed(AREA_DAMAGE_SHIELD,
                damageShield.getBaseValue().divide(new BigDecimal("3"), RoundingMode.HALF_UP),
                damageShield.getFinalValue().divide(new BigDecimal("3"), RoundingMode.HALF_UP));
        FinalPropValue selfDestruct = new FinalPropValueComputed(SELF_DESTRUCT, new BigDecimal("1"), new BigDecimal("1"));
        FinalPropValue plannedSelfDestruct = new FinalPropValueComputed(PLANNED_SELF_DESTRUCT,
                new BigDecimal(1), new BigDecimal(1));
        FinalPropValue selfDestructTimeDiff = new FinalPropValueComputed(SELF_DESTRUCT_TIME_DIFF,
                new BigDecimal("0.02"), new BigDecimal("0.02"));
        FinalPropValue selfDestructMinTime = new FinalPropValueComputed(SELF_DESTRUCT_MIN_TIME,
                new BigDecimal("1.5"), new BigDecimal("1.5"));
        return new FinalProperties(List.of(damageHull, damageShield, areaDamageHull, areaDamageShield,
                selfDestruct, plannedSelfDestruct, selfDestructTimeDiff, selfDestructMinTime));
    }

    public String getName() {
        return name;
    }
}
