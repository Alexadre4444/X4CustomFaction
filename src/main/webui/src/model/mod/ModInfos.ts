export default class ModInfos {
    version:number;
    factionTrigram:string;
    researchMode:string;
    constructor(version:number, factionTrigram:string, researchMode:string) {
        this.version = version;
        this.factionTrigram = factionTrigram;
        this.researchMode = researchMode;
    }
}