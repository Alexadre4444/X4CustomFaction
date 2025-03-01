<script lang="ts" setup>
import { computed } from 'vue';



const props = defineProps<{
    customizationPoint: number;
    customisationBasePoint: number;
}>();

const remainingPoints = computed(() => {
    return (props.customisationBasePoint - props.customizationPoint);
});

const severity = computed(() => {
    if (remainingPoints.value > props.customisationBasePoint) {
        return "warn";
    } else if (remainingPoints.value == props.customisationBasePoint) {
        return "secondary";
    } else if (remainingPoints.value < 0) {
        return "danger";
    } else {
        return "success";
    }
});

const formatValue = () => {
    return remainingPoints.value;
}

</script>

<template>
    <div class="flex flex-col gap-2">
        <div class="font-semibold text-xl">Remaining customization point</div>
        <Tag :severity="severity" :value="formatValue()"></Tag>
    </div>
</template>