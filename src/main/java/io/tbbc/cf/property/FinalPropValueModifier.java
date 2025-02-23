package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.util.List;

public record FinalPropValueModifier(PropertyDefinition definition, double baseValue, List<Modifier> modifiers)
        implements FinalPropValue {

    @Override
    public List<Modifier> getModifiers() {
        return modifiers.stream().toList();
    }

    @Override
    public double getFinalDoubleValue() {
        return baseValue + baseValue * sumModifiers() / 100;
    }

    @Override
    public double getBaseDoubleValue() {
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
