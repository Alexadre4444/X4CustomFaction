package io.tbbc.cf.modifier;

import io.tbbc.cf.common.InternalException;
import io.tbbc.cf.property.CategoryName;
import io.tbbc.cf.property.PropertyName;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record Modifiers(List<Modifier> modifiers) {
    public Modifiers(List<Modifier> modifiers) {
        if (modifiers.stream().distinct().count() != modifiers.size()) {
            throw new InternalException("Modifiers must have unique names.");
        }
        this.modifiers = Collections.unmodifiableList(modifiers);
    }

    public List<Modifier> modifiers() {
        return modifiers;
    }

    public Optional<Modifier> modifier(PropertyName name, CategoryName categoryName) {
        return modifiers.stream().filter(modifier -> modifier.name().equals(name)
                || modifier.name().equals(categoryName)).findFirst();
    }
}
