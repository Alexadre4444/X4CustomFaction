package io.tbbc.cf.customizer;

import io.tbbc.cf.modifier.Modifiers;

public record Customizer(String name, String label, CustomizerCategoryName categoryName, Modifiers modifiers) {
}
