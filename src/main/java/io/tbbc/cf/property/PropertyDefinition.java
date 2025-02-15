package io.tbbc.cf.property;

public record PropertyDefinition(PropertyName name, String label, String description, boolean reverse,
                                 CategoryName categoryName, Double minValue, Double maxValue, String unit,
                                 int decimal, Accessibility accessibility) {
}
