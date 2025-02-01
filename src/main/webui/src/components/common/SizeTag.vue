<script lang="ts" setup>


const props = defineProps({
    size: String
});

</script>
<template>
    <Tag severity="Secondary" :value="size"></Tag>
</template>
