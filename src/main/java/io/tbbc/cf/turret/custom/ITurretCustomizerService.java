package io.tbbc.cf.turret.custom;

import io.tbbc.cf.common.customizer.CustomizerComponent;

import java.util.List;

public interface ITurretCustomizerService {
    List<CustomizerComponent> getAll();

    CustomizerComponent getByName(String name);
}
