import ModInfos from "@/model/mod/ModInfos";
import axios from "axios";

function dataToObject(data: any) {
    return new ModInfos(data.version, data.factionTrigram);
}

export const ModService = {
    getActual() : Promise<ModInfos> {
        return axios.get('/api/v1/mod')
        .then((response) => {
            return dataToObject(response.data);
        });
    },
    updateTrigram(trigram: string) : Promise<void> {
        return axios.post('/api/v1/mod', {factionTrigram: trigram});
    },
    generateNewVersion() : Promise<void> {
        return axios.put('/api/v1/mod/generate');
    }
}

