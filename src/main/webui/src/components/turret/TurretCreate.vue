<script lang="ts" setup>
import Size from '@/model/common/Size';
import TurretCreate from '@/model/turret/TurretCreate';
import { TurretService } from '@/services/TurretServiceMain';
import { ref } from 'vue';

const props = defineProps<{
  onCancel: (payload: MouseEvent) => void,
  onSave: () => void
}>();

const formLabel = ref();
const formSize = ref();

const formError = ref();
const listSizes = ref<Size[]>([Size.MEDIUM, Size.LARGE]);

const createInstance = () => {
    return TurretService.create(new TurretCreate(formLabel.value, formSize.value));
}

const callOnSave = () => {
    createInstance().then(() => {
        props.onSave();
    }).catch(error => {
        formError.value = error.response.data;
    });
};

</script>
<template>
<div class="card flex flex-col gap-4">
    <Message v-if="formError" severity="error" icon="pi pi-times-circle" class="mb-2">{{formError}}</Message>
    <div class="flex flex-col gap-2">
        <label for="label">Label</label>
        <InputText id="label" type="text" v-model="formLabel" />
    </div>
    <div class="flex flex-col gap-2">
        <label for="size">Size</label>
        <Select  id="chassis" v-model="formSize" 
        :options="listSizes" 
        optionLabel="label" 
        :filter="true" />
    </div>
</div>
<div class="flex flex-wrap items-start gap-4 justify-end">
    <Button label="Cancel" severity="secondary" @click="props.onCancel" />
    <Button label="Save" @click="callOnSave"  />
</div>
</template>
