<script lang="ts" setup>
import { computed } from 'vue';

const props = defineProps<{
    reversed: boolean,
    realModifier: number
}>();

const model = defineModel({
    type: Number
});

const emit = defineEmits(['change']);

const valueToTest = computed<number>(() => {
    return props.reversed ? -props.realModifier : props.realModifier;
});

const isNerfed = computed(() => {
    return valueToTest.value < 0;
});

const isBuffed = computed(() => {
    return valueToTest.value > 0;
});

const isNeutral = computed(() => {
    return valueToTest.value === 0;
});

const onChange = () => {
    emit('change', model.value);
}

</script>

<style>
.neutral .p-slider-range {
    background-color: #475569;
}

.nerf .p-slider-range {
    background-color: #ff0000;
}

.buff .p-slider-range {
    background-color: #10b981;
}
</style>

<template>
    <Slider :class="{nerf: isNerfed, buff: isBuffed, neutral: isNeutral}"
     v-model="model" :min=-50 :max=100 @change="onChange()" />
</template>
