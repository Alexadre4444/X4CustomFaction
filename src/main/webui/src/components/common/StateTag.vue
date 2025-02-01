<script lang="ts" setup>

const props = defineProps({
    state: String
});


const severity = (state:string) => {
    switch (state) {
        case "VALID":
            return "success";
        case "DRAFT":
            return "warn";
        case "DEPLOYED":
            return "primary";
        default:
            return "info";
    }
}

</script>
<template>
    <Tag :severity="severity(state)" :value="state"></Tag>
</template>
