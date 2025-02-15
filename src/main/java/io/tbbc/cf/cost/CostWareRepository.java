package io.tbbc.cf.cost;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.cost.CostWareInstances.ENERGY_CELLS;

@ApplicationScoped
public class CostWareRepository implements ICostWareRepository {
    private static final List<CostWare> INSTANCES = Stream.of(
            ENERGY_CELLS
    ).sorted(Comparator.comparing(CostWare::label)).toList();

    @Override
    public List<CostWare> getAll() {
        return INSTANCES;
    }
}
