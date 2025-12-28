<script setup lang="ts">
import { useAuthStore } from '@/stores/auth';

definePageMeta({
  middleware: defineNuxtRouteMiddleware(() => {
    const authStore = useAuthStore();
    if (authStore.isAuthenticated) {
      return navigateTo('/home');
    }
    return navigateTo('/welcome');
  }),
});
</script>