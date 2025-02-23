<script lang="ts" setup>
import Modifier from '@/model/common/Modifier';
import PropertyDefinition from '@/model/common/PropertyDefinition';
import { computed } from 'vue';

const props = defineProps({
    modifiers: Array<Modifier>,
    applicableProperties: Array<PropertyDefinition>
});

const displayableModifiers = computed(() => {
    if (!props.modifiers) {
        return [];
    }
    return props.modifiers;
});

const shouldDisplayModifier = (modifier: Modifier) => {
    return props.applicableProperties 
        && (props.applicableProperties.some((property) => property.name === modifier.name)
        || props.applicableProperties.some((property) => property.category?.name === modifier.name));
};

</script>
<template>
    <div class="flex flex-wrap justify-center gap-2">
        <div v-for="modifier in displayableModifiers" :key="modifier.name">
            <ModifierDisplay v-if="shouldDisplayModifier(modifier)"
            :modifier="modifier" />
        </div>
    </div>
</template>
