import ProductionMethodName from "../common/ProductionMethodName";
import PropertyCustomizerValue from "../common/PropertyCustomizerValue";
import Size from "../common/Size";

export default class Turret {
    id: number;
    label: string;
    description: string;
    chassisName: string;
    chassisSkinName: string;
    bulletName: string;
    bulletSkinName: string;
    state: string;
    _size: Size;
    size: string;
    methods: ProductionMethodName[];
    propertyCustomizers: PropertyCustomizerValue[];

    constructor(id: number, label: string, size: Size, description: string, chassisName: string, 
        chassisSkinName:string, bulletName: string, bulletSkinName: string, 
        state: string, methods: ProductionMethodName[], propertyCustomizers: PropertyCustomizerValue[]) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.chassisName = chassisName;
        this.chassisSkinName = chassisSkinName;
        this.bulletName = bulletName;
        this.bulletSkinName = bulletSkinName;
        this.state = state;
        this.size = size.key;
        this._size = size;
        this.methods = methods;
        this.propertyCustomizers = propertyCustomizers;
    }
}