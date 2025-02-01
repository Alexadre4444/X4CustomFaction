<script lang="ts" async setup>
import Turret from '@/model/turret/Turret';
import { TurretService } from '@/services/TurretServiceMain';
import { computed, ref } from 'vue';

const turrets = ref<Turret[]>([]);
const turretsByState = computed<Record<string, Turret[]>>(() => {
    return turrets.value.reduce((acc, turret) => {
        if (!acc[turret.state]) {
            acc[turret.state] = [];
        }
        acc[turret.state].push(turret);
        return acc;
    }, {} as Record<string, Turret[]>);
});

turrets.value = await TurretService.getAll();

</script>
<template>
    <Card style="width: 25rem; overflow: hidden">
        <template #title>
            <div class="flex gap-4 mt-1">
                Turrets
            </div>
        </template>
        <template #content>
            <div v-if="turrets.length" class="grid gap-4 grid-cols-2 text-center">
                <div class="col">
                    <p class="mt-2" v-for="(turret, state) in turretsByState">
                        <StateTag :state="state" />
                    </p>
                </div>
                <div class="col">
                    <p class="mt-2"  v-for="(turret, state) in turretsByState">
                        {{ turret.length }}
                    </p>
                </div>
            </div>
            <p v-if="!turrets.length">
                No turrets.
            </p>
        </template>
        <template #footer>
            <div v-if="turrets.length" class="flex gap-4 mt-4">
                <router-link to="/turrets" class="w-full"> <Button severity="info" label="Details" class="w-full" /></router-link>
            </div>
            <div v-if="!turrets.length" class="flex gap-4 mt-4">
                <router-link to="/turrets?action=create" class="w-full"> <Button severity="info" label="Create turret" class="w-full" /></router-link>
            </div>
        </template>
    </Card>
</template>
