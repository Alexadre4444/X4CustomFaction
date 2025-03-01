import Category from "./Category";

export default class PropertyDefinition {
    name: string;
    label: string;
    description: string;
    reverse: boolean;
    category: Category;
    unit: string;
    accessibility: string;
    isFree: boolean;
    decimal: number;
    constructor(name: string, label: string, description: string, reverse: boolean, category: Category, 
        unit: string, accessibility: string, isFree: boolean, decimal: number) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.reverse = reverse;
        this.category = category;
        this.unit = unit;
        this.accessibility = accessibility;
        this.isFree = isFree;
        this.decimal = decimal;
    }
}