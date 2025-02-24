<script lang="ts" setup>
import ModifiedValue from '@/model/common/ModifiedValue';
import { computed } from 'vue';

const props = defineProps({
    modifiedValue: ModifiedValue
});

const valueToTest = computed<number>(() => {
    let value = props.modifiedValue.modfier;
    if(value == undefined) {
        value = props.modifiedValue.finalDoubleValue - props.modifiedValue.baseDoubleValue;
    }
    return props.modifiedValue.definition.reverse ? -value: value;
});


const computeSeverity = () => {
    if (valueToTest.value > 0) {
        return "success";
    } else if (valueToTest.value < 0) {
        return "danger";
    } else {
        return "secondary";
    }
}

const formatValue = () => {
    return props.modifiedValue.baseValueString + " → " +
        props.modifiedValue.finalValueString + 
        (props.modifiedValue.modifierList.length > 0 ? ' (' + props.modifiedValue.modifierSum().toFixed(0) + '%)' : "");
}

const fullLabel = computed(() => {
    return props.modifiedValue.definition.label + 
    (props.modifiedValue.definition.unit !== null ? ' (' + props.modifiedValue.definition.unit + ')' : '');
});

</script>
<template>
    <div class="flex flex-col gap-2">
        <label v-if="modifiedValue.definition">{{ fullLabel }}</label>
        <Tag :severity="computeSeverity()" :value="formatValue()"></Tag>
    </div>
</template>
