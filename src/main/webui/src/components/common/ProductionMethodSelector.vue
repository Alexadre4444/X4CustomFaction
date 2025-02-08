<script lang="ts" setup>
import ProductionMethod from '@/model/common/ProductionMethod';
import ProductionMethodName from '@/model/common/ProductionMethodName';
import { ProductionMethodService } from '@/services/ProductionMethodService';
import { ref } from 'vue';

const model = defineModel({
    type: Array<ProductionMethodName>
});

const emit = defineEmits(['change']);

const onChange = (event: { value: ProductionMethodName[] }) => {
    emit('change', event.value);
}

const allMethods = ref<ProductionMethod[]>(await ProductionMethodService.getAll());

</script>
<template>
    <SelectButton @change="onChange" v-model="model" :options="allMethods" optionLabel="label" optionValue="name" multiple aria-labelledby="multiple" />
</template>
