<script setup>
import { ModService } from '@/services/ModService';
import { ref } from 'vue';

const props = defineProps({
  onCancel: Function,
  onSave: Function
});

const formError = ref();

const callOnGenerate = () => {
    ModService.generateNewVersion()
    .then(() => {
        props.onSave();
    }).catch(error => {
        formError.value = error.response.data;
    });
};

</script>
<template>
<div class="card flex flex-col gap-4">
    <Message v-if="formError" severity="error" icon="pi pi-times-circle" class="mb-2">{{formError}}</Message>
    <Message severity="info" icon="pi pi-info-circle">
        <p>A new version of the mod will be generated.</p>
        <p>Once done, generated elements cannot be deleted anymore.</p>
    </Message>
</div>
<div class="flex flex-wrap items-start gap-4 justify-end">
    <Button label="Cancel" severity="secondary" @click="props.onCancel" />
    <Button label="Generate" @click="callOnGenerate"  />
</div>
</template>
