package io.tbbc.cf.property;

import io.tbbc.cf.common.InternalException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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

    public boolean hasProperty(String name) {
        return properties.stream().anyMatch(property -> property.getName().name().equals(name));
    }

    public FinalPropValue property(PropertyName name) {
        return properties.stream().filter(property -> property.getName().equals(name))
                .findFirst().orElseThrow(() -> new InternalException("Property not found: " + name));
    }

    public FinalPropValue property(String name) {
        return property(new PropertyName(name));
    }

    public FinalProperties concat(FinalProperties other) {
        return new FinalProperties(Stream.concat(properties.stream(),
                other.properties.stream()
                        .filter(property ->
                                properties.stream().noneMatch(existingProperty ->
                                        existingProperty.getName().equals(property.getName())))).toList());
    }
}
