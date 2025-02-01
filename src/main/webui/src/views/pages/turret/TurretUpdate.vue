<script setup>  
import TurretUpdate from '@/components/turret/TurretUpdate.vue';
import { useRoute } from 'vue-router';

const route = useRoute()
const id = route.params.id;

</script>
<template>
    <Suspense>
        <template #default>
            <TurretUpdate :id="id" />
        </template>
        <template #fallback>
            <Loading />
        </template>
    </Suspense>
</template>