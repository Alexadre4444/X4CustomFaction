package io.tbbc.cf.property;

import io.tbbc.cf.modifier.Modifier;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public interface FinalPropValue {

    default String getBaseValueString() {
        return format(limitValue(getBaseBigDecimalValue()));
    }

    default String getFinalValueString() {
        return format(limitValue(getFinalBigDecimalValue()));
    }

    List<Modifier> getModifiers();

    private BigDecimal limitValue(BigDecimal value) {
        if (definition().minValue() != null && value.compareTo(definition().minValue()) < 0) {
            return definition().minValue();
        }
        if (definition().maxValue() != null && value.compareTo(definition().maxValue()) > 0) {
            return definition().maxValue();
        }
        return value;
    }

    private String format(BigDecimal value) {
        String format = "%." + definition().decimal() + "f";
        return String.format(Locale.US, format, value);
    }

    BigDecimal getFinalBigDecimalValue();

    BigDecimal getBaseBigDecimalValue();

    double getFinalDoubleValue();

    double getBaseDoubleValue();

    PropertyName getName();

    PropertyDefinition definition();
}
