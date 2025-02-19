import ProductionMethod from '@/model/common/ProductionMethod';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import Research from '@/model/common/Research';
import axios from 'axios';
import { CacheService } from './CacheService';
import { ResearchService } from './ResearchService';

const METHODS = 'production_methods';

function dataToObject(data : any) : Promise<ProductionMethod> {
    return Promise.all<Research>(data.requiredResearch.map((data: any) => dataToResearch(data)))
        .then((requiredResearch) => {
            return new ProductionMethod(new ProductionMethodName(data.name), data.label, requiredResearch);
        });
}

function _getMethods() : Promise<ProductionMethod[]> {  
    return CacheService.getOrCache(METHODS, 
        () => axios.get('/api/v1/production_methods')
            .then((response) => {return response.data;}))
        .then((response) => {
            return Promise.all<ProductionMethod>(response.map((data: any) => dataToObject(data)));
        });
}

function dataToResearch(data : any) : Promise<Research> {
    return ResearchService.getAll().then((research) => {
        const foundResearch = research.find((r) => r.name === data.name);
        if (!foundResearch) {
            throw new Error(`Research with name ${data.name} not found`);
        }
        return foundResearch;
    });
}

export const ProductionMethodService = {
    getAll() : Promise<ProductionMethod[]> {
        return _getMethods();
    }
}