package io.tbbc.cf.rest;

import io.tbbc.cf.common.property.PropertyDefinition;
import io.tbbc.cf.turret.chassis.ITurretChassisService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/turrets_properties")
public class TurretPropertyResource {
    @Inject
    ITurretChassisService turretChassisService;

    @GET
    public List<PropertyDefinition> getProperties() {
        return turretChassisService.getProperties();
    }
}
