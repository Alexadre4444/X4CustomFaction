package io.tbbc.cf.turret;

import io.tbbc.cf.common.Size;
import io.tbbc.cf.common.State;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Turret extends TurretCreate {
    @Id
    @GeneratedValue
    private long id;
    private String label;
    private String description;
    private String chassisName;
    private String chassisSkinName;
    private String bulletName;
    private String bulletSkinName;
    private State state;
    private Size size;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CustomizerValue> customizers = List.of();

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<CustomizerValue> getCustomizers() {
        return customizers;
    }

    public void setCustomizers(List<CustomizerValue> customizers) {
        this.customizers = customizers;
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

    public String getName() {
        return "turret_%s".formatted(getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
