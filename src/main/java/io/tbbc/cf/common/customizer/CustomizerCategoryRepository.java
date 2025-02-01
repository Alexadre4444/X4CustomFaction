package io.tbbc.cf.common.customizer;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CustomizerCategoryRepository implements ICustomizerCategoryRepository {
    private static final List<CustomizerCategory> MODIFIERS = List.of(
            CustomizerCategoryInstances.BASIC,
            CustomizerCategoryInstances.ADVANCED,
            CustomizerCategoryInstances.EXPERT
    );

    @Override
    public List<CustomizerCategory> getAll() {
        return MODIFIERS;
    }
}
