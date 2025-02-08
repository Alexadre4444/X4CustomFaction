package io.tbbc.cf.common.production;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductionMethodService implements IProductionMethodService {
    @Inject
    IProductionMethodRepository productionMethodRepository;

    @Override
    public List<ProductionMethod> getAll() {
        return productionMethodRepository.getAll();
    }

    @Override
    public Optional<ProductionMethod> getByName(String name) {
        return productionMethodRepository.getByName(name);
    }
}
