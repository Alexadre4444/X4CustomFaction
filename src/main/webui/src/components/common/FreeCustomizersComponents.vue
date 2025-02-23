<script lang="ts" setup>
import FreeCustomizerValue from '@/model/common/FreeCustomizerValue';

const model = defineModel({
    type: Array<FreeCustomizerValue>
});

const emit = defineEmits(['change']);

const onChange = (freeCustomizerValue:FreeCustomizerValue) => {
    emit('change', freeCustomizerValue);
}

</script>
<template>
        <div v-for="freeCustomizerValue in model" class="flex flex-col gap-2 pb-2" :key="freeCustomizerValue.propertyDefinition.name">
            <FreeCustomizerComponent :configuration="freeCustomizerValue" @change="onChange(freeCustomizerValue)" />
        </div>
</template>
