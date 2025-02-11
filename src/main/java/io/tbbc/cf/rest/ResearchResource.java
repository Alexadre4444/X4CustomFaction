package io.tbbc.cf.rest;

import io.tbbc.cf.research.IResearchService;
import io.tbbc.cf.research.Research;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/research")
public class ResearchResource {
    private final IResearchService researchService;

    @Inject
    public ResearchResource(IResearchService researchService) {
        this.researchService = researchService;
    }

    @GET
    public List<Research> getAll() {
        return researchService.getAll();
    }
}
