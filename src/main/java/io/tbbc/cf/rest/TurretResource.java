package io.tbbc.cf.rest;

import io.tbbc.cf.common.NotFoundException;
import io.tbbc.cf.turret.ITurretService;
import io.tbbc.cf.turret.Turret;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/turrets")
public class TurretResource {
    @Inject
    ITurretService turretService;

    @GET
    public List<Turret> getAll() {
        return turretService.getAll();
    }

    @GET
    @Path("/{id}")
    public Turret get(long id) {
        return turretService.getById(id)
                .orElseThrow(() -> new NotFoundException("Turret with id '%s' not found.".formatted(id)));
    }

    @PUT
    public void insert(Turret turret) {
        turretService.insert(turret);
    }

    @POST
    @Path("/{id}")
    public void update(Turret turret) {
        turret.setId(turret.getId()); // Ensure the name is the same as the path parameter
        turretService.update(turret);
    }

    @DELETE
    @Path("/{id}")
    public void delete(long id) {
        turretService.delete(id);
    }
}
