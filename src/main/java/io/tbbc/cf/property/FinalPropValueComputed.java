package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.util.List;

public record FinalPropValueComputed(PropertyDefinition definition, double baseDoubleValue,
                                     double finalDoubleValue)
        implements FinalPropValue {

    @Override
    public double getFinalDoubleValue() {
        return finalDoubleValue;
    }

    @Override
    public double getBaseDoubleValue() {
        return baseDoubleValue;
    }

    @Override
    public List<Modifier> getModifiers() {
        if (!hasModifiers()) {
            return List.of();
        }
        return List.of(computeModifier());
    }

    private boolean hasModifiers() {
        return baseDoubleValue != 0;
    }

    private Modifier computeModifier() {
        return new Modifier(definition().name(),
                baseDoubleValue != 0 ? (long) (finalDoubleValue / baseDoubleValue * 100L) : 0);
    }

    @Override
    public PropertyName getName() {
        return definition.name();
    }
}
