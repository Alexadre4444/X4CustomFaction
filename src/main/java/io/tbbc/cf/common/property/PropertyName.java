package io.tbbc.cf.common.property;

import io.tbbc.cf.common.modifier.ModifierName;

public record PropertyName(String name) implements ModifierName, Comparable<PropertyName> {
    public PropertyName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank.");
        }
    }

    @Override
    public int compareTo(PropertyName o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
