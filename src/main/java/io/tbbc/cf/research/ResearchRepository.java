package io.tbbc.cf.research;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.research.ResearchInstances.*;

@ApplicationScoped
public class ResearchRepository implements IResearchRepository {
    private static final List<Research> RESEARCH = Stream.of(
            RESEARCH_TERRAN_TECH, RESEARCH_FRONTIER,
            RESEARCH_ARGON, RESEARCH_PARANID, RESEARCH_TELADI, RESEARCH_SPLIT,
            RESEARCH_KHAAK, RESEARCH_XENON,
            RESEARCH_CLOSED_LOOP_PRODUCTION, RESEARCH_TERRAN_PRODUCTION,
            RESEARCH_MOD_BASIC, RESEARCH_MOD_ADVANCED, RESEARCH_MOD_EXPERT
    ).sorted(Comparator.comparing(Research::label)).toList();

    @Override
    public List<Research> getAll() {
        return RESEARCH;
    }
}
