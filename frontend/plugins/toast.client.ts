import { defineNuxtPlugin } from 'nuxt/app';
import Toast, {
  type PluginOptions,
  POSITION,
  useToast as useToastVueToastification,
} from 'vue-toastification';
import 'vue-toastification/dist/index.css';

const options: PluginOptions = {
  position: POSITION.TOP_RIGHT,
  closeOnClick: false,
  hideProgressBar: true,
  draggable: false,
  timeout: 3000,
  toastClassName: 'toast',
};

export default defineNuxtPlugin((nuxtApp) => {
  nuxtApp.vueApp.use(Toast, options);
  nuxtApp.provide('toast', useToastVueToastification());
});
