package io.tbbc.cf.property;

import io.tbbc.cf.common.InternalException;
import io.tbbc.cf.modifier.Modifier;
import io.tbbc.cf.modifier.Modifiers;

import java.util.List;
import java.util.Optional;

public class PropsHelper {
    private PropsHelper() {
    }

    public static FinalProperties computeFinalProps(List<Property> properties,
                                                    List<PropertyDefinition> definitions,
                                                    List<Modifiers> modifiers) {
        return new FinalProperties(properties.stream()
                .map(property -> computeFinalProps(property, definitions, modifiers))
                .toList());
    }

    private static FinalPropValue computeFinalProps(Property property, List<PropertyDefinition> definitions, List<Modifiers> modifiers) {
        PropertyDefinition propertyDefinition = definitions.stream()
                .filter(definition -> definition.name().equals(property.name()))
                .findFirst()
                .orElseThrow(() -> new InternalException("Property definition not found for property: " + property.name()));
        return computeFinalProps(property, propertyDefinition, modifiers);
    }

    private static FinalPropValue computeFinalProps(Property property, PropertyDefinition definition, List<Modifiers> modifiers) {
        List<Modifier> applicableModifiers = modifiers.stream()
                .map(modifier -> modifier.modifier(property.name(), definition.categoryName()))
                .flatMap(Optional::stream)
                .toList();

        return new FinalPropValueBase(property, definition, applicableModifiers);
    }
}
