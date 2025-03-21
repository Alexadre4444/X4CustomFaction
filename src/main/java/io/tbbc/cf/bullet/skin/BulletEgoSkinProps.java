package io.tbbc.cf.bullet.skin;

public enum BulletEgoSkinProps {
    // Pulse - M
    BLUE_M_PULSE("bullet_gen_s_laser_01_mk1", null,
            new BulletEffect("impact_gen_s_laser_01_mk1", "impact_gen_s_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_s_laser_01_mk1_bigobject", "impact_gen_s_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_laser_01_mk1", null)),
    BLUE_M_PULSE_ION("bullet_arg_s_ion_01_mk1", null,
            new BulletEffect("impact_arg_s_ion_01_mk1", "impact_arg_s_ion_01_mk1_inside"),
            new BulletEffect("impact_arg_s_ion_01_mk1_bigobject", "impact_arg_s_ion_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_arg_s_ion_01_mk1", null)),
    BORON_M_PULSE_ION("bullet_bor_s_railgun_01_mk1", null,
            new BulletEffect("impact_bor_m_railgun_01_mk1", "impact_bor_m_railgun_01_mk1_inside"),
            new BulletEffect("impact_bor_m_railgun_01_mk1_bigobject", "impact_bor_m_railgun_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_bor_m_railgun_01_mk1", null)),
    RED_M_PULSE("bullet_xen_s_laser_01_mk1", null,
            new BulletEffect("impact_xen_s_laser_01_mk1", "impact_xen_s_laser_01_mk1_inside"),
            new BulletEffect("impact_xen_s_laser_01_mk1_bigobject", "impact_xen_s_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_xen_m_laser_01_mk1", null)),
    // Pulse - L
    BLUE_L_PULSE("bullet_arg_l_laser_01_mk1", null,
            new BulletEffect("impact_gen_l_laser_01_mk1", "impact_gen_l_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_l_laser_01_mk1_bigobject", "impact_gen_l_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_l_laser_01_mk1", null)),
    YELLOW_L_PULSE("bullet_tel_l_laser_01_mk1", null,
            new BulletEffect("impact_gen_l_laser_01_mk1", "impact_gen_l_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_l_laser_01_mk1_bigobject", "impact_gen_l_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_l_laser_01_mk1", null)),
    VIOLET_L_PULSE("bullet_par_l_laser_01_mk1", null,
            new BulletEffect("impact_gen_l_laser_01_mk1", "impact_gen_l_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_l_laser_01_mk1_bigobject", "impact_gen_l_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_l_laser_01_mk1", null)),
    RED_L_PULSE("bullet_xen_l_laser_01_mk1", "turret_xen_l_laser_01_mk1_flyby",
            new BulletEffect("impact_xen_l_laser_01_mk1", "impact_xen_l_laser_01_mk1_inside"),
            new BulletEffect("impact_xen_l_laser_01_mk1_bigobject", "impact_xen_l_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_xen_l_laser_01_mk1", null)),
    ORANGE_L_PULSE("bullet_spl_l_laser_01_mk1", null,
            new BulletEffect("impact_gen_l_laser_01_mk1", "impact_gen_l_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_l_laser_01_mk1_bigobject", "impact_gen_l_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_l_laser_01_mk1", null)),
    WHITE_L_PULSE("bullet_ter_l_laser_01_mk1", null,
            new BulletEffect("impact_gen_l_laser_01_mk1", "impact_gen_l_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_l_laser_01_mk1_bigobject", "impact_gen_l_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_ter_l_laser_01_mk1", null)),

    // Plasma - L
    BLUE_L_PLASMA("bullet_arg_l_plasma_01_mk1", null,
            new BulletEffect("impact_gen_m_plasma_01_mk1", "impact_gen_m_plasma_01_mk1_inside"),
            new BulletEffect("impact_gen_m_plasma_01_mk1_bigobject", "impact_gen_m_plasma_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_plasma_01_mk1", null)),
    YELLOW_L_PLASMA("bullet_tel_l_plasma_01_mk1", null,
            new BulletEffect("impact_gen_m_plasma_01_mk1", "impact_gen_m_plasma_01_mk1_inside"),
            new BulletEffect("impact_gen_m_plasma_01_mk1_bigobject", "impact_gen_m_plasma_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_plasma_01_mk1", null)),
    VIOLET_L_PLASMA("bullet_par_l_plasma_01_mk1", null,
            new BulletEffect("impact_gen_m_plasma_01_mk1", "impact_gen_m_plasma_01_mk1_inside"),
            new BulletEffect("impact_gen_m_plasma_01_mk1_bigobject", "impact_gen_m_plasma_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_plasma_01_mk1", null)),
    ORANGE_L_PLASMA("bullet_spl_l_plasma_01_mk1", null,
            new BulletEffect("impact_gen_m_plasma_01_mk1", "impact_gen_m_plasma_01_mk1_inside"),
            new BulletEffect("impact_gen_m_plasma_01_mk1_bigobject", "impact_gen_m_plasma_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_plasma_01_mk1", null)),

