package io.tbbc.cf.rest;

import io.tbbc.cf.common.customizer.CustomizerComponent;
import io.tbbc.cf.turret.custom.ITurretCustomizerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/turrets_customizer")
public class TurretCustomizerResource {
    @Inject
    ITurretCustomizerService turretCustomizerService;

    @GET
    public List<CustomizerComponent> getAll() {
        return turretCustomizerService.getAll();
    }
}
