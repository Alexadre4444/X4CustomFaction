package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public record FinalPropValueComputed(PropertyDefinition definition, BigDecimal baseValue,
                                     BigDecimal finalValue)
        implements FinalPropValue {

    @Override
    public double getFinalDoubleValue() {
        return finalValue.doubleValue();
    }

    @Override
    public double getBaseDoubleValue() {
        return baseValue.doubleValue();
    }

    @Override
    public List<Modifier> getModifiers() {
        if (!hasModifiers()) {
            return List.of();
        }
        return List.of(computeModifier());
    }

    @Override
    public BigDecimal getFinalBigDecimalValue() {
        return finalValue;
    }

    @Override
    public BigDecimal getBaseBigDecimalValue() {
        return baseValue;
    }

    private boolean hasModifiers() {
        return baseValue.compareTo(new BigDecimal(0)) != 0;
    }

    private Modifier computeModifier() {
        return new Modifier(definition().name(), finalValue.divide(baseValue, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100)).longValue());
    }

    @Override
    public PropertyName getName() {
        return definition.name();
    }
}
