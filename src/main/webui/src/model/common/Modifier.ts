import Category from "./Category";
import PropertyDefinition from "./PropertyDefinition";

export default class Modifier {
    name:string;
    value:number;
    propertyDefinition?: PropertyDefinition;
    category?: Category;
    constructor(name: string, value: number, propertyDefinition?: PropertyDefinition, category?: Category) {
        this.name = name;
        this.value = value;
        this.propertyDefinition = propertyDefinition;
        this.category = category;
    }
}