package io.tbbc.cf.mod;

import io.quarkus.runtime.Startup;
import io.tbbc.cf.common.BadArgumentException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.ZonedDateTime;
import java.util.Comparator;

@ApplicationScoped
public class ModInfosService implements IModInfosService {
    @Inject
    private IModInfosRepository modInfosRepository;

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
        modInfosRepository.insert(newModInfos);

        return newModInfos;
    }

    @Override
    @Transactional
    public void setFactionTrigram(String factionTrigram) {
        factionTrigram = factionTrigram.toUpperCase().trim();
        validateFactionTrigram(factionTrigram);
        ModInfos actualModInfos = getActualModInfos();
        actualModInfos.setFactionTrigram(factionTrigram);
        modInfosRepository.update(actualModInfos);
    }

    private void validateFactionTrigram(String factionTrigram) {
        if (factionTrigram.length() != 3) {
            throw new BadArgumentException("Faction trigram must be 3 characters long");
        }
    }
}
