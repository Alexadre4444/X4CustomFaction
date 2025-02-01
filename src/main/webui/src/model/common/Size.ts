export default class Size {
    key:string;
    label:string;
    private constructor(key:string, label:string) {
        this.key = key;
        this.label = label;
    }
    static SMALL: Size = new Size("SMALL", "Small");
    static MEDIUM: Size = new Size("MEDIUM", "Medium");
    static LARGE: Size = new Size("LARGE", "Large");
    static EXTRA_LARGE: Size = new Size("EXTRA_LARGE", "Extra Large");
    static values() : Size[] {
        return [Size.SMALL, Size.MEDIUM, Size.LARGE, Size.EXTRA_LARGE];
    }
    static fromKey(key: string) : Size {
        return Size.values().find((size) => size.key === key);
    }
}