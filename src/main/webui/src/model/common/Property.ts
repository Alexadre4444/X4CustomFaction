import PropertyDefinition from './PropertyDefinition';

export default class Property {
    name:string;
    value:number;
    definition: PropertyDefinition;
    constructor(name: string, value: number, definition: PropertyDefinition) {
        this.name = name;
        this.value = value;
        this.definition = definition;
    }
}