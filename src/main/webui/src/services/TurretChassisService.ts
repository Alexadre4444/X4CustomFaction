import Category from '@/model/common/Category';
import Properties from '@/model/common/Properties';
import Property from '@/model/common/Property';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Size from '@/model/common/Size';
import ChassisSkin from '@/model/turret/ChassisSkin';
import ChassisTurret from '@/model/turret/TurretChassis';
import axios from 'axios';
import { CacheService } from './CacheService';
import { CategoryService } from './CategoryService';

const DEFINITIONS = 'turrets_chassis_properties_definitions';
const CHASSIS = 'turret_chassis';

function dataToObject(data : any) : Promise<ChassisTurret> {
    return dataToProperties(data.props).then((properties) => {
        return new ChassisTurret(data.name, data.label, data.type, properties, data.skin,
            data.availableSkins.map((data: any) => dataToChassisSkin(data)), Size.fromKey(data.size));
    });
}

function dataToProperties(properties: any): Promise<Properties> {
    return Promise.all(properties.properties.map((data: any) => dataToProperty(data)))
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
        return properties.find((property) => property.name === name);
    });
}

function dataToPropertyDefinition(data: any): Promise<PropertyDefinition> {
    return getCategory(data.categoryName.name).then((category) => {
        return new PropertyDefinition(data.name.name, data.label, data.description, data.reverse, category, data.unit, data.accessibility);
    });
}

function dataToChassisSkin(data : any) : ChassisSkin {
    return new ChassisSkin(data.name, data.label, data.egoSkinProps);
}

function getCategory(name: string): Promise<Category> {
    return CategoryService.getAll().then((categories) => {
        return categories.find((category) => category.name === name);
    });
}

function _getAll() : Promise<ChassisTurret[]> {
    return CacheService.getOrCache(CHASSIS,
        () => axios.get('/api/v1/turrets_chassis')
            .then((response) => {
                return Promise.all(response.data.map((data) => dataToObject(data)));
            }));
}

function _getProperties() : Promise<PropertyDefinition[]> { 
    return CacheService.getOrCache(DEFINITIONS, 
        () => axios.get('/api/v1/turrets_properties')
        .then((response) => {
            return response.data;
        }))
        .then((data) => {
            return Promise.all(data.map(data => dataToPropertyDefinition(data)));
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

