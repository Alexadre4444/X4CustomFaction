<script lang="ts" setup>
import { useToast } from 'primevue/usetoast';
import { NotificationService } from './services/NotificationService';

const toast = useToast();
NotificationService.setToastInstance(toast);</script>

<template>
    <router-view />
</template>

<style scoped></style>
