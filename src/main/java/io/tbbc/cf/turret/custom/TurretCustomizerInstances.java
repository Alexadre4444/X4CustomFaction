package io.tbbc.cf.turret.custom;

import io.tbbc.cf.common.customizer.Customizer;
import io.tbbc.cf.common.customizer.CustomizerCategoryInstances;
import io.tbbc.cf.common.customizer.CustomizerComponent;
import io.tbbc.cf.common.modifier.Modifier;
import io.tbbc.cf.common.modifier.Modifiers;

import java.util.List;

import static io.tbbc.cf.common.property.CategoryInstances.COST;
import static io.tbbc.cf.turret.chassis.TurretChassisInstances.PropertyNames.*;

public class TurretCustomizerInstances {
    public static final CustomizerComponent CANNON = new CustomizerComponent("cannon", "Cannon",
            List.of(
                    // Basic
                    new Customizer("bDamage", "Damage", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 10),
                                    new Modifier(DAMAGE_SHIELD, 10),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bReloadTime", "Reload time", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -15),
                                    new Modifier(FIRE_RATE, 15),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bLifeTime", "Life time", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(LIFE_TIME, 10),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bSpeed", "Speed/Range", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(SPEED, 10),
                                    new Modifier(RANGE, 10),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bAccuracy", "Accuracy", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, 10),
                                    new Modifier(COST.name(), 10)))
                    ),
                    // Advanced
                    new Customizer("aDamage", "Damage", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 25),
                                    new Modifier(DAMAGE_SHIELD, 25),
                                    new Modifier(RELOAD_TIME, 10),
                                    new Modifier(FIRE_RATE, -10),
                                    new Modifier(COST.name(), 20)))
                    ),
                    new Customizer("aReloadTime", "Reload time", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -25),
                                    new Modifier(FIRE_RATE, 25),
                                    new Modifier(DAMAGE_SHIELD, -10),
                                    new Modifier(DAMAGE_HULL, -10),
                                    new Modifier(COST.name(), 20)))
                    ),
                    new Customizer("aSniper", "Sniper", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -20),
                                    new Modifier(SPEED, 15),
                                    new Modifier(LIFE_TIME, 15),
                                    new Modifier(RANGE, 25),
                                    new Modifier(COST.name(), 20)))
                    ),
                    // Expert
                    new Customizer("eDamage", "Damage", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 50),
                                    new Modifier(DAMAGE_SHIELD, 50),
                                    new Modifier(RELOAD_TIME, 20),
                                    new Modifier(FIRE_RATE, -20),
                                    new Modifier(COST.name(), 30)))
                    ),
                    new Customizer("eReload", "Reload time", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -40),
                                    new Modifier(FIRE_RATE, 40),
                                    new Modifier(DAMAGE_SHIELD, -20),
                                    new Modifier(DAMAGE_HULL, -20),
                                    new Modifier(COST.name(), 30)))
                    ),
                    new Customizer("eSniper", "Sniper", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -30),
                                    new Modifier(SPEED, 20),
                                    new Modifier(LIFE_TIME, 20),
                                    new Modifier(RANGE, 40),
                                    new Modifier(COST.name(), 30)))
                    )
            )
    );
    public static final CustomizerComponent TURRET_ENGINE = new CustomizerComponent("turretEngine", "Turret engine",
            List.of(
                    // Basic
                    new Customizer("bMobility", "Mobility", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(ROTATION_SPEED, 20),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bArmored", "Armored", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(HULL, 20),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bAccuracy", "Accuracy/Beam range", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -10),
                                    new Modifier(RANGE, 10),
                                    new Modifier(COST.name(), 10)))
                    ),
                    // Advanced
                    new Customizer("aMobility", "Mobility", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(ROTATION_SPEED, 40),
                                    new Modifier(COST.name(), 20)))
                    ),
                    new Customizer("aArmored", "Armored", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(HULL, 40),
                                    new Modifier(COST.name(), 20)))
                    ),
                    new Customizer("aAccuracy", "Accuracy/Beam range", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -20),
                                    new Modifier(RANGE, 20),
                                    new Modifier(COST.name(), 20)))
                    ),
                    // Expert
                    new Customizer("eMobility", "Mobility", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(ROTATION_SPEED, 60),
                                    new Modifier(COST.name(), 30)))
                    ),
                    new Customizer("eArmored", "Armored", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(HULL, 60),
                                    new Modifier(COST.name(), 30)))
                    ),
                    new Customizer("eAccuracy", "Accuracy/Beam range", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(ACCURACY, -30),
                                    new Modifier(RANGE, 30),
                                    new Modifier(COST.name(), 30)))
                    )
            )
    );
    public static final CustomizerComponent ENERGY_SOURCE = new CustomizerComponent("energySource", "Energy source",
            List.of(
                    // Basic
                    new Customizer("bDamage", "Damage", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 10),
                                    new Modifier(DAMAGE_SHIELD, 10),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bDamageIon", "Damage ion", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_SHIELD, 20),
                                    new Modifier(COST.name(), 10)))
                    ),
                    new Customizer("bReloadTime", "Reload time", CustomizerCategoryInstances.BASIC.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -15),
                                    new Modifier(FIRE_RATE, 15),
                                    new Modifier(COST.name(), 10)))
                    ),
                    // Advanced
                    new Customizer("aDamage", "Damage", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 25),
                                    new Modifier(DAMAGE_SHIELD, 25),
                                    new Modifier(RELOAD_TIME, 10),
                                    new Modifier(FIRE_RATE, -10),
                                    new Modifier(COST.name(), 20)))
                    ),
                    new Customizer("aDamageIon", "Damage ion", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, -20),
                                    new Modifier(DAMAGE_SHIELD, 60),
                                    new Modifier(COST.name(), 20)))
                    ),
                    new Customizer("aReloadTime", "Reload time", CustomizerCategoryInstances.ADVANCED.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -25),
                                    new Modifier(FIRE_RATE, 25),
                                    new Modifier(DAMAGE_SHIELD, -10),
                                    new Modifier(DAMAGE_HULL, -10),
                                    new Modifier(COST.name(), 20)))
                    ),
                    // Expert
                    new Customizer("eDamage", "Damage", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, 50),
                                    new Modifier(DAMAGE_SHIELD, 50),
                                    new Modifier(RELOAD_TIME, 20),
                                    new Modifier(FIRE_RATE, -20),
                                    new Modifier(COST.name(), 30)))
                    ),
                    new Customizer("eDamageIon", "Damage ion", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(DAMAGE_HULL, -40),
                                    new Modifier(DAMAGE_SHIELD, 100),
                                    new Modifier(COST.name(), 30)))
                    ),
                    new Customizer("eReload", "Reload time", CustomizerCategoryInstances.EXPERT.name(),
                            new Modifiers(List.of(
                                    new Modifier(RELOAD_TIME, -40),
                                    new Modifier(FIRE_RATE, 40),
                                    new Modifier(DAMAGE_SHIELD, -20),
                                    new Modifier(DAMAGE_HULL, -20),
                                    new Modifier(COST.name(), 30)))
                    )
            )
    );

    private TurretCustomizerInstances() {
    }
}
