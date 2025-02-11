package io.tbbc.cf.turret.chassis.skin;

import io.tbbc.cf.research.Research;

import java.util.List;

public record ChassisSkin(String name, String label, ChassisEgoSkinProps egoSkinProps,
                          List<Research> requiredResearch) {
}
