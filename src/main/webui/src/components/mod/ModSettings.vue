<script lang="ts" setup>
import { ModService } from '@/services/ModService';
import { Checkbox, InputNumber } from 'primevue';
import { ref } from 'vue';

const props = defineProps({
  onCancel: Function,
  onSave: Function
});

const formError = ref();

const callOnSave = async () => {
    ModService.updateInfos(formFactionTrigram.value, formResearchActivated.value)
    .then(() => {
        props.onSave();
    }).catch(error => {
        formError.value = error.response.data;
    });
};

let modInfos = await ModService.getActual();

const formVersion = ref(modInfos.version);
const formFactionTrigram = ref(modInfos.factionTrigram);
const formResearchActivated = ref<boolean>(modInfos.researchMode == "RESEARCH");

</script>
<template>
<div class="card flex flex-col gap-4">
    <Message v-if="formError" severity="error" icon="pi pi-times-circle" class="mb-2">{{formError}}</Message>
    <div class="flex flex-col gap-2">
        <label for="version">Version</label>
        <InputNumber id="version" v-model="formVersion" disabled />
    </div>
    <div class="flex flex-col gap-2">
        <label for="trigram">Faction trigram</label>
        <InputText id="trigram" v-model="formFactionTrigram"/>
    </div>
    <div class="flex flex-col gap-2">
        <label for="research">Research needed for blueprints</label>
        <Checkbox id="research" v-model="formResearchActivated" binary />
    </div>
</div>
<div class="flex flex-wrap items-start gap-4 justify-end">
    <Button label="Cancel" severity="secondary" @click="props.onCancel" />
    <Button label="Save" @click="callOnSave"  />
</div>
</template>
