package io.tbbc.cf.research;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.research.ResearchInstances.FRONTIER;
import static io.tbbc.cf.research.ResearchInstances.TERRAN;

@ApplicationScoped
public class ResearchRepository implements IResearchRepository {
    private static final List<Research> RESEARCH = Stream.of(
            TERRAN, FRONTIER
    ).sorted(Comparator.comparing(Research::label)).toList();

    @Override
    public List<Research> getAll() {
        return RESEARCH;
    }
}
