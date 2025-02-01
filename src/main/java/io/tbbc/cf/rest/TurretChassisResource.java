package io.tbbc.cf.rest;

import io.tbbc.cf.turret.chassis.ITurretChassisService;
import io.tbbc.cf.turret.chassis.TurretChassis;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/turrets_chassis")
public class TurretChassisResource {
    @Inject
    ITurretChassisService turretChassisService;

    @GET
    public List<TurretChassis> getAll() {
        return turretChassisService.getAll();
    }
}
