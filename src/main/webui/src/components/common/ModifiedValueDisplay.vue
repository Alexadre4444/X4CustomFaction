<script lang="ts" setup>
import ModifiedValue from '@/model/common/ModifiedValue';
import { computed } from 'vue';

const props = defineProps({
    modifiedValue: ModifiedValue
});

const computeSeverity = () => {
    if(props.modifiedValue.definition.reverse) {
        return computeSeverityReverse(props.modifiedValue);
    } else {
        return computeSeverityNormal(props.modifiedValue);
    }
}
const computeSeverityNormal = (modifier: ModifiedValue) => {
    let valueToCompare = modifier.modifierList.length > 0 ? modifier.modifierSum() : modifier.finalDoubleValue - modifier.baseDoubleValue;
    if (valueToCompare > 0) {
        return "success";
    } else if (valueToCompare < 0) {
        return "danger";
    } else {
        return "secondary";
    }
}

const computeSeverityReverse = (modifier: ModifiedValue) => {
    let valueToCompare = modifier.modifierList.length > 0 ? modifier.modifierSum() : modifier.finalDoubleValue - modifier.baseDoubleValue;
    if (valueToCompare > 0) {
        return "danger";
    } else if (valueToCompare < 0) {
        return "success";
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
