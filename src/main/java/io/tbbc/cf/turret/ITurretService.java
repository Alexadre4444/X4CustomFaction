package io.tbbc.cf.turret;

import io.tbbc.cf.common.property.FinalProperties;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITurretService {
    List<Turret> getAll();

    void insert(TurretCreate turret);

    void update(Turret turret);

    Optional<Turret> getById(long id);

    List<TurretEgoProps> getTurretEgoProps();

    FinalProperties computeFinalValues(String chassisName, String bulletName, Map<String, String> customizers);

    void delete(long id);

    void deployTurrets();
}
