package io.tbbc.cf.cost;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.cost.CostWareInstances.*;

@ApplicationScoped
public class CostWareRepository implements ICostWareRepository {
    private static final List<CostWare> INSTANCES = Stream.of(
            ENERGY_CELLS,
            ADVANCED_ELECTRONICS,
            ADVANCED_COMPOSITES,
            FIELD_COILS,
            SMART_CHIPS,
            WEAPON_COMPONENTS,
            TURRET_COMPONENTS,
            PLASMA_CONDUCTORS,
            ANTIMATTER_CONVERTERS,
            NIVIDIUM,
            CLAYTRONICS,
            HULL_PARTS,
            COMPUTRONIC_SUBSTRATE,
            METALLIC_MICRO_LATTICE,
            SILICON_CARBIDE
    ).sorted(Comparator.comparing(CostWare::label)).toList();

    @Override
    public List<CostWare> getAll() {
        return INSTANCES;
    }
}
