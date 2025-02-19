package io.tbbc.cf.research;

import io.tbbc.cf.cost.CostEntry;

import java.util.List;

public record Research(String name, String label, String description, int researchTime, List<CostEntry> costs,
                       List<Research> parents) {
}
