import ModifiedValue from "./ModifiedValue";
import Research from "./Research";

export default class ComputationResult {
    finalProperties: ModifiedValue[];
    requiredResearch: Research[];
    computationCost: number;
    computationMaxCost: number;
    constructor(finalProperties: ModifiedValue[], requiredResearch: Research[], computationCost: number, computationMaxCost: number) {
        this.finalProperties = finalProperties;
        this.requiredResearch = requiredResearch;
        this.computationCost = computationCost;
        this.computationMaxCost = computationMaxCost;
    }
}
