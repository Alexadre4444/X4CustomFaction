import CostEntry from './CostEntry';

export default class Research {
    name: string;
    label: string;
    description: string;
    costs: CostEntry[];

    constructor(name: string, label: string, description: string, costs: CostEntry[]) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.costs = costs;
    }
}