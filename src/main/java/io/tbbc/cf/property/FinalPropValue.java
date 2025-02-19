package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.util.List;
import java.util.Locale;

public interface FinalPropValue {

    default String getBaseValueString() {
        return format(limitValue(getBaseDoubleValue()));
    }

    default String getFinalValueString() {
        return format(limitValue(getFinalDoubleValue()));
    }

    default List<Modifier> getModifiers() {
        return List.of();
    }

    private double limitValue(double value) {
        if (definition().minValue() != null && value < definition().minValue()) {
            return definition().minValue();
        }
        if (definition().maxValue() != null && value > definition().maxValue()) {
            return definition().maxValue();
        }
        return value;
    }

    private String format(double value) {
        String format = "%." + definition().decimal() + "f";
        return String.format(Locale.US, format, value);
    }

    double getFinalDoubleValue();

    double getBaseDoubleValue();

    PropertyName getName();

    PropertyDefinition definition();
}
