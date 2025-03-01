<script lang="ts" setup>
import Category from '@/model/common/Category';
import FreeCustomizerValue from '@/model/common/FreeCustomizerValue';
import { computed } from 'vue';

const model = defineModel({
    type: Array<FreeCustomizerValue>
});

const props = defineProps<{
    accessibility: string
}>();

const emit = defineEmits(['change']);

const onChange = (freeCustomizerValue:FreeCustomizerValue) => {
    emit('change', freeCustomizerValue);
}

const freeCustomizerValueByCategory = computed<Map<Category, Array<FreeCustomizerValue>>>(() => {
    const map = new Map<Category, Array<FreeCustomizerValue>>();
    model.value
        .sort((a:FreeCustomizerValue, b:FreeCustomizerValue) => a.propertyDefinition.category.name.localeCompare(b.propertyDefinition.category.name))
        .forEach((freeCustomizerValue) => {
        if(props.accessibility == 'ADVANCED' || freeCustomizerValue.propertyDefinition.accessibility == 'BASIC') {
            const category = freeCustomizerValue.propertyDefinition.category;
            let mapEntry = map.keys().find((key) => key.name === category.name);
            if (!mapEntry) {
                mapEntry = category;
                map.set(category, []);
            }
            map.get(mapEntry).push(freeCustomizerValue);
        }
    });
    return map;
});

</script>
<template>
        <div v-for="(groupEntry, index) in freeCustomizerValueByCategory" class="col-span-3 xl:col-span-3 mb-6" :key="groupEntry[0].name">
            <div class="font-semibold text-l">{{ groupEntry[0].label }}</div>
            <div v-for="freeCustomizerValue in groupEntry[1]" class="flex flex-col gap-2 pb-2" :key="freeCustomizerValue[0]">
                <FreeCustomizerComponent :configuration="freeCustomizerValue" @change="onChange(freeCustomizerValue)" />
            </div>
        </div>
</template>
