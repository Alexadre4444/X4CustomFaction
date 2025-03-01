<script lang="ts" setup>
import Customizer from '@/model/common/Customizer';
import CustomizerComponent from '@/model/common/CustomizerComponent';
import FreeCustomizerValue from '@/model/common/FreeCustomizerValue';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import PropertyCustomizerValue from '@/model/common/PropertyCustomizerValue';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import Research from '@/model/common/Research';
import Bullet from '@/model/turret/Bullet';
import BulletSkin from '@/model/turret/BulletSkin';
import ChassisSkin from '@/model/turret/ChassisSkin';
import Turret from '@/model/turret/Turret.ts';
import ChassisTurret from '@/model/turret/TurretChassis';
import { BulletService } from '@/services/BulletService.ts';
import { CacheService } from '@/services/CacheService';
import { ComputationService } from '@/services/ComputationService';
import { NotificationService } from '@/services/NotificationService.ts';
import { TurretChassisService } from '@/services/TurretChassisService.ts';
import { TurretService } from '@/services/TurretServiceMain';
import { computed, Ref, ref } from 'vue';
import CostDisplay from '../common/CostDisplay.vue';

CacheService.clear();

const props = defineProps({
    id: Number
});

const loadedTurret = ref<Turret>();

const formLabel = ref<string>();
const formDescription = ref();
const formChassis = ref<ChassisTurret>();
const formChassisSkin = ref<ChassisSkin>();
const formBullet = ref<Bullet>();
const formBulletSkin = ref<BulletSkin>();
const formCustomizers = ref<Map<CustomizerComponent, Ref<Customizer>>>();
const formProductionMethodsName = ref<ProductionMethodName[]>([]);

const formAccessibility = ref<string>('BASIC');

const customizerComponents = ref<CustomizerComponent[]>();

const freeCustomizerValues =  ref<FreeCustomizerValue[]>([]);

const customisationBasePoint = ref<number>(0);
const customizationPoint = ref<number>(0);

const computeUuid = ref<number>(0);

const chassisOnChange = () => {
    fixChassisSkinOnChange();
    fixBulletOnChange();
    computeProperties();
}

const bulletOnChange = () => {
    fixBulletSkinOnChange();
    computeProperties();
}

const fixChassisSkinOnChange = () => {
    if(formChassisSkin.value) {
        formChassisSkin.value = formChassis.value?.availableSkins.find(skin => skin.name == formChassisSkin.value?.name);
    }
}

const fixBulletOnChange = () => {
    if(formBullet.value) {
        formBullet.value = availableBullets.value.find(bullet => bullet.name == formBullet.value?.name);
    }
}

const fixBulletSkinOnChange = () => {
    if(formBulletSkin.value) {
        formBulletSkin.value = formBullet.value?.availableSkins.find(skin => skin.name == formBulletSkin.value?.name);
    }
}

const requiredResearch = ref<Research[]>([]);

const applicableProperties = ref<PropertyDefinition[]>([]);

const availableBullets = computed<Bullet[]>(() => {
    if(formChassis.value == null) {
        return [];
    }
    return finalBulletList.value
        .filter(bullet => bullet.size == formChassis.value.size.key)
        .filter(bullet => bullet.compatibleChassis
            .filter(chassisType => chassisType == formChassis.value?.type).length > 0);
});

const iconUrl = computed(() => {
    if(formChassisSkin.value == null) {
        return `/assets/turret/notfound.png`;
    }
    return `/assets/turret/${formChassisSkin.value.icon}.png`;
});

const menu = ref([{
        label: 'Update',
        icon: 'pi pi-fw pi-save',
        command: () => {update();}
    }
])

const update = () => {
    TurretService.update(new Turret(loadedTurret.value.id, formLabel.value, loadedTurret.value._size, formDescription.value, 
        formChassis.value?.name, formChassisSkin.value?.name,  formBullet.value?.name, formBulletSkin.value?.name,
        loadedTurret.value.state, formProductionMethodsName.value, propertyCustomizerValues.value))
    .then(() => {
        NotificationService.success('Turret updated.');
    }).catch(error => {
        NotificationService.error(error);
    });
}

function computeProperties() {
    computePropertiesAsync().then(() => {});
}

