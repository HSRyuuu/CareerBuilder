<template>
  <div class="description-box" :class="variantClass">
    <slot>{{ text }}</slot>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';

export type DescriptionBoxVariant = 'info' | 'warning' | 'success' | 'neutral';

export interface DescriptionBoxProps {
  text?: string;
  variant?: DescriptionBoxVariant;
}

const { text = '', variant = 'info' } = defineProps<DescriptionBoxProps>();

const variantClass = computed(() => {
  return `description-box--${variant}`;
});
</script>

<style lang="scss" scoped>
@import './DescriptionBox.scss';
</style>
