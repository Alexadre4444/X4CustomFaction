export default class ModInfos {
    version:number;
    factionTrigram:string;
    researchMode:string;
    customizePoints:number;
    constructor(version:number, factionTrigram:string, researchMode:string, customizePoints:number) {
        this.version = version;
        this.factionTrigram = factionTrigram;
        this.researchMode = researchMode;
        this.customizePoints = customizePoints;
    }
}