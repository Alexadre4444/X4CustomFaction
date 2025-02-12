package io.tbbc.cf.research;

import io.tbbc.cf.common.LangEntry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ResearchService implements IResearchService {
    public static final int EGO_RESEARCH_LABEL_SECTION = 145000;
    public static final int EGO_RESEARCH_DESCRIPTION_SECTION = 146000;

    private final IResearchRepository researchRepository;

    @Inject
    public ResearchService(IResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }


    @Override
    public List<Research> getAll() {
        return researchRepository.getAll();
    }

    @Override
    public List<ResearchEgoProp> getResearchEgoProps() {
        List<ResearchEgoProp> researchEgoProps = new ArrayList<>();
        List<Research> researches = researchRepository.getAll();
        for (int i = 0; i < researches.size(); i++) {
            researchEgoProps.add(makeEgoProps(researches.get(i), i));
        }
        return researchEgoProps;
    }

    private ResearchEgoProp makeEgoProps(Research research, int index) {
        return new ResearchEgoProp(research.name(), EGO_RESEARCH_LABEL_SECTION, EGO_RESEARCH_DESCRIPTION_SECTION,
                new LangEntry(index, research.label()),
                new LangEntry(index, research.description()));
    }
}
