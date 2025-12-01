<template>
  <VSelect
    class="selectbox"
    :model-value="modelValue"
    :items="items"
    :placeholder="placeholder"
    :density="size"
    :variant="variant === FormVariant.Regular ? undefined : variant"
    :multiple="multiple"
    :disabled="disabled"
    :readonly="readonly"
    :menu="menu"
    :menu-props="{ contentClass: 'select-menu' }"
    item-props
    hide-details
    @update:model-value="handleUpdate"
  >
  </VSelect>
</template>

<script setup lang="ts" generic="T extends TModelValue">
import { VSelect } from 'vuetify/lib/components/index.mjs';
import { FormSize, FormVariant } from '@/constants/enums/style-enum';

export type TModelValue = string | number | null | Array<string | number>;

export type TSelectItem = {
  title: string | number;
  value: TModelValue;
  disabled?: boolean;
  subtitle?: string;
};

export type TSelectProps<T extends TModelValue = TModelValue> = {
  modelValue: T;
  items: TSelectItem[];
  placeholder?: string;
  size?: FormSize;
  variant?: FormVariant;
  multiple?: boolean;
  disabled?: boolean;
  readonly?: boolean;
  menu?: boolean;
};

const {
  placeholder = 'placeholder',
  size = FormSize.Default,
  variant = FormVariant.Outlined,
  multiple = false,
  disabled = false,
  readonly = false,
  menu = false,
} = defineProps<TSelectProps<T>>();

const emit = defineEmits<{
  'update:modelValue': [T];
}>();

const handleUpdate = (value: T) => {
  emit('update:modelValue', value);
};
</script>

<style lang="scss" scoped>
@use '@/components/atoms/Select/Select.scss';
</style>
