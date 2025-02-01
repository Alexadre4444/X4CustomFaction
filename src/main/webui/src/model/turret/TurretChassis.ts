import ChassisSkin from "./ChassisSkin";
import Properties from "./common/Properties";
import Size from "./common/Size";

export default class ChassisTurret {
    name: string;
    label: string;
    type: string;
    props: Properties;
    skin: string;
    availableSkins: ChassisSkin[];
    size: Size;
    constructor(name: string, label: string, type: string, props: Properties, skin: string, availableSkins: ChassisSkin[],
        size: Size) {
        this.name = name;
        this.label = label;
        this.type = type;
        this.props = props;
        this.skin = skin;
        this.availableSkins = availableSkins;
        this.size = size;
    }
}