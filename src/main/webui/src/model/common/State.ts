export default class State {
    static UP = new State("UP");
    static WARNING = new State("WARNING");
    static DOWN = new State("DOWN");
    static NONE = new State("NONE");

    label: string;

    constructor(label) {
        this.label = label;
    }

    static from(label: string) : State {
        return Object.keys(State)[label];
    }
}