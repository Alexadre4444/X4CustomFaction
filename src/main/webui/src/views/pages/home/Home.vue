<script lang="ts" setup>
import Loading from '@/components/common/Loading.vue';
import TurretsSummary from '@/components/turret/TurretsSummary.vue';
import { NotificationService } from '@/services/NotificationService';
import { ref } from 'vue';

const displayGenerate = ref(false);
const displaySettings = ref(false);

const refreshKey = ref(0);

const menu = ref([{
        label: 'Generate new version',
        icon: 'pi pi-fw pi-file',
        command: () => openGenerate()
    },
    {
        label: 'Settings',
        icon: 'pi pi-fw pi-cog',
        command: () => openSettings()
    }
])

const openGenerate = () => {
    displayGenerate.value = true;
}

const openSettings = () => {
    displaySettings.value = true;
}

const closeGenerate = () => {
    displayGenerate.value = false;
}

const onGenerate = () => {
    displayGenerate.value = false;
    NotificationService.success("New version generated and available in \"versions\" folder at the root of the tool.");
    refreshKey.value++;
}

const closeSettings = () => {
    displaySettings.value = false;
}

const onUpdateSettings = () => {
    displaySettings.value = false;
    NotificationService.success("Settings updated");
}


</script>
<template>
    <div class="mb-4">
        <Menubar :model="menu" breakpoint="500px">
            <template #end>
                <Dialog header="Generate new version" v-model:visible="displayGenerate" :breakpoints="{ '960px': '75vw' }"
                    :style="{ width: '30vw' }" :modal="true">
                    <Suspense>
                        <ModUpdate :onCancel="closeGenerate" :onSave="onGenerate"/>
                    </Suspense>
                </Dialog>
                <Dialog header="Settings" v-model:visible="displaySettings" :breakpoints="{ '960px': '75vw' }"
                    :style="{ width: '30vw' }" :modal="true">
                    <Suspense>
                        <template #default>
                            <ModSettings :onCancel="closeSettings" :onSave="onUpdateSettings"/>
                        </template>
                        <template #fallback>
                            <Loading />
                        </template>
                    </Suspense>
                </Dialog>
            </template>
        </Menubar>
    </div>
    <div class="grid grid-cols-4" :key="refreshKey">
        <Suspense>
            <template #default>
                <TurretsSummary />
            </template>
            <template #fallback>
                <Loading />
            </template>
        </Suspense>
    </div>
</template>