    // Plasma - M
    PLASMA_M_CW("bullet_gen_s_plasma_01_mk1", null,
            new BulletEffect("impact_gen_s_plasma_01_mk1", "impact_gen_s_plasma_01_mk1_inside"),
            new BulletEffect("impact_gen_s_plasma_01_mk1_bigobject", "impact_gen_s_plasma_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_plasma_01_mk1", null)),
    // Gatling - M
    CW_M_GATLING("bullet_gen_s_gatling_01_mk1", "turret_gatling_muzzle",
            new BulletEffect("impact_gen_s_gatling_01_mk1", "impact_gen_s_gatling_01_mk1_inside"),
            new BulletEffect("impact_gen_s_gatling_01_mk1_bigobject", "impact_gen_s_gatling_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_gatling_01_mk1", null)),
    XEN_M_GATLING("bullet_xen_s_gatling_01_mk1", null,
            new BulletEffect("impact_xen_s_gatling_01_mk1", "impact_xen_s_gatling_01_mk1_inside"),
            new BulletEffect("impact_xen_s_gatling_01_mk1_bigobject", "impact_xen_s_gatling_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_xen_s_gatling_01_mk1", null)),
    TER_M_GATLING("bullet_ter_turret_m_gatling_01_mk1", null,
            new BulletEffect("impact_ter_s_gatling_01_mk1", "impact_ter_s_gatling_01_mk1_inside"),
            new BulletEffect("impact_ter_s_gatling_01_mk1_bigobject", "impact_ter_s_gatling_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_ter_s_gatling_01_mk1", null)),
    // Gatling - L
    TER_L_GATLING("bullet_ter_l_gatling_01_mk1", null,
            new BulletEffect("impact_ter_l_gatling_01_mk1", "impact_ter_l_gatling_01_mk1_inside"),
            new BulletEffect("impact_ter_l_gatling_01_mk1_bigobject", "impact_ter_l_gatling_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_ter_l_gatling_01_mk1", null)),

    // Shotgun - M
    CW_M_SHOTGUN("bullet_gen_s_shotgun_01_mk1", null,
            new BulletEffect("impact_gen_s_shotgun_01_mk1", "impact_gen_s_shotgun_01_mk1_inside"),
            new BulletEffect("impact_gen_s_shotgun_01_mk1_bigobject", "impact_gen_s_shotgun_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_shotgun_01_mk1", null)),
    // Beam - M
    CW_M_BEAM("bullet_gen_s_beam_01_mk1", "turret_beam_muzzle",
            new BulletEffect("impact_gen_m_beam_01_mk1", "impact_gen_m_beam_01_mk1_inside"),
            new BulletEffect("impact_gen_m_beam_01_mk1_bigobject", "impact_gen_m_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_beam_01_mk1", null)),
    CW_M_BEAM_MINING("bullet_gen_s_mining_01_mk1", null,
            new BulletEffect("impact_gen_s_mining_01_mk1", "impact_gen_s_mining_01_mk1_inside"),
            new BulletEffect("impact_gen_s_mining_01_mk1_bigobject", "impact_gen_s_mining_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_gen_s_mining_01_mk1", null)),
    KHA_M_BEAM("bullet_kha_m_beam_01", "wpn_beam_khaak",
            new BulletEffect("impact_gen_m_beam_01_mk1", "impact_gen_m_beam_01_mk1_inside"),
            new BulletEffect("impact_gen_m_beam_01_mk1_bigobject", "impact_gen_m_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_beam_01_mk1", null)),
    TER_M_BEAM("bullet_ter_turret_m_beam_01_mk1", "weapon_ter_s_beam_loop",
            new BulletEffect("impact_gen_s_beam_01_mk1", "impact_gen_s_beam_01_mk1_inside"),
            new BulletEffect("impact_gen_s_beam_01_mk1_bigobject", "impact_gen_s_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_ter_s_beam_01_mk1", null)),

    // Beam - L
    ARG_L_BEAM("bullet_arg_l_beam_01_mk1", null,
            new BulletEffect("impact_gen_m_beam_01_mk1", "impact_gen_m_beam_01_mk1_inside"),
            new BulletEffect("impact_gen_m_beam_01_mk1_bigobject", "impact_gen_m_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_beam_01_mk1", null)),
    TEL_L_BEAM("bullet_tel_l_beam_01_mk1", null,
            new BulletEffect("impact_gen_m_beam_01_mk1", "impact_gen_m_beam_01_mk1_inside"),
            new BulletEffect("impact_gen_m_beam_01_mk1_bigobject", "impact_gen_m_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_beam_01_mk1", null)),
    PAR_L_BEAM("bullet_par_l_beam_01_mk1", null,
            new BulletEffect("impact_gen_m_beam_01_mk1", "impact_gen_m_beam_01_mk1_inside"),
            new BulletEffect("impact_gen_m_beam_01_mk1_bigobject", "impact_gen_m_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_beam_01_mk1", null)),
    KHA_L_BEAM("bullet_kha_l_beam_01_mk1", "wpn_beam_khaak",
            new BulletEffect("impact_kha_l_beam_01_mk1", "impact_kha_l_beam_01_mk1_inside"),
            new BulletEffect("impact_kha_l_beam_01_mk1_bigobject", "impact_kha_l_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_kha_l_beam_01_mk1", null)),
    SPL_L_BEAM("bullet_spl_l_beam_01_mk1", null,
            new BulletEffect("impact_gen_m_beam_01_mk1", "impact_gen_m_beam_01_mk1_inside"),
            new BulletEffect("impact_gen_m_beam_01_mk1_bigobject", "impact_gen_m_beam_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_turret_gen_m_beam_01_mk1", null)),
    TER_L_BEAM("bullet_ter_l_beam_01_mk1", null,
            new BulletEffect("impact_gen_l_laser_01_mk1", "impact_gen_l_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_l_laser_01_mk1_bigobject", "impact_gen_l_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_ter_l_beam_01_mk1", null)),

