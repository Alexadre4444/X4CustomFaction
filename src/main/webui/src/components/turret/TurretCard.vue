<script lang="ts" async setup>
import ChassisSkin from '@/model/turret/ChassisSkin';
import Turret from '@/model/turret/Turret';
import { NotificationService } from '@/services/NotificationService';
import { TurretChassisService } from '@/services/TurretChassisService';
import { TurretService } from '@/services/TurretServiceMain';
import { computed, ref } from 'vue';

const props = defineProps({
  turret: Turret
});

const chassisSkin = ref<ChassisSkin>();

const iconUrl = computed<string>(() => {
    if(chassisSkin.value == null) {
        return `/assets/turret/notfound.png`;
    }
    return `/assets/turret/${chassisSkin.value.icon}.png`;
});

const emit = defineEmits(['delete']);

const route = computed(() => {
    return "/turrets/" + props.turret.id;
});

const isDeployed = computed(() => {
    return props.turret.state === "DEPLOYED";
});

const deleteInProgress = ref(false);

const deleteTurret = () => {
    deleteInProgress.value = true;
    TurretService.delete(props.turret.id)
    .then(() => {
        NotificationService.success("Turret deleted");
        emit('delete', props.turret);
    })
    .catch((error) => {
        NotificationService.error(error);
    })
    .finally(() => {
        deleteInProgress.value = false;
    });
}

await TurretChassisService.getAll()
.then(chassis => {
    let loadedChassis = chassis.find(chassis => chassis.name === props.turret.chassisName);
    chassisSkin.value = loadedChassis?.availableSkins.find(skin => skin.name === props.turret.chassisSkinName);
});

</script>
<template>
    <Card style="width: 25rem; overflow: hidden">
        <template #title>
            <div class="flex gap-4 mt-1">
                <StateTag :state="props.turret.state" />
                <SizeTag :size="props.turret._size.label" />
                <div>{{ props.turret.label }}</div>
            </div>
        </template>
        <template #content>
            <div class="flex gap-4 mt-1">
                <Image class="justify-content-between" :src="iconUrl" alt="Image" width="200" />
                <p class="m-0 w-full">
                    {{ props.turret.description != null ? props.turret.description : "No description" }}
                </p>
            </div>
        </template>
        <template #footer>
            <div class="flex gap-4 mt-1">
                <router-link :to="route" class="w-full" > <Button label="Update" class="w-full" /></router-link>
                <Button label="Delete" severity="danger" :disabled="isDeployed || deleteInProgress" :loading="deleteInProgress" @click="deleteTurret" class="w-full" />
            </div>
        </template>
    </Card>
</template>
