package io.tbbc.cf.common.property;

import io.tbbc.cf.common.InternalException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record Properties(List<Property> properties) {
    public Properties(List<Property> properties) {
        if (properties.stream().distinct().count() != properties.size()) {
            throw new InternalException("Props must have unique names.");
        }
        this.properties = Collections.unmodifiableList(properties);
    }

    public Optional<Property> property(PropertyName name) {
        return properties.stream().filter(property -> property.name().equals(name)).findFirst();
    }
}
