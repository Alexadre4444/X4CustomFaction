package io.tbbc.cf.turret.custom;

import io.tbbc.cf.customizer.Customizer;
import io.tbbc.cf.customizer.CustomizerCategoryInstances;
import io.tbbc.cf.customizer.CustomizerComponent;
import io.tbbc.cf.modifier.Modifier;
import io.tbbc.cf.modifier.Modifiers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.tbbc.cf.property.CategoryInstances.COST;
import static io.tbbc.cf.research.ResearchInstances.*;
import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class TurretCustomizerInstances {
    public static final CustomizerComponent CANNON = new CustomizerComponent("cannon", "Cannon",
            Stream.of(
                    // Basic
                    new Customizer("bDamage", "Damage", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 10),
                                    new Modifier(DAMAGE_SHIELD, 10),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bReloadTime", "Reload time", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -15),
                                    new Modifier(FIRE_RATE, 15),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bLifeTime", "Life time", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(LIFE_TIME, 10),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bSpeed", "Speed/Range", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(SPEED, 10),
                                    new Modifier(BEAM_RANGE, 10),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bAccuracy", "Accuracy", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, 10),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    // Advanced
                    new Customizer("aDamage", "Damage", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 25),
                                    new Modifier(DAMAGE_SHIELD, 25),
                                    new Modifier(RELOAD_TIME, 10),
                                    new Modifier(FIRE_RATE, -10),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    new Customizer("aReloadTime", "Reload time", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -25),
                                    new Modifier(FIRE_RATE, 25),
                                    new Modifier(DAMAGE_SHIELD, -10),
                                    new Modifier(DAMAGE_HULL, -10),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    new Customizer("aSniper", "Sniper", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -20),
                                    new Modifier(SPEED, 15),
                                    new Modifier(LIFE_TIME, 15),
                                    new Modifier(BEAM_RANGE, 25),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    // Expert
                    new Customizer("eDamage", "Damage", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 50),
                                    new Modifier(DAMAGE_SHIELD, 50),
                                    new Modifier(RELOAD_TIME, 20),
                                    new Modifier(FIRE_RATE, -20),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    ),
                    new Customizer("eReload", "Reload time", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -40),
                                    new Modifier(FIRE_RATE, 40),
                                    new Modifier(DAMAGE_SHIELD, -20),
                                    new Modifier(DAMAGE_HULL, -20),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    ),
                    new Customizer("eSniper", "Sniper", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -30),
                                    new Modifier(SPEED, 20),
                                    new Modifier(LIFE_TIME, 20),
                                    new Modifier(BEAM_RANGE, 40),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    )
            ).sorted(Comparator.comparing(Customizer::label)).toList()
    );
    public static final CustomizerComponent TURRET_ENGINE = new CustomizerComponent("turretEngine", "Turret engine",
            Stream.of(
                    // Basic
                    new Customizer("bMobility", "Mobility", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(ROTATION_SPEED, 20),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bArmored", "Armored", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(HULL, 20),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bAccuracy", "Accuracy/Beam range", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -10),
                                    new Modifier(BEAM_RANGE, 10),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    // Advanced
                    new Customizer("aMobility", "Mobility", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(ROTATION_SPEED, 40),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    new Customizer("aArmored", "Armored", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(HULL, 40),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    new Customizer("aAccuracy", "Accuracy/Beam range", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -20),
                                    new Modifier(BEAM_RANGE, 20),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    // Expert
                    new Customizer("eMobility", "Mobility", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(ROTATION_SPEED, 60),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    ),
                    new Customizer("eArmored", "Armored", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(HULL, 60),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    ),
                    new Customizer("eAccuracy", "Accuracy/Beam range", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -30),
                                    new Modifier(BEAM_RANGE, 30),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    )
            ).sorted(Comparator.comparing(Customizer::label)).toList()
    );
    public static final CustomizerComponent ENERGY_SOURCE = new CustomizerComponent("energySource", "Energy source",
            Stream.of(
                    // Basic
                    new Customizer("bDamage", "Damage", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 10),
                                    new Modifier(DAMAGE_SHIELD, 10),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bDamageIon", "Damage ion", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_SHIELD, 20),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    new Customizer("bReloadTime", "Reload time", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -15),
                                    new Modifier(FIRE_RATE, 15),
                                    new Modifier(COST.name(), 10))),
                            RESEARCH_MOD_BASIC
                    ),
                    // Advanced
                    new Customizer("aDamage", "Damage", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 25),
                                    new Modifier(DAMAGE_SHIELD, 25),
                                    new Modifier(RELOAD_TIME, 10),
                                    new Modifier(FIRE_RATE, -10),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    new Customizer("aDamageIon", "Damage ion", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, -20),
                                    new Modifier(DAMAGE_SHIELD, 60),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    new Customizer("aReloadTime", "Reload time", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -25),
                                    new Modifier(FIRE_RATE, 25),
                                    new Modifier(DAMAGE_SHIELD, -10),
                                    new Modifier(DAMAGE_HULL, -10),
                                    new Modifier(COST.name(), 20))),
                            RESEARCH_MOD_ADVANCED
                    ),
                    // Expert
                    new Customizer("eDamage", "Damage", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 50),
                                    new Modifier(DAMAGE_SHIELD, 50),
                                    new Modifier(RELOAD_TIME, 20),
                                    new Modifier(FIRE_RATE, -20),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    ),
                    new Customizer("eDamageIon", "Damage ion", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, -40),
                                    new Modifier(DAMAGE_SHIELD, 100),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    ),
                    new Customizer("eReload", "Reload time", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -40),
                                    new Modifier(FIRE_RATE, 40),
                                    new Modifier(DAMAGE_SHIELD, -20),
                                    new Modifier(DAMAGE_HULL, -20),
                                    new Modifier(COST.name(), 30))),
                            RESEARCH_MOD_EXPERT
                    )
            ).sorted(Comparator.comparing(Customizer::label)).toList()
    );

    private TurretCustomizerInstances() {
    }
}
