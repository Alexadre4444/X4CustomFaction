import PropertyDefinition from "./PropertyDefinition";

export default class FreeCustomizerValue {
    propertyBaseValue: string;
    propertyFinalValue: string;
    propertyDefinition: PropertyDefinition;
    modifierValue?: number;
    constructor(propertyBaseValue: string, propertyFinalValue: string, 
        propertyDefinition: PropertyDefinition, modifierValue: number = 0) {
        this.modifierValue = modifierValue;
        this.propertyBaseValue = propertyBaseValue;
        this.propertyFinalValue = propertyFinalValue;
        this.propertyDefinition = propertyDefinition;
    }
}