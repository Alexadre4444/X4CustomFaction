package io.tbbc.cf.bullet;

import io.tbbc.cf.property.FinalPropValue;
import io.tbbc.cf.property.FinalPropValueBase;
import io.tbbc.cf.property.FinalProperties;
import io.tbbc.cf.property.Property;

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
        FinalPropValue areaDamageHull = new FinalPropValueBase(new Property(AREA_DAMAGE_HULL.name(),
                damageHull.getBaseBigDecimalValue().divide(new BigDecimal("3"), RoundingMode.HALF_UP)),
                AREA_DAMAGE_HULL, damageHull.getModifiers());
        FinalPropValue areaDamageShield = new FinalPropValueBase(new Property(AREA_DAMAGE_SHIELD.name(),
                damageShield.getBaseBigDecimalValue().divide(new BigDecimal("3"), RoundingMode.HALF_UP)),
                AREA_DAMAGE_SHIELD, damageShield.getModifiers());
        FinalPropValue selfDestruct = new FinalPropValueBase(new Property(SELF_DESTRUCT.name(), new BigDecimal("1")),
                SELF_DESTRUCT, List.of());
        FinalPropValue plannedSelfDestruct = new FinalPropValueBase(new Property(PLANNED_SELF_DESTRUCT.name(), new BigDecimal(1)),
                PLANNED_SELF_DESTRUCT, List.of());
        FinalPropValue selfDestructTimeDiff = new FinalPropValueBase(new Property(SELF_DESTRUCT_TIME_DIFF.name(), new BigDecimal("0.02")),
                SELF_DESTRUCT_TIME_DIFF, List.of());
        FinalPropValue selfDestructMinTime = new FinalPropValueBase(new Property(SELF_DESTRUCT_MIN_TIME.name(), new BigDecimal("1.5")),
                SELF_DESTRUCT_MIN_TIME, List.of());
        return new FinalProperties(List.of(damageHull, damageShield, areaDamageHull, areaDamageShield,
                selfDestruct, plannedSelfDestruct, selfDestructTimeDiff, selfDestructMinTime));
    }

    public String getName() {
        return name;
    }
}
