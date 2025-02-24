package io.tbbc.cf.property;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface FinalPropValue {

    default String getBaseValueString() {
        return getBaseValue().toString();
    }

    default String getFinalValueString() {
        return getFinalValue().toString();
    }

    private BigDecimal limitValue(BigDecimal value) {
        return rangeLimit(limitScale(value));
    }

    private BigDecimal rangeLimit(BigDecimal value) {
        if (definition().minValue() != null && value.compareTo(definition().minValue()) < 0) {
            return definition().minValue();
        }
        if (definition().maxValue() != null && value.compareTo(definition().maxValue()) > 0) {
            return definition().maxValue();
        }
        return value;
    }

    private BigDecimal limitScale(BigDecimal value) {
        return value.setScale(definition().decimal(), RoundingMode.HALF_UP);
    }

    default BigDecimal getFinalValue() {
        return limitValue(getFinalValueWithoutLimit());
    }

    BigDecimal getBaseValue();

    PropertyName getName();

    PropertyDefinition definition();

    BigDecimal getFinalValueWithoutLimit();

    default Integer getModifier() {
        BigDecimal baseValue = getBaseValue();
        if (baseValue.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        BigDecimal limitedFinalValue = getFinalValue();
        BigDecimal diff = limitedFinalValue.subtract(baseValue);
        BigDecimal diffPercent = diff.divide(baseValue, 2, RoundingMode.HALF_UP);
        return diffPercent.multiply(new BigDecimal("100")).intValue();
    }
}
