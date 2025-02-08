package io.tbbc.cf.common.production;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ProductionMethodName extends PanacheEntity {
    private String name;

    public ProductionMethodName() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
