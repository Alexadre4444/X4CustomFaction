import Research from "../common/Research";

export default class BulletSkin {
    name: string;
    label: string;
    requiredResearch: Research[];
    constructor(name: string, label: string, requiredResearch: Research[]) {
        this.name = name;
        this.label = label;
        this.requiredResearch = requiredResearch;
    }
}