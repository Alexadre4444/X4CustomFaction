import PropertyDefinition from "./PropertyDefinition";

export default class FreeCustomizerValue {
    propertyBaseValue: string;
    propertyFinalValue: string;
    propertyDefinition: PropertyDefinition;
    realModifierValue?: number;
    desiredModifierValue?: number;
    constructor(propertyBaseValue: string, propertyFinalValue: string, 
        propertyDefinition: PropertyDefinition, modifierValue: number = 0,
        desiredModifierValue: number = 0) {
        this.desiredModifierValue = desiredModifierValue;
        this.realModifierValue = modifierValue;
        this.propertyBaseValue = propertyBaseValue;
        this.propertyFinalValue = propertyFinalValue;
        this.propertyDefinition = propertyDefinition;
    }
}