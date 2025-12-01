<template>
  <DatePicker
    :id="id"
    :model-value="modelValue"
    :range="range"
    :locale="locale"
    :multi-calendars="range"
    :disabled="disabled"
    :readonly="readonly"
    :enable-time-picker="useTimePicker"
    :time-picker-inline="useTimePickerInline"
    :action-row="{ showNow: useToday }"
    :cancel-text="customLocale.cancel"
    :select-text="customLocale.select"
    :now-button-label="customLocale.today"
    :format="format"
    :placeholder="placeholder"
    auto-apply
    @update:model-value="handleUpdate"
  />
</template>

<script setup lang="ts">
import DatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

export type TDatePickerProps = {
  modelValue: string | string[] | Date | Date[] | null;
  locale?: string;
  customLocale?: {
    cancel: string;
    select: string;
    today: string;
  };
  useToday?: boolean;
  id?: string;
  format?: string;
  type?: string;
  range?: boolean;
  disabled?: boolean;
  readonly?: boolean;
  placeholder?: string;
  useTimePicker?: boolean;
  useTimePickerInline?: boolean;
};

const {
  locale = 'ko',
  customLocale = {
    cancel: '취소',
    select: '선택',
    today: '오늘',
  },
  useToday = false,
  id = 'datePicker',
  format = 'yyyy-MM-dd',
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  type = 'date',
  range = false,
  disabled = false,
  readonly = false,
  placeholder = '',
  useTimePicker = false,
  useTimePickerInline = false,
} = defineProps<TDatePickerProps>();

const emit = defineEmits<{
  'update:modelValue': [string | string[] | Date | Date[]];
}>();

const handleUpdate = (value: string | string[] | Date | Date[]) => {
  if (!value) {
    emit('update:modelValue', '');
    return;
  }

  const formatDate = (date: Date | string) => {
    if (date instanceof Date) {
      if (useTimePicker) {
        return date.toISOString().slice(0, 16); // YYYY-MM-ddTHH:mm 형식
      }
      return date.toISOString().split('T')[0]; // YYYY-MM-dd 형식
    }
    return date;
  };

  if (Array.isArray(value)) {
    const formattedDates = value.map(formatDate);
    emit('update:modelValue', formattedDates);
  } else {
    emit('update:modelValue', formatDate(value));
  }
};
</script>

<style lang="scss">
@use './DatePicker.scss';
</style>
