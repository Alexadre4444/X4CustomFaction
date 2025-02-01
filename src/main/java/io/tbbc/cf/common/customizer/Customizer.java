package io.tbbc.cf.common.customizer;

import io.tbbc.cf.common.modifier.Modifiers;

public record Customizer(String name, String label, CustomizerCategoryName categoryName, Modifiers modifiers) {
}
