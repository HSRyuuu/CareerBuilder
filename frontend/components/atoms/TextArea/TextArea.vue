<template>
  <div class="cb-textarea-wrapper">
    <label v-if="label" :for="textareaId" class="cb-textarea-label">
      {{ label }}
      <span v-if="required" class="cb-textarea-required">*</span>
    </label>
    <textarea
      :id="textareaId"
      :class="textareaClasses"
      :value="modelValue"
      :placeholder="placeholder"
      :disabled="disabled"
      :readonly="readonly"
      :required="required"
      :rows="rows"
      @input="handleInput"
      @blur="handleBlur"
      @focus="handleFocus"
    ></textarea>
  </div>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue';
import { CommonSize } from '@/constants/enums/style-enum';
import type { TextAreaProps } from '@/types/component-types';

const props = withDefaults(defineProps<TextAreaProps>(), {
  size: CommonSize.Medium,
  disabled: false,
  readonly: false,
  required: false,
  error: false,
  rows: 4,
});

const emit = defineEmits<{
  'update:modelValue': [value: string];
  blur: [];
  focus: [];
}>();

const textareaId = ref(`cb-textarea-${Math.random().toString(36).substr(2, 9)}`);

const textareaClasses = computed(() => {
  return [
    'cb-textarea',
    `cb-textarea--${props.size}`,
    {
      'cb-textarea--error': props.error,
      'cb-textarea--disabled': props.disabled,
      'cb-textarea--readonly': props.readonly,
    },
  ];
});

const handleInput = (event: Event) => {
  const target = event.target as HTMLTextAreaElement;
  emit('update:modelValue', target.value);
};

const handleBlur = () => {
  emit('blur');
};

const handleFocus = () => {
  emit('focus');
};
</script>

<style lang="scss">
@use './TextArea.scss';
</style>

