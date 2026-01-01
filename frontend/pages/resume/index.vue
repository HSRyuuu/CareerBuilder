<template>
  <div />
</template>

<script setup lang="ts">
import { onMounted } from 'vue';

const toast = useToast();
const router = useRouter();

onMounted(() => {
  toast.info('준비 중입니다.');
  router.back();
});
</script>