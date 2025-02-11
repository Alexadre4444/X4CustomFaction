package io.tbbc.cf.research;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ResearchService implements IResearchService {

    private final IResearchRepository researchRepository;

    @Inject
    public ResearchService(IResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }


    @Override
    public List<Research> getAll() {
        return researchRepository.getAll();
    }
}
