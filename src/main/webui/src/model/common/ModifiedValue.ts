import PropertyDefinition from "./PropertyDefinition";

export default class ModifiedValue {
    definition: PropertyDefinition;
    baseValueString: string;
    finalValueString: string;
    modfier?: number;
    constructor(definition: PropertyDefinition, baseValueString: string, finalValueString: string, modifier?: number) {
        this.definition = definition;
        this.modfier = modifier;
        this.baseValueString = baseValueString;
        this.finalValueString = finalValueString;
    }
}