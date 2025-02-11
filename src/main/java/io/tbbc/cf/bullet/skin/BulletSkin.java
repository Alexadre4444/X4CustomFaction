package io.tbbc.cf.bullet.skin;

import io.tbbc.cf.research.Research;

import java.util.List;

public record BulletSkin(String name, String label, BulletEgoSkinProps skinProps, List<Research> requiredResearch) {
}
