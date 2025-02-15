package io.tbbc.cf.research;

import io.tbbc.cf.common.LangEntry;
import io.tbbc.cf.cost.CostEntry;

import java.util.List;

public record ResearchEgoProp(String name, int labelSection, int descriptionSection, LangEntry langEntryName,
                              LangEntry langEntryDescription, int researchTime, List<CostEntry> costs,
                              List<Research> parents) {
}
