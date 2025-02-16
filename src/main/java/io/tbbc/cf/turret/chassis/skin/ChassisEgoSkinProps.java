package io.tbbc.cf.turret.chassis.skin;

public enum ChassisEgoSkinProps {
    // Pulse - M
    ARG_M_PULSE("turret_arg_m_laser_01_mk1", "turret_arg_m_laser_02_mk1"),
    TEL_M_PULSE("turret_tel_m_laser_01_mk1", "turret_tel_m_laser_02_mk1"),
    PAR_M_PULSE("turret_par_m_laser_01_mk1", "turret_par_m_laser_02_mk1"),
    XEN_M_PULSE("turret_xen_m_laser_01_mk1", "turret_xen_m_laser_02_mk1"),
    SPL_M_PULSE("turret_spl_m_laser_01_mk1", "turret_spl_m_laser_02_mk1"),
    TER_M_PULSE("turret_ter_m_laser_01_mk1", "turret_ter_m_laser_02_mk1"),
    // Pulse - L
    ARG_L_PULSE("turret_arg_l_laser_01_mk1", null),
    TEL_L_PULSE("turret_tel_l_laser_01_mk1", null),
    PAR_L_PULSE("turret_par_l_laser_01_mk1", null),
    XEN_L_PULSE("turret_xen_l_laser_01_mk1", null),
    KHA_L_PULSE("turret_cf_kha_l_laser_01_mk1", null),
    SPL_L_PULSE("turret_spl_l_laser_01_mk1", null),
    TER_L_PULSE("turret_ter_l_laser_01_mk1", null),
    // Gatling - M
    ARG_M_GATLING("turret_arg_m_gatling_01_mk1", "turret_arg_m_gatling_02_mk1"),
    TEL_M_GATLING("turret_tel_m_gatling_01_mk1", "turret_tel_m_gatling_02_mk1"),
    PAR_M_GATLING("turret_par_m_gatling_01_mk1", "turret_par_m_gatling_02_mk1"),
    SPL_M_GATLING("turret_spl_m_gatling_01_mk1", "turret_spl_m_gatling_02_mk1"),
    TER_M_GATLING("turret_ter_m_gatling_01_mk1", "turret_ter_m_gatling_02_mk1"),
    // Gatling - L
    TER_L_GATLING("turret_ter_l_gatling_01_mk1", null),
    // Plasma - M
    ARG_M_PLASMA("turret_arg_m_plasma_01_mk1", "turret_arg_m_plasma_02_mk1"),
    TEL_M_PLASMA("turret_tel_m_plasma_01_mk1", "turret_tel_m_plasma_02_mk1"),
    PAR_M_PLASMA("turret_par_m_plasma_01_mk1", "turret_par_m_plasma_02_mk1"),
    SPL_M_PLASMA("turret_spl_m_plasma_01_mk1", "turret_spl_m_plasma_02_mk1"),
    // Plasma - L
    ARG_L_PLASMA("turret_arg_l_plasma_01_mk1", null),
    TEL_L_PLASMA("turret_tel_l_plasma_01_mk1", null),
    PAR_L_PLASMA("turret_par_l_plasma_01_mk1", null),
    SPL_L_PLASMA("turret_spl_l_plasma_01_mk1", null),
    // Shotgun - M
    ARG_M_SHOTGUN("turret_arg_m_shotgun_01_mk1", "turret_arg_m_shotgun_02_mk1"),
    TEL_M_SHOTGUN("turret_tel_m_shotgun_01_mk1", "turret_tel_m_shotgun_02_mk1"),
    PAR_M_SHOTGUN("turret_par_m_shotgun_01_mk1", "turret_par_m_shotgun_02_mk1"),
    SPL_M_SHOTGUN("turret_spl_m_shotgun_01_mk1", "turret_spl_m_shotgun_02_mk1"),
    // Beam - M
    ARG_M_BEAM("turret_arg_m_beam_01_mk1", "turret_arg_m_beam_02_mk1"),
    TEL_M_BEAM("turret_tel_m_beam_01_mk1", "turret_tel_m_beam_02_mk1"),
    PAR_M_BEAM("turret_par_m_beam_01_mk1", "turret_par_m_beam_02_mk1"),
    XEN_M_BEAM("turret_xen_m_laser_01_mk1", "turret_xen_m_laser_02_mk1"),
    SPL_M_BEAM("turret_spl_m_beam_01_mk1", "turret_spl_m_beam_02_mk1"),
    TER_M_BEAM("turret_ter_m_beam_01_mk1", "turret_ter_m_beam_02_mk1"),
    // Flak - M
    ARG_M_FLAK("turret_arg_m_flak_01_mk1", "turret_arg_m_flak_02_mk1"),
    SPL_M_FLAK("turret_spl_m_flak_01_mk1", "turret_spl_m_flak_02_mk1"),
    // Needler - M
    XEN_M_NEEDLER("turret_cf_xen_m_gatling_01_mk1", "turret_cf_xen_m_gatling_02_mk1"),
    // Beam - L
    ARG_L_BEAM("turret_arg_l_beam_01_mk1", null),
    TEL_L_BEAM("turret_tel_l_beam_01_mk1", null),
    PAR_L_BEAM("turret_par_l_beam_01_mk1", null),
    KHA_L_BEAM("turret_cf_kha_l_beam_01_mk1", null),
    SPL_L_BEAM("turret_spl_l_beam_01_mk1", null),
    TER_L_BEAM("turret_ter_l_beam_01_mk1", null),
    // Electromagnetic - M
    TER_M_ELECTROMAGNETIC("turret_cf_ter_m_laser_01_mk1", "turret_cf_ter_m_laser_02_mk1");

    private final String egoComponentName;
    private final String egoComponentNameAlias;

    ChassisEgoSkinProps(String egoComponentName, String egoComponentNameAlias) {
        this.egoComponentName = egoComponentName;
        this.egoComponentNameAlias = egoComponentNameAlias;
    }

    public String egoComponentName() {
        return egoComponentName;
    }

    public String egoComponentNameAlias() {
        return egoComponentNameAlias;
    }
}
