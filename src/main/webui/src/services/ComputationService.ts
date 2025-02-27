import ComputationResultFree from '@/model/common/ComputationResultFree';
import ModifiedValue from '@/model/common/ModifiedValue';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Research from '@/model/common/Research';
import axios from 'axios';
import { ResearchService } from './ResearchService';
import { TurretChassisService } from './TurretChassisService';

function dataToComputationResultFree(data: any): Promise<ComputationResultFree> {
    return Promise.all<ModifiedValue>(data.finalProperties.properties.map((data: any) => dataToModifiedValue(data)))
    .then((properties) => {
        return Promise.all<Research>(data.requiredResearch.map((data: any) => dataToResearch(data)))
        .then((requiredResearch) => {
            return new ComputationResultFree(properties, requiredResearch, data.customizationPoint, data.customisationBasePoint);
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
        return new ModifiedValue(definition, data.baseValueString, data.finalValueString, data.modifier);
    });
}

function getPropertyDefinition(name: string): Promise<PropertyDefinition> {
    return TurretChassisService.getProperties().then((properties) => {
        return properties.find((property) => property.name === name);;
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
    computeTurretPropertiesFree(chassisName: String, chassisSkinName: String, bulletName: String, bulletSkinName: String,
        customizers: Record<string, number>, productionMethodNames: ProductionMethodName[]) : Promise<ComputationResultFree> {
        return _computeTurretPropertiesFree(chassisName, chassisSkinName, bulletName, bulletSkinName, customizers, productionMethodNames);
    }
}