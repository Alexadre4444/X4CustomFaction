import Property from './Property';
export default class Properties {
    properties: Array<Property>;
    constructor(properties: Property[]) {
        this.properties = properties;
    }

    property(name: string): Property {
        return this.properties.find((property) => property.name === name);
    }
}