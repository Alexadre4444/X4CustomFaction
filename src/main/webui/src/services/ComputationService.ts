import Category from '@/model/common/Category';
import ComputationResult from '@/model/common/ComputationResult';
import ComputationResultFree from '@/model/common/ComputationResultFree';
import ModifiedValue from '@/model/common/ModifiedValue';
import Modifier from '@/model/common/Modifier';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Research from '@/model/common/Research';
import axios from 'axios';
import { CategoryService } from './CategoryService';
import { ResearchService } from './ResearchService';
import { TurretChassisService } from './TurretChassisService';

function dataToComputationResult(data: any): Promise<ComputationResult> {
    return Promise.all<ModifiedValue>(data.finalProperties.properties.map((data: any) => dataToModifiedValue(data)))
    .then((properties) => {
        return Promise.all<Research>(data.requiredResearch.map((data: any) => dataToResearch(data)))
        .then((requiredResearch) => {
            return new ComputationResult(properties, requiredResearch);
        });
    });
}

function dataToComputationResultFree(data: any): Promise<ComputationResultFree> {
    return Promise.all<ModifiedValue>(data.finalProperties.properties.map((data: any) => dataToModifiedValue(data)))
    .then((properties) => {
        return Promise.all<Research>(data.requiredResearch.map((data: any) => dataToResearch(data)))
        .then((requiredResearch) => {
            return new ComputationResultFree(properties, requiredResearch, data.computationCost, data.computationMaxCost);
        });
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


function dataToModifiedValue(data : any): Promise<ModifiedValue> {
    return getPropertyDefinition(data.name.name).then((definition) => {
        return Promise.all<Modifier>(data.modifiers.map((data: any) => dataToModifier(data)))
        .then((modifiers) => {
            return new ModifiedValue(definition, modifiers, data.baseValueString, data.finalValueString, data.baseDoubleValue, data.finalDoubleValue);
        });
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

function getCategory(name: string): Promise<Category | undefined> {
    return CategoryService.getAll().then((categories) => {
        return categories.find((category) => category.name === name);;
    });
}
function getPropertyDefinition(name: string): Promise<PropertyDefinition> {
    return TurretChassisService.getProperties().then((properties) => {
        return properties.find((property) => property.name === name);;
    });
}

function _computeTurretProperties(chassisName: String, chassisSkinName: String, bulletName: String, bulletSkinName: String,
    customizers: Record<string, string>, productionMethodNames: ProductionMethodName[]): Promise<ComputationResult> {
    let body = {
        chassisName: chassisName,
        bulletName: bulletName,
        customizers: customizers,
        productionMethodNames: productionMethodNames,
        chassisSkinName: chassisSkinName,
        bulletSkinName: bulletSkinName
    };
    return axios.post('/api/v1/computation/turret', body)
    .then((response) => {
        return dataToComputationResult(response.data);
    });
}

function _computeTurretPropertiesFree(chassisName: String, chassisSkinName: String, bulletName: String, bulletSkinName: String,
    customizers: Record<string, number>, productionMethodNames: ProductionMethodName[]): Promise<ComputationResultFree> {
    let body = {
        chassisName: chassisName,
        bulletName: bulletName,
        customizers: customizers,
        productionMethodNames: productionMethodNames,
        chassisSkinName: chassisSkinName,
        bulletSkinName: bulletSkinName
    };
    return axios.post('/api/v1/computation/turret_free', body)
    .then((response) => {
        return dataToComputationResultFree(response.data);
    });
}

export const ComputationService = {
    computeTurretProperties(chassisName: String, chassisSkinName: String, bulletName: String, bulletSkinName: String,
        customizers: Record<string, string>, productionMethodNames: ProductionMethodName[]) : Promise<ComputationResult> {
        return _computeTurretProperties(chassisName, chassisSkinName, bulletName, bulletSkinName, customizers, productionMethodNames);
    },
    computeTurretPropertiesFree(chassisName: String, chassisSkinName: String, bulletName: String, bulletSkinName: String,
        customizers: Record<string, number>, productionMethodNames: ProductionMethodName[]) : Promise<ComputationResultFree> {
        return _computeTurretPropertiesFree(chassisName, chassisSkinName, bulletName, bulletSkinName, customizers, productionMethodNames);
    }
}