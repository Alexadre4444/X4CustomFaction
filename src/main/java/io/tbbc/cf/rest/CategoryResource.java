package io.tbbc.cf.rest;

import io.tbbc.cf.property.Category;
import io.tbbc.cf.property.ICategoryServices;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/categories")
public class CategoryResource {
    @Inject
    ICategoryServices categoryServices;

    @GET
    public List<Category> getAll() {
        return categoryServices.getAll();
    }
}
