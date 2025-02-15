package io.tbbc.cf.property;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CategoryRepository implements ICategoryRepository {
    private static final List<Category> INSTANCES = List.of(
            CategoryInstances.BULLET, CategoryInstances.COST, CategoryInstances.TURRET, CategoryInstances.DAMAGE);

    @Override
    public List<Category> getAll() {
        return INSTANCES;
    }
}
