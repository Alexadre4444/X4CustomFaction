<script lang="ts" setup>
import Research from '@/model/common/Research';



const props = defineProps({
    research: Research
});

</script>
<template>
    <Tag v-tooltip.top="research.description" severity="info" :value="research.label"></Tag>
</template>
