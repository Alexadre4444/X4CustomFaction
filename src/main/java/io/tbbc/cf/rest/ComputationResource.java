package io.tbbc.cf.rest;

import io.tbbc.cf.rest.model.TurretComputation;
import io.tbbc.cf.turret.ComputationResult;
import io.tbbc.cf.turret.ITurretService;
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
}
