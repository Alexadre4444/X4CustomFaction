package io.tbbc.cf.production;

import io.tbbc.cf.research.ResearchInstances;

import java.util.List;

import static io.tbbc.cf.research.ResearchInstances.RESEARCH_CLOSED_LOOP_PRODUCTION;

public class ProductionMethodInstances {
    public static ProductionMethod CW = new ProductionMethod("commonWealth", "Common Wealth", List.of());
    public static ProductionMethod TERRAN = new ProductionMethod("terran", "Terran",
            List.of(ResearchInstances.RESEARCH_TERRAN_PRODUCTION));
    public static ProductionMethod CLOSED_LOOP = new ProductionMethod("closedLoop", "Closed Loop",
            List.of(RESEARCH_CLOSED_LOOP_PRODUCTION));

    private ProductionMethodInstances() {
    }
}
