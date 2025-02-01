package io.tbbc.cf.common.property;

import io.tbbc.cf.common.InternalException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public record FinalProperties(List<FinalPropValue> properties) {
    public FinalProperties(List<FinalPropValue> properties) {
        if (properties.stream().distinct().count() != properties.size()) {
            throw new InternalException("Props must have unique names.");
        }
        this.properties = Collections.unmodifiableList(properties);
    }

    public List<FinalPropValue> getProperties() {
        return properties.stream().sorted(Comparator.comparing(FinalPropValue::getName)).toList();
    }

    public FinalPropValue property(PropertyName name) {
        return properties.stream().filter(property -> property.getName().equals(name))
                .findFirst().orElseThrow(() -> new InternalException("Property not found: " + name));
    }

    public FinalPropValue property(String name) {
        return property(new PropertyName(name));
    }
}
