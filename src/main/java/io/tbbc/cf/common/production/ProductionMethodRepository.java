package io.tbbc.cf.common.production;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static io.tbbc.cf.common.production.ProductionMethodInstances.*;

@ApplicationScoped
public class ProductionMethodRepository implements IProductionMethodRepository {
    private final List<ProductionMethod> productionMethods = Stream.of(
            CW, TERRAN, CLOSED_LOOP
    ).sorted(Comparator.comparing(ProductionMethod::label)).toList();

    @Override
    public List<ProductionMethod> getAll() {
        return productionMethods;
    }

    @Override
    public Optional<ProductionMethod> getByName(String name) {
        return productionMethods.stream().filter(method -> method.name().equals(name)).findFirst();
    }
}
