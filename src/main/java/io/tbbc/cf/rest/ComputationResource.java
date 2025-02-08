package io.tbbc.cf.rest;

import io.tbbc.cf.common.property.FinalProperties;
import io.tbbc.cf.rest.model.TurretComputation;
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
    public FinalProperties computeTurretStats(TurretComputation turretComputation) {
        return turretService.computeFinalValues(turretComputation.getChassisName(),
                turretComputation.getBulletName(), turretComputation.getCustomizers(),
                turretComputation.getProductionMethodNames());
    }
}
