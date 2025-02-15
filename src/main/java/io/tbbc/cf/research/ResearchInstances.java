package io.tbbc.cf.research;

import io.tbbc.cf.cost.CostEntry;

import java.util.List;

import static io.tbbc.cf.cost.CostWareInstances.ENERGY_CELLS;

public class ResearchInstances {
    public static final Research RESEARCH_CLOSED_LOOP_PRODUCTION = new Research("closedLoop", "Closed loop Production",
            "Unlock closed loop production method.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_TERRAN_PRODUCTION = new Research("terran", "Terran production",
            "Unlock terran production method.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_TERRAN_TECH = new Research("terran", "Terran technology",
            "Unlock terran technology.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of(RESEARCH_TERRAN_PRODUCTION));
    public static final Research RESEARCH_FRONTIER = new Research("frontier", "Frontier technology",
            "Unlock Frontier technology (Old terran tech).",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_ARGON = new Research("argon", "Argon technology",
            "Unlock Argon technology.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_PARANID = new Research("paranid", "Paranid technology",
            "Unlock Paranid technology.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_TELADI = new Research("teladi", "Teladi technology",
            "Unlock Teladi technology.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_SPLIT = new Research("split", "Split technology",
            "Unlock Split technology.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_XENON = new Research("xenon", "Xenon technology",
            "Unlock Xenon technology.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_KHAAK = new Research("khaak", "Kha'ak technology",
            "Unlock Kha'ak technology.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_MOD_BASIC = new Research("mod_basic", "Basic modifications",
            "Unlock basic modifications.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of());
    public static final Research RESEARCH_MOD_ADVANCED = new Research("mod_advanced", "Advanced modifications",
            "Unlock advanced modifications.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of(RESEARCH_MOD_BASIC));
    public static final Research RESEARCH_MOD_EXPERT = new Research("mod_expert", "Expert modifications",
            "Unlock expert modifications.",
            5, List.of(new CostEntry(ENERGY_CELLS, 1)),
            List.of(RESEARCH_MOD_ADVANCED));

    private ResearchInstances() {
    }


}
