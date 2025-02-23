<script lang="ts" setup>
import { ref } from 'vue';

const model = ref<string>('MODIFIER');

const options = ref<string[]>(["MODIFIER", "FREE"]);

</script>

<template>
    <SelectButton v-model="model" :options="options" />
</template>