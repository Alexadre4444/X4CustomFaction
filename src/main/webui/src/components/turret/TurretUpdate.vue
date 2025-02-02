<script lang="ts" setup>
import Category from '@/model/common/Category';
import Customizer from '@/model/common/Customizer';
import CustomizerComponent from '@/model/common/CustomizerComponent';
import CustomizerValue from '@/model/common/CustomizerValue';
import ModifiedValue from '@/model/common/ModifiedValue';
import Bullet from '@/model/turret/Bullet';
import BulletSkin from '@/model/turret/BulletSkin';
import ChassisSkin from '@/model/turret/ChassisSkin';
import Turret from '@/model/turret/Turret.ts';
import ChassisTurret from '@/model/turret/TurretChassis';
import { BulletService } from '@/services/BulletService.ts';
import { CacheService } from '@/services/CacheService';
import { CategoryService } from '@/services/CategoryService';
import { ComputationService } from '@/services/ComputationService';
import { NotificationService } from '@/services/NotificationService.ts';
import { TurretChassisService } from '@/services/TurretChassisService.ts';
import { TurretService } from '@/services/TurretServiceMain';
import { computed, Ref, ref } from 'vue';
import CustomizersComponents from '../common/CustomizersComponents.vue';

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

const formAccessibility = ref<string>('BASIC');

const customizerComponents = ref<CustomizerComponent[]>();

const computedProperties = ref<ModifiedValue[]>([]);

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

const computedPropertiesByCategoryMap = computed(() => {
    let map = new Map<Category, ModifiedValue[]>();
    computedProperties.value?.forEach(property => {
        let category = property.definition.category;
        if(category != null) {
            let list = map.entries().find(entry => entry[0].name == category.name)?.[1];
            if(list == null) {
                list = [];
                map.set(category, list);
            }
            list.push(property);
        } else {
            let list = map.get(CategoryService.default());
            if(list == null) {
                list = [];
                map.set(CategoryService.default(), list);
            }
            list.push(property);
        }
    });
    return map;
});

const chassisPropertiesDefinitions = computed(() => {
    if(formChassis.value == null) {
        return [];
    }
    return formChassis.value.props.properties.map(property => property.definition);
});

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

const computedCustomizers = computed(() => {
    return customizerComponents.value?.filter(category => {
        return formCustomizers.value.get(category).value != null;
    }).map(category => {
        return new CustomizerValue(category.name, formCustomizers.value.get(category).value?.name);
    });
});

const update = () => {
    TurretService.update(new Turret(loadedTurret.value.id, formLabel.value, loadedTurret.value._size, formDescription.value, 
        formChassis.value?.name, formChassisSkin.value?.name,  formBullet.value?.name, formBulletSkin.value?.name,
        computedCustomizers.value, loadedTurret.value.state))
    .then(() => {
        NotificationService.success('Turret updated.');
    }).catch(error => {
        NotificationService.error(error);
    });
}

function computeProperties() {
    if(formChassis.value != null && formBullet.value != null) {
        let customizers: Record<string, string> = {};
        formCustomizers.value.forEach((customizer, category) => {
            if(customizer != null) {
                customizers[category.name] = customizer.value?.name;
            }
        });
        ComputationService.computeTurretProperties(formChassis.value.name, formBullet.value.name, customizers)
        .then(properties => {
            computedProperties.value = properties;
        }).catch(error => {
            NotificationService.error(error);
        });
    } else {
        computedProperties.value = [];
    }
}

const resetForm = () => {
    formLabel.value = loadedTurret.value.label;
    formDescription.value = loadedTurret.value.description; 
    formChassis.value = allChassis.value.find(chassis => chassis.name == loadedTurret.value.chassisName);
    formChassisSkin.value = formChassis.value?.availableSkins.find(skin => skin.name == loadedTurret.value.chassisSkinName);
    formBullet.value = finalBulletList.value.find(bullet => bullet.name == loadedTurret.value.bulletName);
    formBulletSkin.value = formBullet.value?.availableSkins.find(skin => skin.name == loadedTurret.value.bulletSkinName);
    customizerComponents.value.forEach(category => {
        let loadedValue = loadedTurret.value.customizers.find(customizer => customizer.categoryName == category.name);
        if(loadedValue != null) {
            formCustomizers.value.set(category, ref(category.customizers.find(c => c.name == loadedValue.customizerName)));
        } else {
            formCustomizers.value.set(category, ref());
        }
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
                    :modifiers="formBullet.modifiers.modifiers"/>
                    <div v-if="formBullet" class="flex flex-col gap-2">
                        <label for="bulletSkin">Bullet skin</label>
                        <Select id="bulletSkin" 
                        v-model="formBulletSkin" 
                        :options="formBullet.availableSkins" 
                        optionLabel="label" :filter="true" :showClear="true" />   
                    </div>
                <CustomizersComponents v-model="formCustomizers" @change="computeProperties"/> 
            </div>
        </div>
        <CategoryProperties v-if="formAccessibility == 'ADVANCED'" v-for="categoryPropertiesEntry in computedPropertiesByCategoryMap" 
        :category="categoryPropertiesEntry[0]" :modifiedValues="categoryPropertiesEntry[1]"
        :key="categoryPropertiesEntry[0].name"/>
        <div v-if="formAccessibility == 'BASIC'" class="col-span-6 xl:col-span-6">
            <div class="font-semibold text-xl">Basic properties</div>
            <div v-for="categoryPropertiesEntry in computedPropertiesByCategoryMap">
                <div class="font-semibold text-xl">{{ categoryPropertiesEntry[0].label }}</div>
                <div v-for="modifiedValue in categoryPropertiesEntry[1]" :key="modifiedValue.definition.name">
                <ModifiedValueDisplay v-if="modifiedValue.definition.accessibility == 'BASIC'" :modifiedValue="modifiedValue" />
                </div>
            </div>
        </div>
    </div>
</template>