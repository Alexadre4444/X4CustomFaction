package io.tbbc.cf.mod;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ModInfosRepository implements IModInfosRepository, PanacheRepository<ModInfos> {
    @Override
    public List<ModInfos> getAll() {
        List<ModInfos> allModInfos = listAll();
        allModInfos.forEach(this::setDefaultResearchMode);
        allModInfos.forEach(this::setDefaultCustomizePoints);
        return allModInfos;
    }

    private void setDefaultResearchMode(ModInfos modInfos) {
        if (modInfos.getResearchMode() == null) {
            modInfos.setResearchMode(ModInfos.ResearchMode.NO_RESEARCH);
        }
    }

    private void setDefaultCustomizePoints(ModInfos modInfos) {
        if (modInfos.getCustomizePoints() == null) {
            modInfos.setCustomizePoints(150);
        }
    }

    private Optional<ModInfos> get(long version) {
        return find("version", version).firstResultOptional().map(modInfos -> {
            setDefaultResearchMode(modInfos);
            setDefaultCustomizePoints(modInfos);
            return modInfos;
        });
    }

    @Override
    public void update(ModInfos infoToUpdate) {
        ModInfos modInfos = get(infoToUpdate.getVersion())
                .orElseThrow(() -> new IllegalArgumentException("Mod version not found"));
        modInfos.setFactionTrigram(infoToUpdate.getFactionTrigram());
        modInfos.setDeploymentTime(infoToUpdate.getDeploymentTime());
        modInfos.setResearchMode(infoToUpdate.getResearchMode());
        modInfos.setCustomizePoints(infoToUpdate.getCustomizePoints());
    }

    @Override
    public void insert(ModInfos modInfos) {
        persist(modInfos);
    }
}
