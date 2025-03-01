package io.tbbc.cf.bullet.effect;

import io.tbbc.cf.property.FinalProperties;

public interface IBulletEffect {
    FinalProperties getNewProperties(FinalProperties properties);
}
