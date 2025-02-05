package io.tbbc.cf.rest.model;

import io.tbbc.cf.common.production.ProductionMethodName;

import java.util.List;
import java.util.Map;

public class TurretComputation {
    String chassisName;
    String bulletName;
    Map<String, String> customizers;
    List<ProductionMethodName> productionMethodNames;

    public TurretComputation() {
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
