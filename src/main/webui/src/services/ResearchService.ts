import CostEntry from '@/model/common/CostEntry';
import CostWare from '@/model/common/CostWare';
import Research from '@/model/common/Research';
import axios from 'axios';
import { CacheService } from './CacheService';
import { CostWareService } from './CostWareService';

const RESEARCH = 'research';

function dataToObject(data : any) : Promise<Research> {
    return Promise.all<CostWare>(data.costs.map((data: any) => {
        return dataToCostWare(data.costWare);
    }))
    .then((costs) => {
    return new Research(data.name, data.label, data.description, 
        costs.map((cost) => new CostEntry(cost, data.costs
            .find((c) => c.costWare.name === cost.name).amount)));
    });
}

function dataToCostWare(data : any) : Promise<CostWare> {
    return CostWareService.getAll().then((costWare) => {
        const foundCostWare = costWare.find((r) => r.name === data.name);
        if (!foundCostWare) {
            throw new Error(`Cost ware with name ${data.name} not found`);
        }
        return foundCostWare;
    });
}

function _getResearch() : Promise<Research[]> {  
    return CacheService.getOrCache(RESEARCH, 
        () => axios.get('/api/v1/research')
            .then((response) => {return response.data;}))
        .then((response) => {
            return Promise.all<Research>(response.map((data: any) => dataToObject(data)));
        });
}

export const ResearchService = {
    getAll() : Promise<Research[]> {
        return _getResearch();
    }
}