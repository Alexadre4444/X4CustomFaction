import ModifiedValue from "./ModifiedValue";
import Research from "./Research";

export default class ComputationResult {
    finalProperties: ModifiedValue[];
    requiredResearch: Research[];
    constructor(finalProperties: ModifiedValue[], requiredResearch: Research[]) {
        this.finalProperties = finalProperties;
        this.requiredResearch = requiredResearch;
    }
}
