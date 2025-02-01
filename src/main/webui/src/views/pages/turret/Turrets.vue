<script lang="ts" setup>
import TurretCreate from '@/components/turret/TurretCreate.vue';
import Size from '@/model/common/Size';
import Turret from '@/model/turret/Turret.ts';
import { TurretService } from '@/services/TurretServiceMain';
import { computed, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const displayCreate = ref(false);
const route = useRoute()
const router = useRouter();

const openCreate = () => {
    displayCreate.value = true;
}
const closeCreate = () => {
    displayCreate.value = false;
}

const menu = ref([{
        label: 'Create',
        icon: 'pi pi-fw pi-plus',
        command: () => {openCreate();}
    }
])

const search = ref<string>("");
const filerSize = ref<Size>();

const turrets = ref<Turret[]>([]);

const finalTurretsList = computed(() => {
    return turrets.value
        .filter((turret) => search.value || turret.label.toLowerCase().includes(search.value.toLowerCase()))
        .filter((turret) => filerSize.value == null || turret.size === filerSize.value?.key)
        .sort((tur1, tur2) => tur1.label.localeCompare(tur2.label));
})

const refreshTurrets = () => {
    TurretService.getAll()
    .then((turretList) => {
        turrets.value = turretList;
    })
    .then(() => {
        if(route.query.action === "create") {
            openCreate();
            router.push({query: {action: null}});
        }
    })
}

const onSave = () => {
    closeCreate();
    refreshTurrets();
}

refreshTurrets();


</script>
<template>
    <div class="mb-4">
        <Menubar :model="menu" breakpoint="500px">
            <template #end>
                <Dialog header="New turret" v-model:visible="displayCreate" :breakpoints="{ '960px': '75vw' }" :style="{ width: '30vw' }" :modal="true">
                    <TurretCreate :onCancel="closeCreate" :onSave="onSave"/>
                </Dialog>
                <div class="flex gap-4">
                    <IconField iconPosition="left">
                        <InputIcon class="pi pi-filter" />
                        <Select placeholder="Size" v-model="filerSize" :options="Size.values()" option-label="label"/>
                    </IconField>
                    <IconField iconPosition="left">
                        <InputIcon class="pi pi-search" />
                        <InputText type="text" placeholder="Search" v-model="search" />
                    </IconField>
                </div>
            </template>
        </Menubar>
    </div>
    <div class="grid xl:grid-cols-4 gap-4">
        <div v-for="turret in finalTurretsList" :key="turret.id">
            <Suspense>
                <template #default>
                    <TurretCard :turret="turret" @delete="refreshTurrets"></TurretCard>
                </template>
                <template #fallback>
                    <ProgressSpinner />
                </template>
            </Suspense>
        </div>
    </div>
</template>
