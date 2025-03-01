package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.math.BigDecimal;
import java.util.List;

public record FinalPropValueBase(Property property, PropertyDefinition definition, List<Modifier> modifiers)
        implements FinalPropValue {

    @Override
    public BigDecimal getBaseValue() {
        return BigDecimal.valueOf(property.value().doubleValue());
    }

    private long sumModifiers() {
        return modifiers.stream().mapToLong(Modifier::value).sum();
    }

    @Override
    public PropertyName getName() {
        return property.name();
    }

    @Override
    public BigDecimal getFinalValueWithoutLimit() {
        return getBaseValue().add(getBaseValue()
                .multiply(new BigDecimal(sumModifiers())).multiply(new BigDecimal("0.01")));
    }
}
