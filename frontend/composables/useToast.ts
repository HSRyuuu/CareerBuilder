import type { ToastInterface } from 'vue-toastification';

export const useToast = (): ToastInterface => {
  const { $toast } = useNuxtApp();
  return $toast as ToastInterface;
};
