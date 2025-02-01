<script lang="ts" setup>
import Category from '@/model/common/Category';
import ModifiedValue from '@/model/common/ModifiedValue';
import ModifiedValueDisplay from './ModifiedValueDisplay.vue';

const props = defineProps({
    category: Category,
    modifiedValues: Array<ModifiedValue>
});

</script>
<template>
    <div class="col-span-6 xl:col-span-2">
        <div class="card flex flex-col gap-2 w-full">
            <div class="font-semibold text-xl">{{ category.label }}</div>
            <ModifiedValueDisplay v-for="modifiedValue in modifiedValues" :key="modifiedValue.definition.name"
            :modifiedValue="modifiedValue" />
        </div>
    </div>
</template>
