package io.tbbc.cf.cost;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CostWareService implements ICostWareService {

    @Inject
    ICostWareRepository costWareRepository;

    @Override
    public List<CostWare> getAll() {
        return costWareRepository.getAll();
    }
}
