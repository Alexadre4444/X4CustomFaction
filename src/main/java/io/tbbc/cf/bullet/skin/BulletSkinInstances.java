package io.tbbc.cf.bullet.skin;

import java.util.List;

import static io.tbbc.cf.bullet.skin.BulletEgoSkinProps.*;
import static io.tbbc.cf.research.ResearchInstances.*;

public class BulletSkinInstances {
    // Pulse - M
    public static final BulletSkin M_PULSE_BLUE = new BulletSkin("pulse_m_blue", "Common wealth (blue)",
            BLUE_M_PULSE, List.of());
    public static final BulletSkin M_PULSE_ION_BLUE = new BulletSkin("pulse_ion_m_blue", "Argon ion (blue)",
            BLUE_M_PULSE_ION, List.of(RESEARCH_ARGON));
    public static final BulletSkin M_PULSE_RED = new BulletSkin("pulse_m_red", "Xenon (red)",
            RED_M_PULSE, List.of(RESEARCH_XENON));

    // Pulse - L
    public static final BulletSkin L_PULSE_BLUE = new BulletSkin("pulse_l_blue", "Argon (blue)",
            BLUE_L_PULSE, List.of(RESEARCH_ARGON));
    public static final BulletSkin L_PULSE_YELLOW = new BulletSkin("pulse_l_yellow", "Teladi (yellow)",
            YELLOW_L_PULSE, List.of(RESEARCH_TELADI));
    public static final BulletSkin L_PULSE_VIOLET = new BulletSkin("pulse_l_violet", "Paranid (violet)",
            VIOLET_L_PULSE, List.of(RESEARCH_PARANID));
    public static final BulletSkin L_PULSE_RED = new BulletSkin("pulse_l_red", "Xenon (red)",
            RED_L_PULSE, List.of(RESEARCH_XENON));
    public static final BulletSkin L_PULSE_ORANGE = new BulletSkin("pulse_l_orange", "Split (orange)",
            ORANGE_L_PULSE, List.of(RESEARCH_SPLIT));
    public static final BulletSkin L_PULSE_WHITE = new BulletSkin("pulse_l_white", "Terran (white)",
            WHITE_L_PULSE, List.of(RESEARCH_TERRAN));

    // Gatling - M
    public static final BulletSkin M_GATLING_CW = new BulletSkin("gatling_m_cw", "Common wealth (orange)",
            CW_M_GATLING, List.of());
    public static final BulletSkin M_GATLING_XEN = new BulletSkin("gatling_m_xen", "Xenon (orange)",
            XEN_M_GATLING, List.of(RESEARCH_XENON));
    public static final BulletSkin M_GATLING_TER = new BulletSkin("gatling_m_ter", "Terran (blue)",
            TER_M_GATLING, List.of(RESEARCH_TERRAN));

    // Gatling - L
    public static final BulletSkin L_GATLING_TER = new BulletSkin("gatling_l_ter", "Terran (blue)",
            TER_L_GATLING, List.of(RESEARCH_TERRAN));

    // Plasma - M
    public static final BulletSkin M_PLASMA_CW = new BulletSkin("plasma_m_cw", "Common wealth (yellow)",
            PLASMA_M_CW, List.of());

    // Plasma - L
    public static final BulletSkin L_PLASMA_BLUE = new BulletSkin("plasma_l_blue", "Argon (blue)",
            BLUE_L_PLASMA, List.of(RESEARCH_ARGON));
    public static final BulletSkin L_PLASMA_YELLOW = new BulletSkin("plasma_l_yellow", "Teladi (yellow)",
            YELLOW_L_PLASMA, List.of(RESEARCH_TELADI));
    public static final BulletSkin L_PLASMA_VIOLET = new BulletSkin("plasma_l_violet", "Paranid (violet)",
            VIOLET_L_PLASMA, List.of(RESEARCH_PARANID));
    public static final BulletSkin L_PLASMA_ORANGE = new BulletSkin("plasma_l_orange", "Split (orange)",
            ORANGE_L_PLASMA, List.of(RESEARCH_SPLIT));

    // Shotgun - M
    public static final BulletSkin M_SHOTGUN_CW = new BulletSkin("shotgun_m_cw", "Common wealth (orange)",
            CW_M_SHOTGUN, List.of());

    // Beam - M
    public static final BulletSkin M_BEAM_CW = new BulletSkin("beam_m_cw", "Common wealth (red)",
            CW_M_BEAM, List.of());
    public static final BulletSkin M_BEAM_CW_MINING = new BulletSkin("beam_m_cw_mining", "Common wealth (blue)",
            CW_M_BEAM_MINING, List.of());
    public static final BulletSkin M_BEAM_KHA = new BulletSkin("beam_m_kha", "Kha'ak (violet)",
            KHA_M_BEAM, List.of(RESEARCH_KHAAK));
    public static final BulletSkin M_BEAM_TER = new BulletSkin("beam_m_ter", "Terran (white)",
            TER_M_BEAM, List.of(RESEARCH_TERRAN));

    // Beam - L
    public static final BulletSkin L_BEAM_BLUE = new BulletSkin("beam_l_blue", "Argon (blue)",
            ARG_L_BEAM, List.of(RESEARCH_ARGON));
    public static final BulletSkin L_BEAM_YELLOW = new BulletSkin("beam_l_yellow", "Teladi (yellow)",
            TEL_L_BEAM, List.of(RESEARCH_TELADI));
    public static final BulletSkin L_BEAM_VIOLET = new BulletSkin("beam_l_violet", "Paranid (violet)",
            PAR_L_BEAM, List.of(RESEARCH_PARANID));
    public static final BulletSkin L_BEAM_VIOLET_KHA = new BulletSkin("beam_l_violet_kha", "Kha'ak (violet)",
            KHA_L_BEAM, List.of(RESEARCH_KHAAK));
    public static final BulletSkin L_BEAM_ORANGE = new BulletSkin("beam_l_orange", "Split (orange)",
            SPL_L_BEAM, List.of(RESEARCH_SPLIT));
    public static final BulletSkin L_BEAM_WHITE = new BulletSkin("beam_l_white", "Terran (white)",
            TER_L_BEAM, List.of(RESEARCH_TERRAN));

    // Electromagnetic - M
    public static final BulletSkin M_ELECTROMAGNETIC_TER = new BulletSkin("electromagnetic_m_ter", "Terran (blue)",
            TER_M_ELECTROMAGNETIC, List.of(RESEARCH_FRONTIER));


    private BulletSkinInstances() {
    }
}
