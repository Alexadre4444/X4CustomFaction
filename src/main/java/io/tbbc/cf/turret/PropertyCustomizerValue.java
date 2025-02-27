package io.tbbc.cf.turret;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class PropertyCustomizerValue extends PanacheEntity {
    private String propertyName;
    private Integer propertyModifier;

    public PropertyCustomizerValue() {
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getPropertyModifier() {
        return propertyModifier;
    }

    public void setPropertyModifier(Integer propertyModifier) {
        this.propertyModifier = propertyModifier;
    }
}
