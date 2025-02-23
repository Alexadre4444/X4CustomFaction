package io.tbbc.cf.turret;

import io.tbbc.cf.production.ProductionMethodName;
import io.tbbc.cf.turret.computation.ComputationResult;
import io.tbbc.cf.turret.computation.ComputationResultFree;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITurretService {
    List<Turret> getAll();

    void insert(TurretCreate turret);

    void update(Turret turret);

    Optional<Turret> getById(long id);

    List<TurretEgoProps> getTurretEgoProps();

    ComputationResult computeFinalProperties(String chassisName, String chassisSkinName, String bulletName, String bulletSkinName,
                                             Map<String, String> customizers,
                                             List<ProductionMethodName> productionMethodNames);

    ComputationResultFree computeFinalPropertiesFree(String chassisName, String chassisSkinName, String bulletName, String bulletSkinName,
                                                     Map<String, Integer> customizers,
                                                     List<ProductionMethodName> productionMethodNames);

    void delete(long id);

    void deployTurrets();
}
