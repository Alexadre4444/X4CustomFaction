package io.tbbc.cf.turret;

import io.tbbc.cf.bullet.influence.Influence;
import io.tbbc.cf.bullet.skin.BulletEgoSkinProps;
import io.tbbc.cf.common.LangEntry;
import io.tbbc.cf.property.FinalPropValue;
import io.tbbc.cf.property.FinalProperties;
import io.tbbc.cf.research.Research;
import io.tbbc.cf.turret.chassis.ChassisType;

import java.math.BigDecimal;
import java.util.List;

public record TurretEgoProps(String name, int labelSection, int descriptionSection, int index, String size,
                             String macroName, String aliasMacroName, String label, String description,
                             String skinComponent, String aliasComponent, String bulletMacroName,
                             FinalProperties properties, BulletEgoSkinProps bulletEgoSkinProps,
                             ChassisType chassisType, TurretEgoType turretEgoType, LangEntry langEntryName,
                             LangEntry langEntryBaseName, LangEntry langEntryShortName,
                             List<Research> requiredResearch, Influence influence) {

    public TurretRange turretRange() {
        FinalPropValue range;
        if (properties.hasProperty("range")) {
            range = properties.property("range");
        } else {
            range = properties.property("beamRange");
        }
        return range.getFinalValue().compareTo(new BigDecimal(2500)) < 0 ? TurretRange.SHORT :
                range.getFinalValue().compareTo(new BigDecimal(5000)) < 0 ? TurretRange.MID : TurretRange.LONG;
    }

    public enum TurretRange {
        SHORT("turret_shortRange"),
        MID("turret_midRange"),
        LONG("turret_longRange");
        private final String egoName;

        TurretRange(String egoName) {
            this.egoName = egoName;
        }

        public String egoName() {
            return egoName;
        }
    }
}
