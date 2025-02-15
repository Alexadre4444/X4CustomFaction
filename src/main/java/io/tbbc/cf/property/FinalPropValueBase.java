package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.util.List;

public record FinalPropValueBase(Property property, PropertyDefinition definition, List<Modifier> modifiers)
        implements FinalPropValue {

    @Override
    public double getFinalDoubleValue() {
        return property.value().doubleValue() +
                property.value().doubleValue() * sumModifiers() / 100;
    }

    @Override
    public double getBaseDoubleValue() {
        return property.value().doubleValue();
    }

    private long sumModifiers() {
        return modifiers.stream().mapToLong(Modifier::value).sum();
    }

    @Override
    public PropertyName getName() {
        return property.name();
    }
}
