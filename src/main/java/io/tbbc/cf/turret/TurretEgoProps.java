package io.tbbc.cf.turret;

import io.tbbc.cf.bullet.skin.BulletEgoSkinProps;
import io.tbbc.cf.common.LangEntry;
import io.tbbc.cf.common.property.FinalPropValue;
import io.tbbc.cf.common.property.FinalProperties;
import io.tbbc.cf.turret.chassis.ChassisType;

public record TurretEgoProps(String name, int labelSection, int descriptionSection, int index, String size,
                             String macroName, String aliasMacroName, String label, String description,
                             String skinComponent, String aliasComponent, String bulletMacroName,
                             FinalProperties properties, BulletEgoSkinProps bulletEgoSkinProps,
                             ChassisType chassisType, TurretEgoType turretEgoType, LangEntry langEntryName,
                             LangEntry langEntryBaseName, LangEntry langEntryShortName) {

    public TurretRange turretRange() {
        FinalPropValue range;
        if (properties.hasProperty("range")) {
            range = properties.property("range");
        } else {
            range = properties.property("beamRange");
        }
        return range.getFinalDoubleValue() < 2500 ? TurretRange.SHORT :
                range.getFinalDoubleValue() < 5000 ? TurretRange.MID : TurretRange.LONG;
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
