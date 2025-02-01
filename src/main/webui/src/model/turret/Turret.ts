import CustomizerValue from "./common/CustomizerValue";
import Size from "./common/Size";

export default class Turret {
    id: number;
    label: string;
    description: string;
    chassisName: string;
    chassisSkinName: string;
    bulletName: string;
    bulletSkinName: string;
    customizers: CustomizerValue[];
    state: string;
    _size: Size;
    size: string;

    constructor(id: number, label: string, size: Size, description: string, chassisName: string, 
        chassisSkinName:string, bulletName: string, bulletSkinName: string, customizers: CustomizerValue[],
        state: string) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.chassisName = chassisName;
        this.chassisSkinName = chassisSkinName;
        this.bulletName = bulletName;
        this.bulletSkinName = bulletSkinName;
        this.customizers = customizers;
        this.state = state;
        this.size = size.key;
        this._size = size;
    }
}