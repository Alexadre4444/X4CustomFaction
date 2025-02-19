<script lang="ts" setup>


const props = defineProps({
    size: String
});

</script>
<template>
    <Tag severity="info" :value="size"></Tag>
</template>
