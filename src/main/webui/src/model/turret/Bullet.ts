import Influence from '../common/Influence';
import Modifiers from '../common/Modifiers';
import BulletSkin from './BulletSkin';

export default class Bullet {
    name: string;
    label: string;
    description: string;
    size: string;
    modifiers: Modifiers;
    availableSkins: BulletSkin[];
    compatibleChassis: string[];
    influence?: Influence;
    constructor(name: string, label: string, description: string, size: string, modifiers: Modifiers, 
        availableSkins: BulletSkin[], compatibleChassis: string[], influence?: Influence) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.size = size;
        this.modifiers = modifiers;
        this.availableSkins = availableSkins;
        this.compatibleChassis = compatibleChassis;
        this.influence = influence;
    }
}