import Customizer from './Customizer';

export default class CustomizerComponent {
    name:string;
    label:string;
    customizers: Customizer[];
    constructor(name:string, label:string, customizers:Customizer[]) {
        this.name = name;
        this.label = label;
        this.customizers = customizers;
    }
}