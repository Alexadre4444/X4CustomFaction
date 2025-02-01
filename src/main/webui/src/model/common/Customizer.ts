import CustomizerCategory from './CustomizerCategory';
import Modifiers from './Modifiers';

export default class Customizer {
    name:string;
    label:string;
    modifiers: Modifiers;
    category: CustomizerCategory;
    constructor(name:string, label:string, modifiers:Modifiers, category:CustomizerCategory) {
        this.name = name;
        this.label = label;
        this.modifiers = modifiers;
        this.category = category;
    }
}