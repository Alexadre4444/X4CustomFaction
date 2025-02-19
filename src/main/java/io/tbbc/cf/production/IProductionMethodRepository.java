package io.tbbc.cf.production;

import java.util.List;
import java.util.Optional;

public interface IProductionMethodRepository {
    List<ProductionMethod> getAll();

    Optional<ProductionMethod> getByName(String name);
}