function computePropertiesAsync(): Promise<void> {
    return new Promise((resolve, reject) => {
        const newUuid = computeUuid.value + 1;
        computeUuid.value = newUuid;
        if(formChassis.value != null && formBullet.value != null && formChassisSkin.value != null && formBulletSkin.value != null) {
            return ComputationService.computeTurretPropertiesFree(formChassis.value.name, formChassisSkin.value.name,
                formBullet.value.name, formBulletSkin.value.name, freeCustomizerValuesModifableRecord.value, formProductionMethodsName.value)
                .then(computationResult => {
                    if(computeUuid.value == newUuid) {
                        requiredResearch.value = computationResult.requiredResearch;
                        customisationBasePoint.value = computationResult.customisationBasePoint;
                        customizationPoint.value = computationResult.customizationPoint;
                        computationResult.finalProperties.forEach(property => {
                            let freeCustomizerValue = freeCustomizerValues.value.find(freeCustomizerValue => freeCustomizerValue.propertyDefinition.name == property.definition.name);
                            if(freeCustomizerValue == null) {
                                freeCustomizerValue = new FreeCustomizerValue(property.baseValueString, property.finalValueString, property.definition, property.modfier, property.modfier);
                                freeCustomizerValues.value.push(freeCustomizerValue);
                                applicableProperties.value.push(property.definition);
                            } else {
                                freeCustomizerValue.propertyBaseValue = property.baseValueString;
                                freeCustomizerValue.propertyFinalValue = property.finalValueString;
                                freeCustomizerValue.realModifierValue = property.modfier;
                            }
                        });
                    }
                    resolve();
                }).catch(error => {
                    NotificationService.error(error);
                    reject(error);
                });
        } else {
            if(computeUuid.value == newUuid) {
                freeCustomizerValues.value = [];
                applicableProperties.value = [];
                requiredResearch.value = [];
                customizationPoint.value = 0;
            }
            resolve();
        }
    });
}

const freeCustomizerValuesModifable = computed(() => {
    return freeCustomizerValues.value.filter(freeCustomizerValue => freeCustomizerValue.propertyDefinition.isFree);
});

const freeCustomizerValuesModifableRecord = computed<Record<string, number>>(() => {
    let record: Record<string,number> = {};
    freeCustomizerValuesModifable.value.forEach(freeCustomizerValue => {
        record[freeCustomizerValue.propertyDefinition.name] = freeCustomizerValue.desiredModifierValue;
    });
    return record;
});

const propertyCustomizerValues = computed<PropertyCustomizerValue[]>(() => {
    return freeCustomizerValuesModifable.value.map(freeCustomizerValue => {
        return new PropertyCustomizerValue(freeCustomizerValue.propertyDefinition.name, freeCustomizerValue.desiredModifierValue);
    });
});

const freeCustomizerValuesNotModifable = computed(() => {
    return freeCustomizerValues.value.filter(freeCustomizerValue => !freeCustomizerValue.propertyDefinition.isFree);
});

const resetForm = () => {
    formLabel.value = loadedTurret.value.label;
    formDescription.value = loadedTurret.value.description; 
    formChassis.value = allChassis.value.find(chassis => chassis.name == loadedTurret.value.chassisName);
    formChassisSkin.value = formChassis.value?.availableSkins.find(skin => skin.name == loadedTurret.value.chassisSkinName);
    formBullet.value = finalBulletList.value.find(bullet => bullet.name == loadedTurret.value.bulletName);
    formBulletSkin.value = formBullet.value?.availableSkins.find(skin => skin.name == loadedTurret.value.bulletSkinName);
    formProductionMethodsName.value = loadedTurret.value.methods;
    computePropertiesAsync().then(() => {
        loadedTurret.value.propertyCustomizers.forEach(propertyCustomizer => {
            let freeCustomizerValue = freeCustomizerValues.value.find(freeCustomizerValue => freeCustomizerValue.propertyDefinition.name == propertyCustomizer.propertyName);
                if(freeCustomizerValue) {
                    freeCustomizerValue.desiredModifierValue = propertyCustomizer.propertyModifier;
                    freeCustomizerValue.realModifierValue = propertyCustomizer.propertyModifier;
                } else {
                    console.log("Property not applicable: " + propertyCustomizer.propertyName);
                }
        });
        computeProperties();
    });
}

const allChassis = ref<ChassisTurret[]>([]);

const refreshChassis = () => {
    return TurretChassisService.getAll()
    .then(chassisList => {
        allChassis.value = chassisList;
    })
    .catch(error => 
        NotificationService.error(error)
    );
}   

const availableChassis = computed(() => {
    return allChassis.value.filter(chassis => chassis.size.key == loadedTurret.value.size);
});

const finalBulletList = ref<Bullet[]>([]);

const refreshBullets = () => {
    return BulletService.getAll()
    .then(bulletList => {
        finalBulletList.value = bulletList;
    })
    .catch(error => NotificationService.error(error));
}

const refreshTurret = () => {
    return TurretService.get(props.id)
    .then(turret => {
        loadedTurret.value = turret;
        resetForm();
    })
    .catch(error => NotificationService.error(error));
}

