package io.tbbc.cf.rest.model;

import java.util.Map;

public class TurretComputation {
    String chassisName;
    String bulletName;
    Map<String, String> customizers;

    public TurretComputation() {
    }

    public Map<String, String> getCustomizers() {
        return customizers;
    }

    public void setCustomizers(Map<String, String> customizers) {
        this.customizers = customizers;
    }

    public String getChassisName() {
        return chassisName;
    }

    public void setChassisName(String chassisName) {
        this.chassisName = chassisName;
    }

    public String getBulletName() {
        return bulletName;
    }

    public void setBulletName(String bulletName) {
        this.bulletName = bulletName;
    }
}
