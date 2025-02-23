package io.tbbc.cf.property;

import java.math.BigDecimal;

public record PropertyDefinition(PropertyName name, String label, String description, boolean reverse,
                                 CategoryName categoryName, BigDecimal minValue, BigDecimal maxValue, String unit,
                                 int decimal, Accessibility accessibility, boolean isFree) {
}
