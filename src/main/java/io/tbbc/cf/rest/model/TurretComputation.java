package io.tbbc.cf.rest.model;

import io.tbbc.cf.production.ProductionMethodName;

import java.util.List;
import java.util.Map;

public class TurretComputation {
    String chassisName;
    String bulletName;
    String chassisSkinName;
    String bulletSkinName;
    Map<String, String> customizers;
    List<ProductionMethodName> productionMethodNames;

    public TurretComputation() {
    }

    public String getChassisSkinName() {
        return chassisSkinName;
    }

    public void setChassisSkinName(String chassisSkinName) {
        this.chassisSkinName = chassisSkinName;
    }

    public String getBulletSkinName() {
        return bulletSkinName;
    }

    public void setBulletSkinName(String bulletSkinName) {
        this.bulletSkinName = bulletSkinName;
    }

    public List<ProductionMethodName> getProductionMethodNames() {
        return productionMethodNames;
    }

    public void setProductionMethodNames(List<ProductionMethodName> productionMethodNames) {
        this.productionMethodNames = productionMethodNames;
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
