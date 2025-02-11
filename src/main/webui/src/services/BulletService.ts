import Category from '@/model/common/Category';
import Modifier from '@/model/common/Modifier';
import Modifiers from '@/model/common/Modifiers';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Bullet from '@/model/turret/Bullet';
import BulletSkin from '@/model/turret/BulletSkin';
import axios from 'axios';
import { CategoryService } from './CategoryService';
import { TurretChassisService } from './TurretChassisService';

function dataToObject(data : any): Promise<Bullet> {
    return dataToModifiers(data.modifiers.modifiers).then((modifiers) => {
        return new Bullet(data.name, data.label, data.description, data.size, modifiers, 
            data.availableSkins.map((data: any) => dataToBulletSkin(data)), data.compatibleChassis);
    });
}

function dataToModifiers(data: any) : Promise<Modifiers> {
    return Promise.all<Modifier>(data.map((data: any) => dataToModifier(data)))
    .then((modifiers) => {
        return new Modifiers(modifiers);
    });
}

function dataToModifier(data: any) : Promise<Modifier> {
    return getPropertyDefinition(data.name.name).then((definition) => {
            return new Modifier(data.name.name, data.value, definition, undefined);
    })
    .then((modifier) => {
        return getCategory(data.name.name).then((category) => {
            return new Modifier(modifier.name, modifier.value, modifier.propertyDefinition, category);
        });
    });
}

function getPropertyDefinition(name: string): Promise<PropertyDefinition | undefined> {
    return TurretChassisService.getProperties().then((properties) => {
        return properties.find((property) => property.name === name);;
    });
}

function getCategory(name: string): Promise<Category | undefined> {
    return CategoryService.getAll().then((categories) => {
        return categories.find((category) => category.name === name);
    });
}

function dataToBulletSkin(data: any) : BulletSkin {
    return new BulletSkin(data.name, data.label);
}
export const BulletService = {
    getAll(): Promise<Bullet[]> {
        return axios.get('/api/v1/bullets')
        .then((response) => {
            return Promise.all(response.data.map((data: any) => dataToObject(data)));
        });
    }
}