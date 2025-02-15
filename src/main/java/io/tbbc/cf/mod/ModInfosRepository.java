package io.tbbc.cf.mod;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ModInfosRepository implements IModInfosRepository, PanacheRepository<ModInfos> {
    @Override
    public List<ModInfos> getAll() {
        return listAll();
    }

    private Optional<ModInfos> get(long version) {
        return find("version", version).firstResultOptional();
    }

    @Override
    public void update(ModInfos infoToUpdate) {
        ModInfos modInfos = get(infoToUpdate.getVersion())
                .orElseThrow(() -> new IllegalArgumentException("Mod version not found"));
        modInfos.setFactionTrigram(infoToUpdate.getFactionTrigram());
        modInfos.setDeploymentTime(infoToUpdate.getDeploymentTime());
        modInfos.setResearchMode(infoToUpdate.getResearchMode());
    }

    @Override
    public void insert(ModInfos modInfos) {
        persist(modInfos);
    }
}
