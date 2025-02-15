package io.tbbc.cf.customizer;

import io.tbbc.cf.modifier.Modifiers;
import io.tbbc.cf.research.Research;

public record Customizer(String name, String label, CustomizerCategoryName categoryName, Modifiers modifiers,
                         Research requiredResearch) {
}
