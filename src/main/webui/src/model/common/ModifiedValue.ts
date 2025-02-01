import Modifier from "./common/Modifier";
import PropertyDefinition from "./common/PropertyDefinition";

export default class ModifiedValue {
    definition: PropertyDefinition;
    modifierList: Modifier[];
    baseValueString: string;
    finalValueString: string;
    baseDoubleValue: number;
    finalDoubleValue: number;
    constructor(definition: PropertyDefinition, modifierList: Modifier[], baseValueString: string, finalValueString: string,
        baseDoubleValue: number, finalDoubleValue: number) {
        this.definition = definition;
        this.modifierList = modifierList;
        this.baseValueString = baseValueString;
        this.finalValueString = finalValueString;
        this.baseDoubleValue = baseDoubleValue;
        this.finalDoubleValue = finalDoubleValue;
    }

    modifierSum(): number {
        return this.modifierList.reduce((sum, modifier) => sum + modifier.value, 0);
    }
}