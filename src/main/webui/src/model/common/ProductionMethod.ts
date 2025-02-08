import ProductionMethodName from "./ProductionMethodName";

export default class ProductionMethod {
    name:ProductionMethodName;
    label:string;
    constructor(name: ProductionMethodName, label: string) {
        this.name = name;
        this.label = label;
    }
}