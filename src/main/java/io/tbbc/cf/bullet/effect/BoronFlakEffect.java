package io.tbbc.cf.bullet.effect;

import io.tbbc.cf.property.FinalPropValue;
import io.tbbc.cf.property.FinalPropValueComputed;
import io.tbbc.cf.property.FinalProperties;

import java.math.BigDecimal;
import java.util.List;

import static io.tbbc.cf.turret.chassis.TurretChassisInstances.Properties.*;


public class BoronFlakEffect implements IBulletEffect {
    private static final BigDecimal AREA_DAMAGE_HULL_MULTIPLIER = new BigDecimal(8);
    private static final BigDecimal AREA_DAMAGE_SHIELD_MULTIPLIER = new BigDecimal("3.5");

    @Override
    public FinalProperties getNewProperties(FinalProperties properties) {
        FinalPropValue damageHull = properties.property(DAMAGE_HULL.name());
        FinalPropValue damageShield = properties.property(DAMAGE_SHIELD.name());

        FinalPropValue areaDamageHull = new FinalPropValueComputed(AREA_DAMAGE_HULL,
                damageHull.getBaseValue().multiply(AREA_DAMAGE_HULL_MULTIPLIER),
                damageHull.getFinalValue().multiply(AREA_DAMAGE_HULL_MULTIPLIER));
        FinalPropValue areaDamageShield = new FinalPropValueComputed(AREA_DAMAGE_SHIELD,
                damageShield.getBaseValue().multiply(AREA_DAMAGE_SHIELD_MULTIPLIER),
                damageShield.getFinalValue().multiply(AREA_DAMAGE_SHIELD_MULTIPLIER));

        FinalPropValue selfDestruct = new FinalPropValueComputed(SELF_DESTRUCT, new BigDecimal("1"), new BigDecimal("1"));
        FinalPropValue plannedSelfDestruct = new FinalPropValueComputed(PLANNED_SELF_DESTRUCT,
                new BigDecimal(1), new BigDecimal(1));
        FinalPropValue selfDestructTimeDiff = new FinalPropValueComputed(SELF_DESTRUCT_TIME_DIFF,
                new BigDecimal("0.02"), new BigDecimal("0.06"));
        FinalPropValue selfDestructMinTime = new FinalPropValueComputed(SELF_DESTRUCT_MIN_TIME,
                new BigDecimal("1.5"), new BigDecimal("0.15"));

        return new FinalProperties(List.of(damageHull, damageShield, areaDamageHull, areaDamageShield,
                selfDestruct, plannedSelfDestruct, selfDestructTimeDiff, selfDestructMinTime));
    }

    public String getName() {
        return "BoronFlakEffect";
    }
}
