import Modifier from '../common/Modifier';

export default class Modifiers {
    modifiers: Array<Modifier>;
    constructor(modifiers: Modifier[]) {
        this.modifiers = modifiers;
    }
    
    modifier(name: string): Modifier {
        return this.modifiers.find((modifier) =>{
            return modifier.name === name;
        } );
    }
}