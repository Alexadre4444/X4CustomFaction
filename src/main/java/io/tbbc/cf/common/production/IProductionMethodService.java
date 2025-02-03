package io.tbbc.cf.common.production;

import java.util.List;
import java.util.Optional;

public interface IProductionMethodService {
    List<ProductionMethod> getAll();

    Optional<ProductionMethod> getByName(String name);
}
