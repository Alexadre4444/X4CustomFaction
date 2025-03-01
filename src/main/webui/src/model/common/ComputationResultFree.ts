import ModifiedValue from "./ModifiedValue";
import Research from "./Research";

export default class ComputationResult {
    finalProperties: ModifiedValue[];
    requiredResearch: Research[];
    customizationPoint: number;
    customisationBasePoint: number;
    constructor(finalProperties: ModifiedValue[], requiredResearch: Research[], customizationPoint: number, customisationBasePoint: number) {
        this.finalProperties = finalProperties;
        this.requiredResearch = requiredResearch;
        this.customizationPoint = customizationPoint;
        this.customisationBasePoint = customisationBasePoint;
    }
}
