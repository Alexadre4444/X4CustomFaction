import CostWare from "./CostWare";

export default class CostEntry {
    costWare: CostWare;
    value: number;
    constructor(costWare: CostWare, value: number) {
        this.costWare = costWare;
        this.value = value;
    }
}