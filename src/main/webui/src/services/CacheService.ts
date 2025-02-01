import Lock from "@/model/common/Lock";

const locks = {};

function _get(key: string): any {
    let data = localStorage.getItem(key);
    if(data) {
        return JSON.parse(data);
    }
    return null;
}

export const CacheService = {
    getOrCache(key: string, fetch: Function) : Promise<any> {
        if(!locks[key]) {
            locks[key] = new Lock();
        }
        return locks[key].acquire()
            .then(() => {
                let data = _get(key);
                if(data) {
                    return Promise.resolve(data);
                }
                console.log('Fetching', key);
                return fetch().then((data) => {
                    console.log('Caching', key, data);
                    localStorage.setItem(key, JSON.stringify(data));
                    return data;
                })
            })
            .finally(() => { locks[key].release(); });
    },
    clear() {
        console.log('Clearing cache');
        localStorage.clear();
    }
};