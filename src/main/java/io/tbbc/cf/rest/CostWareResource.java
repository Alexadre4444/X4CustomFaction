package io.tbbc.cf.rest;

import io.tbbc.cf.cost.CostWare;
import io.tbbc.cf.cost.ICostWareService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/cost_wares")
public class CostWareResource {
    @Inject
    ICostWareService costWareService;

    @GET
    public List<CostWare> getAll() {
        return costWareService.getAll();
    }
}
