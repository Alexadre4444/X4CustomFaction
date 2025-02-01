package io.tbbc.cf.rest;

import io.tbbc.cf.mod.IModInfosService;
import io.tbbc.cf.mod.IModService;
import io.tbbc.cf.mod.ModInfos;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

@Path("/mod")
public class ModResource {

    @Inject
    IModService modService;

    @Inject
    IModInfosService modInfosService;

    @PUT
    @Path("/generate")
    public void generate() {
        modService.generateNewVersion();
    }

    @GET
    public ModInfos getInfos() {
        return modInfosService.getActualModInfos();
    }

    @POST
    public void setInfos(ModInfos infos) {
        modInfosService.setFactionTrigram(infos.getFactionTrigram());
    }
}
