package io.tbbc.cf.mod;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class ModInfos {
    @Id
    private long version;

    private String factionTrigram;

    private ZonedDateTime deploymentTime;

    private ResearchMode researchMode;

    private Integer customizePoints;

    public ModInfos() {
    }

    public Integer getCustomizePoints() {
        return customizePoints;
    }

    public void setCustomizePoints(int customizePoints) {
        this.customizePoints = customizePoints;
    }

    public ResearchMode getResearchMode() {
        return researchMode;
    }

    public void setResearchMode(ResearchMode researchMode) {
        this.researchMode = researchMode;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getFactionTrigram() {
        return factionTrigram;
    }

    public void setFactionTrigram(String factionTrigram) {
        this.factionTrigram = factionTrigram;
    }

    public ZonedDateTime getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(ZonedDateTime deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public ModVersion getEgoVersion() {
        return new ModVersion(version);
    }

    public enum ResearchMode {
        NO_RESEARCH,
        RESEARCH
    }
}
