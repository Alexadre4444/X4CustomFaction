import ModInfos from "@/model/mod/ModInfos";
import axios from "axios";

function dataToObject(data: any) {
    return new ModInfos(data.version, data.factionTrigram, data.researchMode);
}

export const ModService = {
    getActual() : Promise<ModInfos> {
        return axios.get('/api/v1/mod')
        .then((response) => {
            return dataToObject(response.data);
        });
    },
    updateInfos(trigram: string, research: boolean) : Promise<void> {
        return axios.post('/api/v1/mod', 
            {
                factionTrigram: trigram, 
                researchMode: research ? 'RESEARCH' : 'NO_RESEARCH'
            });
    },
    generateNewVersion() : Promise<void> {
        return axios.put('/api/v1/mod/generate');
    }
}

