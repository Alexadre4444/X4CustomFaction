package io.tbbc.cf.mod;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.ZonedDateTime;
import java.util.Comparator;

@ApplicationScoped
public class ModInfosService implements IModInfosService {
    @Inject
    private IModInfosRepository modInfosRepository;

    @Inject
    private ModInfosValidator modInfosValidator;

    @Startup
    @Transactional
    void atStartup() {
        if (modInfosRepository.getAll().stream()
                .findFirst()
                .isEmpty()) {
            ModInfos modInfos = new ModInfos();
            modInfos.setVersion(1);
            modInfos.setFactionTrigram("TBB");
            modInfosRepository.insert(modInfos);
        }
    }

    @Override
    public ModInfos getActualModInfos() {
        return modInfosRepository.getAll().stream()
                .max(Comparator.comparing(ModInfos::getVersion))
                .orElseThrow(() -> new IllegalArgumentException("No mod infos found"));
    }

    @Override
    @Transactional
    public ModInfos deployAndGenerateNewVersion() {
        ModInfos actualModInfos = getActualModInfos();
        actualModInfos.setDeploymentTime(ZonedDateTime.now());
        modInfosRepository.update(actualModInfos);

        ModInfos newModInfos = new ModInfos();
        newModInfos.setVersion(actualModInfos.getVersion() + 1);
        newModInfos.setFactionTrigram(actualModInfos.getFactionTrigram());
        newModInfos.setCustomizePoints(actualModInfos.getCustomizePoints());
        newModInfos.setResearchMode(actualModInfos.getResearchMode());
        modInfosRepository.insert(newModInfos);

        return newModInfos;
    }

    @Override
    @Transactional
    public void updateInfos(ModInfosUpdate modInfosUpdate) {
        String factionTrigram = modInfosUpdate.factionTrigram().toUpperCase().trim();
        modInfosValidator.validateUpdate(modInfosUpdate);
        ModInfos actualModInfos = getActualModInfos();
        actualModInfos.setFactionTrigram(factionTrigram);
        actualModInfos.setResearchMode(modInfosUpdate.researchMode());
        actualModInfos.setCustomizePoints(modInfosUpdate.customizePoints());
        modInfosRepository.update(actualModInfos);
    }
}
