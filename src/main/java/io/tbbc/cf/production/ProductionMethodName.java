package io.tbbc.cf.production;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ProductionMethodName extends PanacheEntity {
    private String name;

    public ProductionMethodName() {
    }

    public ProductionMethodName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
