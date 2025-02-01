import Category from "./Category";

export default class PropertyDefinition {
    name: string;
    label: string;
    description: string;
    reverse: boolean;
    category: Category;
    unit: string;
    constructor(name: string, label: string, description: string, reverse: boolean, category: Category, unit: string) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.reverse = reverse;
        this.category = category;
        this.unit = unit;
    }
}