package io.tbbc.cf.turret;

import io.tbbc.cf.common.production.ProductionMethodName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITurretService {
    List<Turret> getAll();

    void insert(TurretCreate turret);

    void update(Turret turret);

    Optional<Turret> getById(long id);

    List<TurretEgoProps> getTurretEgoProps();

    ComputationResult computeFinalProperties(String chassisName, String chassisSkin, String bulletName, String bulletSkin,
                                             Map<String, String> customizers,
                                             List<ProductionMethodName> productionMethodNames);

    void delete(long id);

    void deployTurrets();
}
