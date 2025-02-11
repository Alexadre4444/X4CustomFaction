import Research from '@/model/common/Research';
import axios from 'axios';
import { CacheService } from './CacheService';

const RESEARCH = 'research';

function dataToObject(data : any) : Research {
    return new Research(data.name, data.label, data.description);
}

function _getResearch() : Promise<Research[]> {  
    return CacheService.getOrCache(RESEARCH, 
        () => axios.get('/api/v1/research')
            .then((response) => {return response.data;}))
        .then((response) => {
            return response.map((data: any) => dataToObject(data));
        });
}

export const ResearchService = {
    getAll() : Promise<Research[]> {
        return _getResearch();
    }
}