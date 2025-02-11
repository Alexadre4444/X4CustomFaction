package io.tbbc.cf.common.production;

import io.tbbc.cf.research.Research;

import java.util.List;

public record ProductionMethod(String name, String label, List<Research> requiredResearch) {
}
