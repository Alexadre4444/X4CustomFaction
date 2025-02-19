import ProductionMethodName from "./ProductionMethodName";
import Research from "./Research";

export default class ProductionMethod {
    name:ProductionMethodName;
    label:string;
    requiredResearch: Research[];
    constructor(name: ProductionMethodName, label: string, requiredResearch: Research[]) {
        this.name = name;
        this.label = label;
        this.requiredResearch = requiredResearch;
    }
}