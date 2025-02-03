import Category from '@/model/common/Category';
import Customizer from '@/model/common/Customizer';
import CustomizerCategory from '@/model/common/CustomizerCategory';
import CustomizerComponent from '@/model/common/CustomizerComponent';
import CustomizerValue from '@/model/common/CustomizerValue';
import Modifier from '@/model/common/Modifier';
import Modifiers from '@/model/common/Modifiers';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Size from '@/model/common/Size';
import Turret from '@/model/turret/Turret.ts';
import TurretCreate from '@/model/turret/TurretCreate';
import axios from 'axios';
import { CacheService } from './CacheService';
import { CategoryService } from './CategoryService';
import { CustomizerCategoryService } from './CustomizerCategoryService';
import { TurretChassisService } from './TurretChassisService';

const CUSTOMIZER = 'turrets_categories';

function dataToTurret(data : any) : Turret {
    return new Turret(data.id, data.label, Size.fromKey(data.size), data.description, data.chassisName,
        data.chassisSkinName, data.bulletName, data.bulletSkinName, data.customizers.map((data: any) => dataToCustomzerValue(data)), data.state,
    data.methods?.map((data: any) => new ProductionMethodName(data.name)));
}

function dataToCustomzerValue(data: any) : CustomizerValue {
    return new CustomizerValue(data.categoryName, data.customizerName);
}

function getPropertyDefinition(name: string): Promise<PropertyDefinition> {
    return TurretChassisService.getProperties().then((properties) => {
        return properties.find((property) => property.name === name);
    });
}

function getCategory(name: string): Promise<Category> {
    return CategoryService.getAll().then((categories) => {
        return categories.find((category) => category.name === name);
    });
}

function dataToModifier(data: any) : Promise<Modifier> {
    return getPropertyDefinition(data.name.name).then((definition) => {
            return new Modifier(data.name.name, data.value, definition, null);
    })
    .then((modifier) => {
        return getCategory(data.name.name).then((category) => {
            return new Modifier(modifier.name, modifier.value, modifier.propertyDefinition, category);
        });
    });
}

function dataToCustomizer(data: any) : Promise<Customizer> {
    return Promise.all(data.modifiers.modifiers.map((data: any) => dataToModifier(data)))
    .then((modifiers) => {
        return dataToCustomizerCategory(data.categoryName).then((category) => {
            return new Customizer(data.name, data.label, new Modifiers(modifiers), category);
        });
    });
}

function dataToCustomizerCategory(data: any) : Promise<CustomizerCategory> {
    return CustomizerCategoryService.getAll().then((categories) => {
        return categories.find((category) => category.name === data.name);
    });
}

function dataToCustomizerComponents(data: any) : Promise<CustomizerComponent> {
    return Promise.all(data.customizers.map((data: any) => dataToCustomizer(data)))
    .then((customizers) => {
        return new CustomizerComponent(data.name, data.label, customizers);
    });
}

function _getCustomizerCategories() : Promise<CustomizerComponent[]> {
    return CacheService.getOrCache(CUSTOMIZER, 
        () =>  axios.get('/api/v1/turrets_customizer'))
        .then((response) => {
            return Promise.all(response.data.map((data: any) => {
                return dataToCustomizerComponents(data);
            }));
    });
}

export const TurretService = {
    async get(id: number): Promise<Turret> {
        const response = await axios.get('/api/v1/turrets/' + id);
        return dataToTurret(response.data);
    },
    async getAll(): Promise<Turret[]> {
        const response = await axios.get('/api/v1/turrets');
        return response.data.map((data : any) => dataToTurret(data));
    },
    async create(turret : TurretCreate) {
        return axios.put('/api/v1/turrets', turret);
    },
    async update(turret : Turret) {
        return axios.post('/api/v1/turrets/'+ turret.id, turret);
    },
    async delete(id: number): Promise<Turret> {
        return axios.delete('/api/v1/turrets/' + id);
    },
    getCustomizers() : Promise<CustomizerComponent[]> {
        return _getCustomizerCategories();
    }
}