<script lang="ts" setup>
import Customizer from '@/model/common/Customizer';
import CustomizerCategory from '@/model/common/CustomizerCategory';
import CustomizerComponent from '@/model/common/CustomizerComponent';
import { Ref } from 'vue';

const model = defineModel({
    type: Map<CustomizerComponent, Ref<Customizer>>
});

const changeValue = (category: CustomizerComponent, customizer: Customizer) => {
    model.value.get(category)!.value = customizer;
    emit('change', model.value);
}

class CustomizerGroup {
    category: CustomizerCategory;
    customizers: Customizer[];
    constructor(category: CustomizerCategory, customizers: Customizer[]) {
        this.category = category;
        this.customizers = customizers;
    }
}

const getByCategory = (customizers: Customizer[]) => {
    const groups = Array<CustomizerGroup>();
    customizers.forEach(customizer => {
        const category = customizer.category;
        const group = groups.find(group => group.category.name === category.name);
        if (group) {
            group.customizers.push(customizer);
        } else {
            groups.push(new CustomizerGroup(category, [customizer]));
        }
    });
    return groups;
}

const emit = defineEmits(['change']);

emit('change', model.value);

</script>
<template>
    <div v-for="(category, index) in model" class="flex flex-col gap-2" :key="category[0].name">
        <div class="flex flex-col gap-2">
            <label :for="category[0].name">{{ category[0].label }}</label>
            <Select :id="category[0].name" v-model="category[1].value" 
            :options="getByCategory(category[0].customizers)"
            :showClear="true"
            :optionGroupLabel="group => group.category.label"
            :optionGroupChildren="group => group.customizers"
            @change="event => changeValue(category[0], event.value)"
            optionLabel="label" />
        </div>
        <ModifiersDisplay v-if="category[1]"
                        :modifiers="category[1].value?.modifiers.modifiers"/>
    </div>
</template>
