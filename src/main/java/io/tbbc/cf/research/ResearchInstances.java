package io.tbbc.cf.research;

import io.tbbc.cf.cost.CostEntry;

import java.util.List;

import static io.tbbc.cf.cost.CostWareInstances.*;

public class ResearchInstances {
    public static final Research RESEARCH_CLOSED_LOOP_PRODUCTION = new Research("closedLoop", "Closed loop Production",
            "Unlock closed loop production method.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 200000),
                    new CostEntry(CLAYTRONICS, 1000),
                    new CostEntry(HULL_PARTS, 4000)),
            List.of());
    public static final Research RESEARCH_TERRAN_PRODUCTION = new Research("terran_production", "Terran production",
            "Unlock terran production method.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 100000),
                    new CostEntry(COMPUTRONIC_SUBSTRATE, 2000),
                    new CostEntry(METALLIC_MICRO_LATTICE, 10000),
                    new CostEntry(SILICON_CARBIDE, 2000)),
            List.of());
    public static final Research RESEARCH_TERRAN_TECH = new Research("terran_tech", "Terran technology",
            "Unlock terran technology.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 100000),
                    new CostEntry(COMPUTRONIC_SUBSTRATE, 2000),
                    new CostEntry(METALLIC_MICRO_LATTICE, 10000),
                    new CostEntry(SILICON_CARBIDE, 2000)),
            List.of());
    public static final Research RESEARCH_FRONTIER = new Research("frontier", "Frontier technology",
            "Unlock Frontier technology (Old terran tech).",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 100000),
                    new CostEntry(COMPUTRONIC_SUBSTRATE, 2000),
                    new CostEntry(METALLIC_MICRO_LATTICE, 10000),
                    new CostEntry(SILICON_CARBIDE, 2000)),
            List.of());
    public static final Research RESEARCH_ARGON = new Research("argon", "Argon technology",
            "Unlock Argon technology.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 10000),
                    new CostEntry(ADVANCED_ELECTRONICS, 200),
                    new CostEntry(ANTIMATTER_CONVERTERS, 400),
                    new CostEntry(NIVIDIUM, 40)),
            List.of());
    public static final Research RESEARCH_PARANID = new Research("paranid", "Paranid technology",
            "Unlock Paranid technology.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 10000),
                    new CostEntry(ADVANCED_ELECTRONICS, 200),
                    new CostEntry(ANTIMATTER_CONVERTERS, 400),
                    new CostEntry(NIVIDIUM, 40)),
            List.of());
    public static final Research RESEARCH_TELADI = new Research("teladi", "Teladi technology",
            "Unlock Teladi technology.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 10000),
                    new CostEntry(ADVANCED_ELECTRONICS, 200),
                    new CostEntry(ANTIMATTER_CONVERTERS, 400),
                    new CostEntry(NIVIDIUM, 40)),
            List.of());
    public static final Research RESEARCH_BORON = new Research("boron", "Boron technology",
            "Unlock Boron technology.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 10000),
                    new CostEntry(ADVANCED_ELECTRONICS, 200),
                    new CostEntry(ANTIMATTER_CONVERTERS, 400),
                    new CostEntry(NIVIDIUM, 40)),
            List.of());
    public static final Research RESEARCH_SPLIT = new Research("split", "Split technology",
            "Unlock Split technology.",
            300,
            List.of(
                    new CostEntry(ENERGY_CELLS, 10000),
                    new CostEntry(ADVANCED_ELECTRONICS, 200),
                    new CostEntry(ANTIMATTER_CONVERTERS, 400),
                    new CostEntry(NIVIDIUM, 40)),
            List.of());
    public static final Research RESEARCH_XENON = new Research("xenon", "Xenon technology",
            "Unlock Xenon technology.",
            600,
            List.of(
                    new CostEntry(ENERGY_CELLS, 50000),
                    new CostEntry(ADVANCED_ELECTRONICS, 1000),
                    new CostEntry(ANTIMATTER_CONVERTERS, 2000),
                    new CostEntry(NIVIDIUM, 200)),
            List.of());
    public static final Research RESEARCH_KHAAK = new Research("khaak", "Kha'ak technology",
            "Unlock Kha'ak technology.",
            600,
            List.of(
                    new CostEntry(ENERGY_CELLS, 50000),
                    new CostEntry(ADVANCED_ELECTRONICS, 1000),
                    new CostEntry(ANTIMATTER_CONVERTERS, 2000),
                    new CostEntry(NIVIDIUM, 200)),
            List.of());
    public static final Research RESEARCH_MOD_BASIC = new Research("mod_basic", "Basic modifications",
            "Unlock basic modifications.",
            300,
            List.of(
                    new CostEntry(ADVANCED_COMPOSITES, 500),
                    new CostEntry(FIELD_COILS, 2500),
                    new CostEntry(PLASMA_CONDUCTORS, 3000),
                    new CostEntry(WEAPON_COMPONENTS, 1000),
                    new CostEntry(TURRET_COMPONENTS, 1000)),
            List.of());
    public static final Research RESEARCH_MOD_ADVANCED = new Research("mod_advanced", "Advanced modifications",
            "Unlock advanced modifications.",
            600,
            List.of(
                    new CostEntry(ADVANCED_COMPOSITES, 2000),
                    new CostEntry(FIELD_COILS, 10000),
                    new CostEntry(PLASMA_CONDUCTORS, 12000),
                    new CostEntry(WEAPON_COMPONENTS, 4000),
                    new CostEntry(TURRET_COMPONENTS, 4000)),
            List.of(RESEARCH_MOD_BASIC));
    public static final Research RESEARCH_MOD_EXPERT = new Research("mod_expert", "Expert modifications",
            "Unlock expert modifications.",
            600,
            List.of(
                    new CostEntry(ADVANCED_COMPOSITES, 4000),
                    new CostEntry(FIELD_COILS, 20000),
                    new CostEntry(PLASMA_CONDUCTORS, 24000),
                    new CostEntry(WEAPON_COMPONENTS, 8000),
                    new CostEntry(TURRET_COMPONENTS, 8000)),
            List.of(RESEARCH_MOD_ADVANCED));

    private ResearchInstances() {
    }


}
