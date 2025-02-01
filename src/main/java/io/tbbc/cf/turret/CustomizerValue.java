package io.tbbc.cf.turret;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CustomizerValue extends PanacheEntity {
    private String categoryName;
    private String customizerName;

    public CustomizerValue() {
    }

    public String getCustomizerName() {
        return customizerName;
    }

    public void setCustomizerName(String customizerName) {
        this.customizerName = customizerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
