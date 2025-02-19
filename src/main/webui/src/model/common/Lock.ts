export default class Lock {
    private isLocked: boolean;
    private queue: Function[];
    
    constructor() {
        this.isLocked = false;
        this.queue = [];
    }

    acquire(): Promise<void> {
        return new Promise<void>((resolve) => {
            if (!this.isLocked) {
                this.isLocked = true;
                resolve();
            } else {
                this.queue.push(resolve);
            }
        });
    }
    release() {
        const resolve = this.queue.shift();
        if (resolve) {
            resolve();
        } else {
            this.isLocked = false;
        }
    }
}