import Category from '@/model/common/Category';
import CustomizerCategory from '@/model/common/CustomizerCategory';
import axios from 'axios';
import { CacheService } from './CacheService';

const CATEGORIES = 'customizers_categories';

function dataToObject(data : any) : CustomizerCategory {
    return new CustomizerCategory(data.name.name, data.label);
}

function _getCategories() : Promise<Category[]> {  
    return CacheService.getOrCache(CATEGORIES, 
        () => axios.get('/api/v1/customizers_categories')
            .then((response) => {return response.data;}))
        .then((response) => {
            return response.map((data: any) => dataToObject(data));
        });
}

export const CustomizerCategoryService = {
    getAll() : Promise<CustomizerCategory[]> {
        return _getCategories();
    }
}