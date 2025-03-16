import ModInfos from "@/model/mod/ModInfos";
import axios from "axios";

function dataToObject(data: any) {
    return new ModInfos(data.version, data.factionTrigram, data.researchMode, data.customizePoints);
}

export const ModService = {
    getActual() : Promise<ModInfos> {
        return axios.get('/api/v1/mod')
        .then((response) => {
            return dataToObject(response.data);
        });
    },
    updateInfos(trigram: string, research: string, customizePoints: number) : Promise<void> {
        return axios.post('/api/v1/mod', 
            {
                factionTrigram: trigram, 
                researchMode: research,
                customizePoints: customizePoints
            });
    },
    generateNewVersion() : Promise<void> {
        return axios.put('/api/v1/mod/generate');
    }
}

