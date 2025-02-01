<script lang="ts" setup>
import Modifier from '@/model/common/Modifier';

const props = defineProps({
    modifier: Modifier
});

const isProperty = () => {
    return props.modifier.propertyDefinition != null;
}

const computeSeverity = () => {
    if(isProperty()) {
        if(props.modifier.propertyDefinition.reverse) {
            return computeSeverityReverse(props.modifier.value);
        } else {
            return computeSeverityNormal(props.modifier.value);
        }
    } else {
        if(props.modifier.category.reverse) {
            return computeSeverityReverse(props.modifier.value);
        } else {
            return computeSeverityNormal(props.modifier.value);
        }
    }
}

const computeSeverityNormal = (modifier: number) => {
    if (modifier > 0) {
        return "success";
    } else if (modifier < 0) {
        return "danger";
    } else {
        return "secondary";
    }
}

const computeSeverityReverse = (modifier: number) => {
    if (modifier > 0) {
        return "danger";
    } else if (modifier < 0) {
        return "success";
    } else {
        return "secondary";
    }
}
const formatValue = () => {
    return (props.modifier.propertyDefinition ? props.modifier.propertyDefinition.label : props.modifier.category.label) + " " + props.modifier.value.toFixed(0) + '%';
}

</script>
<template>
    <Tag :severity="computeSeverity()" :value="formatValue()"></Tag>
</template>
