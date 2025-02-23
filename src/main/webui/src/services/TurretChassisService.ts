import Category from '@/model/common/Category';
import Properties from '@/model/common/Properties';
import Property from '@/model/common/Property';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Research from '@/model/common/Research';
import Size from '@/model/common/Size';
import ChassisSkin from '@/model/turret/ChassisSkin';
import ChassisTurret from '@/model/turret/TurretChassis';
import axios from 'axios';
import { CacheService } from './CacheService';
import { CategoryService } from './CategoryService';
import { ResearchService } from './ResearchService';

const DEFINITIONS = 'turrets_chassis_properties_definitions';
const CHASSIS = 'turret_chassis';

function dataToObject(data : any) : Promise<ChassisTurret> {
    return dataToProperties(data.props).then((properties) => {
        return Promise.all<ChassisSkin>(data.availableSkins.map((data: any) => dataToChassisSkin(data)))
        .then((availableSkins) => {
                return new ChassisTurret(data.name, data.label, data.type, properties, data.skin, availableSkins, 
                    Size.fromKey(data.size));
        });
    });
}

function dataToProperties(properties: any): Promise<Properties> {
    return Promise.all<Property>(properties.properties.map((data: any) => dataToProperty(data)))
    .then((properties) => {
        return new Properties(properties);
    });
}

function dataToProperty(data: any): Promise<Property> {
    return getPropertyDefinition(data.name.name).then((definition) => {
        return new Property(data.name.name, data.value, definition);
    });
}

function getPropertyDefinition(name: string): Promise<PropertyDefinition> {
    return TurretChassisService.getProperties().then((properties) => {
        const property = properties.find((property) => property.name === name);
        if (!property) {
            throw new Error(`Property with name ${name} not found`);
        }
        return property;
    });
}

function dataToPropertyDefinition(data: any): Promise<PropertyDefinition> {
    return getCategory(data.categoryName.name).then((category) => {
        return new PropertyDefinition(data.name.name, data.label, data.description, data.reverse, category, 
            data.unit, data.accessibility, data.isFree, data.decimal);
    });
}

function dataToChassisSkin(data : any) : Promise<ChassisSkin> {
    return Promise.all<Research>(data.requiredResearch.map((data: any) => dataToResearch(data)))
        .then((requiredResearch) => {  
            return new ChassisSkin(data.name, data.label, data.egoSkinProps, requiredResearch);
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

function getCategory(name: string): Promise<Category> {
    return CategoryService.getAll().then((categories) => {
        const category = categories.find((category) => category.name === name);
        if (!category) {
            throw new Error(`Category with name ${name} not found`);
        }
        return category;
    });
}

function _getAll() : Promise<ChassisTurret[]> {
    return CacheService.getOrCache(CHASSIS,
        () => axios.get('/api/v1/turrets_chassis')
            .then((response) => {
                return Promise.all<ChassisTurret>(response.data.map((data: any) => dataToObject(data)));
            }));
}

function _getProperties() : Promise<PropertyDefinition[]> { 
    return CacheService.getOrCache(DEFINITIONS, 
        () => axios.get('/api/v1/turrets_properties')
        .then((response) => {
            return response.data;
        }))
        .then((data) => {
            return Promise.all<PropertyDefinition>(data.map((data: any) => dataToPropertyDefinition(data)));
        });
}

export const TurretChassisService = {
    getAll() : Promise<ChassisTurret[]> {
        return _getAll();
    },
    getProperties() : Promise<PropertyDefinition[]> {
        return _getProperties();
    }
}

