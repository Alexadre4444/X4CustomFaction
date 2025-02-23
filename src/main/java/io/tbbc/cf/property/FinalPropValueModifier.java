package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.math.BigDecimal;
import java.util.List;

public record FinalPropValueModifier(PropertyDefinition definition, BigDecimal baseValue, List<Modifier> modifiers)
        implements FinalPropValue {

    @Override
    public List<Modifier> getModifiers() {
        return modifiers.stream().toList();
    }

    @Override
    public BigDecimal getFinalBigDecimalValue() {
        return baseValue.add(baseValue.multiply(new BigDecimal(sumModifiers())).multiply(new BigDecimal("0.01")));
    }

    @Override
    public BigDecimal getBaseBigDecimalValue() {
        return baseValue;
    }

    private long sumModifiers() {
        return modifiers.stream().mapToLong(Modifier::value).sum();
    }

    @Override
    public PropertyName getName() {
        return definition.name();
    }
}
