package io.tbbc.cf.common.property;

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
    public PropertyName getName() {
        return definition.name();
    }
}
