import ProductionMethod from '@/model/common/ProductionMethod';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import axios from 'axios';
import { CacheService } from './CacheService';

const METHODS = 'production_methods';

function dataToObject(data : any) : ProductionMethod {
    return new ProductionMethod(new ProductionMethodName(data.name), data.label);
}

function _getMethods() : Promise<ProductionMethod[]> {  
    return CacheService.getOrCache(METHODS, 
        () => axios.get('/api/v1/production_methods')
            .then((response) => {return response.data;}))
        .then((response) => {
            return response.map((data: any) => dataToObject(data));
        });
}

export const ProductionMethodService = {
    getAll() : Promise<ProductionMethod[]> {
        return _getMethods();
    }
}