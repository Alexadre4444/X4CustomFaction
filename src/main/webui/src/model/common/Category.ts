export default class Category {
    name: string;
    label: string;
    reverse: boolean;
    constructor(name: string, label: string, reverse: boolean) {
        this.name = name;
        this.label = label;
        this.reverse = reverse;
    }

    equals(other: Category): boolean {
        return this.name === other.name && this.label === other.label && this.reverse === other.reverse;
    }
}