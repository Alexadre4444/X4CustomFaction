import Category from '@/model/common/Category';
import Influence from '@/model/common/Influence';
import Modifier from '@/model/common/Modifier';
import Modifiers from '@/model/common/Modifiers';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Research from '@/model/common/Research';
import Bullet from '@/model/turret/Bullet';
import BulletSkin from '@/model/turret/BulletSkin';
import axios from 'axios';
import { CategoryService } from './CategoryService';
import { ResearchService } from './ResearchService';
import { TurretChassisService } from './TurretChassisService';

function dataToObject(data : any): Promise<Bullet> {
    return dataToModifiers(data.modifiers.modifiers).then((modifiers) => {
        return Promise.all<BulletSkin>(data.availableSkins.map((data: any) => dataToBulletSkin(data)))
        .then((skins) => {
            return new Bullet(data.name, data.label, data.description, data.size, modifiers, skins, 
                data.compatibleChassis, data.influence ? dataToInfluence(data.influence) : undefined);
        });
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

function dataToResearch(data : any) : Promise<Research> {
    return ResearchService.getAll().then((research) => {
        const foundResearch = research.find((r) => r.name === data.name);
        if (!foundResearch) {
            throw new Error(`Research with name ${data.name} not found`);
        }
        return foundResearch;
    });
}

function dataToInfluence(data: any) : Influence {
    return new Influence(data.name, data.label, data.description);
}

function dataToBulletSkin(data: any) : Promise<BulletSkin> {
    return Promise.all<Research>(data.requiredResearch.map((data: any) => dataToResearch(data)))
    .then((requiredResearch) => {
        return new BulletSkin(data.name, data.label, requiredResearch);
    });
}
export const BulletService = {
    getAll(): Promise<Bullet[]> {
        return axios.get('/api/v1/bullets')
        .then((response) => {
            return Promise.all(response.data.map((data: any) => dataToObject(data)));
        });
    }
}