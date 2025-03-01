package io.tbbc.cf.bullet.influence;

public class InfluenceInstances {
    public static final Influence LIGHT_THRUSTER_DISRUPT = new Influence("light_thruster_disrupt",
            "Light Thruster Disrupt",
            "On impact, this weapon decreases manoeuvrability for a short period of time. The effect is most noticeable against small ships; " +
                    "less so on medium and capital ships.");
    public static final Influence LIGHT_DRIVE_DISRUPT_FLAK = new Influence("light_drive_disrupt_flak",
            "Light Drive Disrupt",
            "On impact, this weapon decreases the speed of the target for a short period of time. The effect is most noticeable against small ships; " +
                    "less so on medium and capital ships.");
    public static final Influence HEAVY_MAM_THRUSTER_DISRUPT = new Influence("heavy_mam_thruster_disrupt",
            "Heavy M/AM and Thruster Disrupt",
            "Any target caught in the blast of this weapon will have its boost and travel capabilities disabled, as well as suffering " +
                    "decreased manoeuvrability for a short period of time.");

    private InfluenceInstances() {
    }
}
