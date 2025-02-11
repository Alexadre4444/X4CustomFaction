import Properties from "../common/Properties";
import Research from "../common/Research";
import Size from "../common/Size";
import ChassisSkin from "./ChassisSkin";

export default class ChassisTurret {
    name: string;
    label: string;
    type: string;
    props: Properties;
    skin: string;
    availableSkins: ChassisSkin[];
    size: Size;
    requiredResearch: Research[];
    constructor(name: string, label: string, type: string, props: Properties, skin: string, availableSkins: ChassisSkin[],
        size: Size, requiredResearch: Research[]) {
        this.name = name;
        this.label = label;
        this.type = type;
        this.props = props;
        this.skin = skin;
        this.availableSkins = availableSkins;
        this.size = size;
        this.requiredResearch = requiredResearch;
    }
}