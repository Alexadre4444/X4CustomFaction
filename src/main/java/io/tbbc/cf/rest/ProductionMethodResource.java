package io.tbbc.cf.rest;

import io.tbbc.cf.common.production.IProductionMethodService;
import io.tbbc.cf.common.production.ProductionMethod;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/production_methods")
public class ProductionMethodResource {
    @Inject
    IProductionMethodService productionMethodService;

    @GET
    public List<ProductionMethod> getAll() {
        return productionMethodService.getAll();
    }
}
