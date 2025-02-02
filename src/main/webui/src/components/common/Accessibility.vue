<script lang="ts" setup>
import { ref } from 'vue';

const model = ref<string>('BASIC');

const options = ref<string[]>(["BASIC", "ADVANCED"]);

</script>

<template>
    <SelectButton v-model="model" :options="options" />
</template>