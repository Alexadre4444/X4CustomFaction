package io.tbbc.cf.mod;

import io.tbbc.cf.common.BadArgumentException;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModInfosValidator {
    void validateUpdate(ModInfosUpdate modInfosUpdate) {
        if (modInfosUpdate.factionTrigram().length() != 3) {
            throw new BadArgumentException("Faction trigram must be 3 characters long");
        }

        if (modInfosUpdate.customizePoints() < 50) {
            throw new BadArgumentException("Customize points must be at least 50");
        }

        if (modInfosUpdate.customizePoints() > 1000) {
            throw new BadArgumentException("Customize points must be at most 1000");
        }
    }
}
