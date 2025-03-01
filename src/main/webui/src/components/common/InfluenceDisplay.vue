<script lang="ts" setup>
import Influence from '@/model/common/Influence';

const props = defineProps<{
    influence: Influence
}>();

</script>
<template>
    <Tag v-tooltip.top="influence.description" severity="info" :value="influence.label"></Tag>
</template>