<script lang="ts" setup>
import ProductionMethod from '@/model/common/ProductionMethod';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import { ProductionMethodService } from '@/services/ProductionMethodService';
import { ref } from 'vue';

const model = defineModel({
    type: Array<ProductionMethodName>
});

const allMethods = ref<ProductionMethod[]>(await ProductionMethodService.getAll());

</script>
<template>
    <SelectButton v-model="model" :options="allMethods" optionLabel="label" optionValue="name" multiple aria-labelledby="multiple" />
</template>
