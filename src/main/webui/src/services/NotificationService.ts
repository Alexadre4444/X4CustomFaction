import { ToastServiceMethods } from "primevue/toastservice";

let toastInstance : ToastServiceMethods;

export const NotificationService = {
    setToastInstance: (toast: ToastServiceMethods) => {
        toastInstance = toast;
    },
    success: (message: string) => {
        toastInstance.add({severity:'success', summary: 'Success', detail: message, life: 3000});
    },
    error: (error: any) => {
        console.error(error);
        if(error && error.response && error.response.data && error.response.data) {
            toastInstance.add({severity:'error', summary: 'Error', detail: error.response.data, life: 3000});
        }
        else {
            toastInstance.add({severity:'error', summary: 'Error', detail: error.message, life: 3000});
        }
    }
};