const refreshCustomizers = () => {
    return TurretService.getCustomizers()
    .then(customizers => {
        customizerComponents.value = customizers;
        formCustomizers.value = new Map();
        customizers.forEach(customizer => {
            formCustomizers.value.set(customizer, null);
        });
    })
}

await refreshChassis()
.then(() => refreshBullets())
.then(() => refreshCustomizers())
.then(() => refreshTurret()); // Last action

</script>
<template>
    <div class="mb-4">
        <Menubar :model="menu" breakpoint="500px">
            <template #end>
                <Accessibility v-model="formAccessibility"/>
            </template>
        </Menubar>
    </div>
    <div class="card grid grid-cols-12 gap-2">
        <div class="col-span-6 xl:col-span-3">
            <div class="card flex flex-col gap-2 w-full">
                <div v-if="loadedTurret" class="flex flex-col gap-2">
                    <div class="font-semibold text-xl">Turret</div>
                    <div class="flex flex-col gap-2">
                        <label for="name">Label</label>
                        <InputText id="label" type="text" v-model="formLabel" />
                    </div>
                    <div class=" flex justify-center">
                        <Image :src="iconUrl" alt="Image" width="200" />
                    </div>
                    <div class="flex flex-col gap-2">
                        <label for="description">Description</label>
                        <Textarea id="description" type="text" v-model="formDescription" />
                    </div>
                    <div class="flex flex-col gap-2">
                        <label>Production method</label>
                        <Suspense>
                            <template #default>
                                <ProductionMethodSelector v-model="formProductionMethodsName" @change="computeProperties"/>
                            </template>
                            <template #fallback>
                                <Loading />
                            </template>
                        </Suspense>
                    </div>
                    <div class="flex flex-col gap-2">
                        <label for="chassis">Chassis</label>
                        <Select  id="chassis" v-model="formChassis" 
                        :options="availableChassis" 
                        optionLabel="label" 
                        @change="chassisOnChange"
                        :filter="true" />
                    </div>
                    <div v-if="formChassis" class="flex flex-col gap-2">
                        <label for="chassisSkin">Chassis skin</label>
                        <Select id="chassisSkin" 
                        v-model="formChassisSkin" 
                        :options="formChassis.availableSkins" 
                        @change="computeProperties"
                        optionLabel="label" :filter="true" :showClear="true" />   
                    </div>
                </div>
                <div v-else class="flex flex-col gap-2">
                    <div class="font-semibold text-xl">Properties</div>
                    <div class="flex flex-col md:flex-row gap-4">
                        <Loading />
                    </div>
                </div>
            </div>
        </div>
        <div v-if="loadedTurret" class="col-span-6 xl:col-span-3">
            <div class="card flex flex-col gap-2 w-full">
                <div v-if="requiredResearch.length > 0" class="font-semibold text-xl">Required research</div>
                <div class="flex flex-col gap-2">
                    <ResearchTag v-for="research in requiredResearch" :key="research.name" :research="research"/>
                </div>

                <div class="font-semibold text-xl">Customization</div>
                <div class="flex flex-col gap-2">
                    <label for="bullet">Bullet</label>
                    <Select  id="bullet" v-model="formBullet" 
                    :options="availableBullets" 
                    optionLabel="label" 
                    @change="bulletOnChange"
                    :filter="true" :showClear="true" />
                </div>
                <ModifiersDisplay v-if="formBullet"
                :applicable-properties="applicableProperties" 
                :modifiers="formBullet.modifiers.modifiers"/>
                <div v-if="formBullet" class="flex flex-col gap-2">
                    <label for="bulletSkin">Bullet skin</label>
                    <Select id="bulletSkin" 
                    v-model="formBulletSkin" 
                    :options="formBullet.availableSkins" 
                    @change="computeProperties"
                    optionLabel="label" :filter="true" :showClear="true" />   
                </div>
                <CostDisplay v-if="customisationBasePoint != 0" :customization-point="customizationPoint" :customisation-base-point="customisationBasePoint"/>
            </div>
        </div>
            <div class="col-span-3 xl:col-span-3">
                <div class="font-semibold text-xl mb-6">Updatable properties</div>
                <FreeCustomizersComponents accessibility='ADVANCED' @change="computeProperties()" v-model="freeCustomizerValuesModifable"/>
            </div>
            <div class="col-span-3 xl:col-span-3 ml-4">
                <div class="font-semibold text-xl mb-6">Other properties</div>
                <div v-if="formBullet && formBullet.influence" class="flex flex-col gap-2 pb-2">
                    <label>Impact influence</label>
                    <InfluenceDisplay :influence="formBullet.influence"/>
                </div>
                <FreeCustomizersComponents :accessibility="formAccessibility" v-model="freeCustomizerValuesNotModifable"/>
            </div>
    </div>
</template>