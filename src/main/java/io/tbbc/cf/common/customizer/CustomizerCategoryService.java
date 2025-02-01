package io.tbbc.cf.common.customizer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CustomizerCategoryService implements ICustomizerCategoryService {
    @Inject
    ICustomizerCategoryRepository customizerCategoryRepository;

    @Override
    public List<CustomizerCategory> getAll() {
        return customizerCategoryRepository.getAll();
    }
}
