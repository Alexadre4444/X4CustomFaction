package io.tbbc.cf.rest;

import io.tbbc.cf.common.customizer.CustomizerCategory;
import io.tbbc.cf.common.customizer.ICustomizerCategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/customizers_categories")
public class CustomizerCategoryResource {
    @Inject
    ICustomizerCategoryService customizerCategoryService;

    @GET
    public List<CustomizerCategory> getAll() {
        return customizerCategoryService.getAll();
    }
}
