<script lang="ts" setup>
import FreeCustomizerValue from '@/model/common/FreeCustomizerValue';
import { computed, ref } from 'vue';

const props = defineProps<{
    configuration: FreeCustomizerValue
}>();

const model = ref<number>(props.configuration.modifierValue);

const valueToTest = computed<number>(() => {
    let value = props.configuration.modifierValue;
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

const displayedValue = computed(() => {
    return props.configuration.propertyBaseValue + " → " + props.configuration.propertyFinalValue +
         (props.configuration.modifierValue != undefined ? " (" + props.configuration.modifierValue + "%)" : "");
});

const displayedLabel = computed<string>(() => {
    return props.configuration.propertyDefinition.label + 
        (props.configuration.propertyDefinition.unit ? " (" + props.configuration.propertyDefinition.unit + ")" : "");
});

const onChange = () => {
    props.configuration.modifierValue = model.value;
    emit('change', model.value);
}

</script>

<template>
    <label> {{ displayedLabel }}</label>
    <Tag :severity="computeSeverity()" :value="displayedValue"/>
    <FreeCustomizersSlider v-if="props.configuration.propertyDefinition.isFree && props.configuration.modifierValue != undefined" v-model="model"
     :reversed="props.configuration.propertyDefinition.reverse" @change="onChange" />
</template>
