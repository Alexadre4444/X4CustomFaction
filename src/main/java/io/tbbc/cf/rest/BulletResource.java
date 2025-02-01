package io.tbbc.cf.rest;


import io.tbbc.cf.bullet.Bullet;
import io.tbbc.cf.bullet.IBulletService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/bullets")
public class BulletResource {
    @Inject
    IBulletService bulletTemplateService;

    @GET
    public List<Bullet> getAll() {
        return bulletTemplateService.getAll();
    }
}
