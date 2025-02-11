import Size from "../common/Size";

export default class Turret {
    label: string;
    private _size: Size;
    size: string;
    
    constructor(label: string, size: Size) {
        this.label = label;
        this._size = size;
        this.size = size.key;
    }
}