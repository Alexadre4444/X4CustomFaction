import Research from "../common/Research";

export default class ChassisSkin {
    name: string;
    label: string;
    icon: string;
    requiredResearch: Research[];
    constructor(name: string, label: string, icon: string, requiredResearch: Research[]) {
        this.name = name;
        this.label = label;
        this.icon = icon;
        this.requiredResearch = requiredResearch;
    }
}