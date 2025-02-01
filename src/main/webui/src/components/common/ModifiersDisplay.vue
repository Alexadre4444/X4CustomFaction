<script lang="ts" setup>
import Modifier from '@/model/common/Modifier';
import { computed } from 'vue';

const props = defineProps({
    modifiers: Array<Modifier>
});

const displayableModifiers = computed(() => {
    if (!props.modifiers) {
        return [];
    }
    return props.modifiers;
});

</script>
<template>
    <div class="flex flex-wrap justify-center gap-2">
        <ModifierDisplay v-for="modifier in displayableModifiers" :key="modifier.name"
        :modifier="modifier" />
    </div>
</template>
