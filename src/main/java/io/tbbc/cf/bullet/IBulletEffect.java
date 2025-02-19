package io.tbbc.cf.bullet;

import io.tbbc.cf.property.FinalProperties;

public interface IBulletEffect {
    FinalProperties getNewProperties(FinalProperties properties);
}