    // Electromagnetic - M
    TER_M_ELECTROMAGNETIC("bullet_ter_s_laser_02_mk1", null,
            new BulletEffect("impact_ter_s_laser_02_mk1", "impact_ter_s_laser_02_mk1_inside"),
            new BulletEffect("impact_ter_s_laser_02_mk1_bigobject", "impact_ter_s_laser_02_mk1_bigobject_inside"),
            new BulletEffect("muzzle_ter_s_laser_02_mk1", null)),

    // Flak - M
    CW_M_FLAK("bullet_gen_m_flak_01_mk1", null,
            new BulletEffect("impact_gen_m_flak_01_mk1", null),
            null,
            new BulletEffect("muzzle_turret_gen_m_flak_01_mk1", null)),

    // Arc - M
    BOR_M_ARC("bullet_bor_s_arc_01_mk1", null,
            new BulletEffect("impact_bor_s_arc_01_mk1", "impact_bor_s_arc_01_mk1_inside"),
            new BulletEffect("impact_bor_s_arc_01_mk1_bigobject", "impact_bor_s_arc_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_bor_s_arc_01_mk1", null)),

    // Phase - M
    BOR_M_PHASE("bullet_bor_s_laser_01_mk1", null,
            new BulletEffect("impact_gen_s_laser_01_mk1", "impact_gen_s_laser_01_mk1_inside"),
            new BulletEffect("impact_gen_s_laser_01_mk1_bigobject", "impact_gen_s_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_bor_s_laser_mk1", null)),

    // Phase - L
    BOR_L_PHASE("bullet_bor_m_laser_01_mk1", null,
            new BulletEffect("impact_bor_m_laser_01_mk1", "impact_bor_m_laser_01_mk1_inside"),
            new BulletEffect("impact_bor_m_laser_01_mk1_bigobject", "impact_bor_m_laser_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_bor_m_laser_mk1", null)),

    // Seismic - L
    XEN_L_SEISMIC("bullet_xen_l_plasma_01_mk1", "turret_xen_l_plasma_01_mk1_flyby",
            new BulletEffect("impact_xen_l_plasma_01_mk1", "impact_xen_l_plasma_01_mk1_inside"),
            new BulletEffect("impact_xen_l_plasma_01_mk1_bigobject", "impact_xen_l_plasma_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_xen_l_plasma_01_mk1", null)),

    // Flak - L
    BOR_L_FLAK("bullet_bor_l_flak_01_mk1", null,
            new BulletEffect("impact_bor_l_flak_01_mk1", "impact_bor_l_flak_01_mk1_inside"),
            new BulletEffect("impact_bor_l_flak_01_mk1_bigobject", "impact_bor_l_flak_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_bor_l_flak_01_mk1", null)),

    // Disruptor - L
    BOR_L_DISRUPTOR("bullet_bor_l_disruptor_01_mk1", null,
            new BulletEffect("impact_bor_l_disruptor_01_mk1", "impact_bor_l_disruptor_01_mk1_inside"),
            new BulletEffect("impact_bor_l_disruptor_01_mk1_bigobject", "impact_bor_l_disruptor_01_mk1_bigobject_inside"),
            new BulletEffect("muzzle_bor_l_disruptor_01_mk1", null));

    private final String componentName;
    private final String soundName;
    private final BulletEffect impact;
    private final BulletEffect bigObjectImpact;
    private final BulletEffect launch;

    BulletEgoSkinProps(String componentName, String soundName, BulletEffect impact, BulletEffect bigObjectImpact, BulletEffect launch) {
        this.componentName = componentName;
        this.soundName = soundName;
        this.impact = impact;
        this.bigObjectImpact = bigObjectImpact;
        this.launch = launch;
    }

    public String componentName() {
        return componentName;
    }

    public BulletEffect impact() {
        return impact;
    }

    public BulletEffect bigObjectImpact() {
        return bigObjectImpact;
    }

    public BulletEffect launch() {
        return launch;
    }

    public String soundName() {
        return soundName;
    }
}
