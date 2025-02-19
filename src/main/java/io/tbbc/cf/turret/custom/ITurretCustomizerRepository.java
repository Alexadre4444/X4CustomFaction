package io.tbbc.cf.turret.custom;

import io.tbbc.cf.customizer.CustomizerComponent;

import java.util.List;

public interface ITurretCustomizerRepository {
    List<CustomizerComponent> getAll();

    CustomizerComponent getByName(String name);
}
