<script lang="ts" setup>
import FreeCustomizerValue from '@/model/common/FreeCustomizerValue';
import { computed, ref } from 'vue';

const props = defineProps<{
    configuration: FreeCustomizerValue
}>();

const model = ref<number>(props.configuration.realModifierValue);

const valueToTest = computed<number>(() => {
    let value = props.configuration.realModifierValue;
    if(value == undefined) {
        value = Number.parseFloat(props.configuration.propertyFinalValue) - Number.parseFloat(props.configuration.propertyBaseValue);
    }
    return props.configuration.propertyDefinition.reverse ? -value: value;
});

const emit = defineEmits(['change']);

const computeSeverity = () => {
    if (valueToTest.value > 0) {
        return "success";
    } else if (valueToTest.value < 0) {
        return "danger";
    } else {
        return "secondary";
    }
}

const displayedModifier = computed(() => {
    return props.configuration.propertyDefinition.isFree ? props.configuration.desiredModifierValue : props.configuration.realModifierValue;
});

const displayedValue = computed(() => {
    return props.configuration.propertyBaseValue + " → " + props.configuration.propertyFinalValue +
         (displayedModifier.value != undefined ? " (" + displayedModifier.value + "%)" : "");
});

const displayedLabel = computed<string>(() => {
    return props.configuration.propertyDefinition.label + 
        (props.configuration.propertyDefinition.unit ? " (" + props.configuration.propertyDefinition.unit + ")" : "");
});

const onChange = () => {
    props.configuration.desiredModifierValue = model.value;
    emit('change', model.value);
}

</script>

<template>
    <label> {{ displayedLabel }}</label>
    <Tag :severity="computeSeverity()" :value="displayedValue"/>
    <FreeCustomizersSlider v-if="props.configuration.propertyDefinition.isFree && props.configuration.realModifierValue != undefined" v-model="model"
     :reversed="props.configuration.propertyDefinition.reverse"
     :real-modifier="props.configuration.realModifierValue"
     @change="onChange" />
</template>
