import CostWare from '@/model/common/CostWare';
import axios from 'axios';
import { CacheService } from './CacheService';

const COST_WARES = 'costWares';

function dataToObject(data : any) : CostWare {
    return new CostWare(data.name, data.label);
}

function _getCostWares() : Promise<CostWare[]> {  
    return CacheService.getOrCache(COST_WARES, 
        () => axios.get('/api/v1/cost_wares')
            .then((response) => {return response.data;}))
        .then((response) => {
            return response.map((data: any) => dataToObject(data));
        });
}

export const CostWareService = {
    getAll() : Promise<CostWare[]> {
        return _getCostWares();
    }
}