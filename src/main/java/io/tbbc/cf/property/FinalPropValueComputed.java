package io.tbbc.cf.property;

import java.math.BigDecimal;

public record FinalPropValueComputed(PropertyDefinition definition, BigDecimal baseValue,
                                     BigDecimal finalValue)
        implements FinalPropValue {

    @Override
    public BigDecimal getBaseValue() {
        return baseValue;
    }

    @Override
    public PropertyName getName() {
        return definition.name();
    }

    @Override
    public BigDecimal getFinalValueWithoutLimit() {
        return finalValue;
    }
}
