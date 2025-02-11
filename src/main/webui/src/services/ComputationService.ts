import Category from '@/model/common/Category';
import ModifiedValue from '@/model/common/ModifiedValue';
import Modifier from '@/model/common/Modifier';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import axios from 'axios';
import { CategoryService } from './CategoryService';
import { TurretChassisService } from './TurretChassisService';

function dataToObject(data : any): Promise<ModifiedValue> {
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

function _computeTurretProperties(chassisName: String, bulletName: String, customizers: Record<string, string>,
    productionMethodNames: ProductionMethodName[]
) : Promise<ModifiedValue[]> {
    let body = {
        chassisName: chassisName,
        bulletName: bulletName,
        customizers: customizers,
        productionMethodNames: productionMethodNames
    };
    return axios.post('/api/v1/computation/turret', body)
    .then((response) => {
        return Promise.all(response.data.properties.map((data: any) => dataToObject(data)));
    });
}

export const ComputationService = {
    computeTurretProperties(chassisName: String, bulletName: String, customizers: Record<string, string>,
        productionMethodNames: ProductionMethodName[]) : Promise<ModifiedValue[]> {
        return _computeTurretProperties(chassisName, bulletName, customizers, productionMethodNames);
    }
}