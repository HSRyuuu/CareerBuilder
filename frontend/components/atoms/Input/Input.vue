<template>
  <div class="cb-input-wrapper">
    <label v-if="label" :for="inputId" class="cb-input-label">
      {{ label }}
      <span v-if="required" class="cb-input-required">*</span>
    </label>
    <input
      :id="inputId"
      :class="inputClasses"
      :type="type"
      :value="modelValue"
      :placeholder="placeholder"
      :disabled="disabled"
      :readonly="readonly"
      :required="required"
      @input="handleInput"
      @blur="handleBlur"
      @focus="handleFocus"
    />
  </div>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue';
import { CommonSize } from '@/constants/enums/style-enum';
import type { InputProps } from '@/types/component-types';

const props = withDefaults(defineProps<InputProps>(), {
  type: 'text',
  size: CommonSize.Medium,
  disabled: false,
  readonly: false,
  required: false,
  error: false,
});

const emit = defineEmits<{
  'update:modelValue': [value: string | number];
  blur: [];
  focus: [];
}>();

const inputId = ref(`cb-input-${Math.random().toString(36).substr(2, 9)}`);

const inputClasses = computed(() => {
  return [
    'cb-input',
    `cb-input--${props.size}`,
    {
      'cb-input--error': props.error,
      'cb-input--disabled': props.disabled,
      'cb-input--readonly': props.readonly,
    },
  ];
});

const handleInput = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const value = props.type === 'number' ? Number(target.value) : target.value;
  emit('update:modelValue', value);
};

const handleBlur = () => {
  emit('blur');
};

const handleFocus = () => {
  emit('focus');
};
</script>

<style lang="scss">
@use './Input.scss';
</style>

