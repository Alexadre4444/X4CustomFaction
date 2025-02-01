import Category from '@/model/common/Category';
import axios from 'axios';
import { CacheService } from './CacheService';

const CATEGORIES = 'categories';

function dataToObject(data : any) : Category {
    return new Category(data.name.name, data.label, data.reverse);
}

function _getCategories() : Promise<Category[]> {  
    return CacheService.getOrCache(CATEGORIES, 
        () => axios.get('/api/v1/categories')
            .then((response) => {return response.data;}))
        .then((response) => {
            return response.map((data) => dataToObject(data));
        });
}

export const CategoryService = {
    getAll() : Promise<Category[]> {
        return _getCategories();
    },
    default() {
        return new Category('unknown', 'Unknown', false);
    }
}