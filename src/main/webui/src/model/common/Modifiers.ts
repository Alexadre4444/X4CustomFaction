import Modifier from '../common/Modifier';

export default class Modifiers {
    modifiers: Array<Modifier>;
    constructor(modifiers: Modifier[]) {
        this.modifiers = modifiers;
    }
    
    modifier(name: string): Modifier {
        const foundModifier = this.modifiers.find((modifier) => {
            return modifier.name === name;
        });
        if (!foundModifier) {
            throw new Error(`Modifier with name ${name} not found`);
        }
        return foundModifier;
    }
}