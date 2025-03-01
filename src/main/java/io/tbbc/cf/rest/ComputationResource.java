package io.tbbc.cf.rest;

import io.tbbc.cf.rest.model.TurretComputation;
import io.tbbc.cf.rest.model.TurretComputationFree;
import io.tbbc.cf.turret.ITurretService;
import io.tbbc.cf.turret.computation.ComputationResult;
import io.tbbc.cf.turret.computation.ComputationResultFree;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/computation")
public class ComputationResource {
    @Inject
    ITurretService turretService;

    @POST
    @Path("/turret")
    public ComputationResult computeTurretStats(TurretComputation turretComputation) {
        return turretService.computeFinalProperties(turretComputation.getChassisName(),
                turretComputation.getChassisSkinName(), turretComputation.getBulletName(),
                turretComputation.getBulletSkinName(), turretComputation.getCustomizers(),
                turretComputation.getProductionMethodNames());
    }

    @POST
    @Path("/turret_free")
    public ComputationResultFree computeTurretStatsFree(TurretComputationFree turretComputation) {
        return turretService.computeFinalPropertiesFree(turretComputation.getChassisName(),
                turretComputation.getChassisSkinName(), turretComputation.getBulletName(),
                turretComputation.getBulletSkinName(), turretComputation.getCustomizers(),
                turretComputation.getProductionMethodNames());
    }
}
