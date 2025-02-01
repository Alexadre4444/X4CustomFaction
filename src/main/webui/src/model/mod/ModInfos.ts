export default class ModInfos {
    version:number;
    factionTrigram:string;
    constructor(version:number, factionTrigram:string) {
        this.version = version;
        this.factionTrigram = factionTrigram;
    }
}