package io.tbbc.cf.turret;

import io.tbbc.cf.property.FinalProperties;
import io.tbbc.cf.research.Research;

import java.util.List;

public record ComputationResult(FinalProperties finalProperties, List<Research> requiredResearch) {
}
