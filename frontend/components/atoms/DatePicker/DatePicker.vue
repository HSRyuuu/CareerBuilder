<template>
  <DatePicker
    :id="id"
    :model-value="internalModelValue"
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
    :format="computedFormat"
    :placeholder="placeholder"
    :dark="isDark"
    :month-picker="type === 'month' || monthPicker"
    auto-apply
    @update:model-value="handleUpdate"
  />
</template>

<script setup lang="ts">
import { computed } from 'vue';
import DatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

export type TDatePickerProps = {
  modelValue: string | string[] | Date | Date[] | any | null;
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
  monthPicker?: boolean;
};

const colorMode = useColorMode();
const isDark = computed(() => colorMode.value === 'dark');

const {
  modelValue,
  locale = 'ko',
  customLocale = {
    cancel: '취소',
    select: '선택',
    today: '오늘',
  },
  useToday = false,
  id = 'datePicker',
  format = 'yyyy-MM-dd',
  type = 'date',
  range = false,
  disabled = false,
  readonly = false,
  placeholder = '',
  useTimePicker = false,
  useTimePickerInline = false,
  monthPicker = false,
} = defineProps<TDatePickerProps>();

const emit = defineEmits<{
  'update:modelValue': [any];
}>();

const isMonthMode = computed(() => type === 'month' || monthPicker);

const internalModelValue = computed(() => {
  if (!modelValue) return null;

  const parseDate = (val: any) => {
    if (!val) return null;
    if (val instanceof Date) return val;
    if (typeof val === 'string') {
      if (isMonthMode.value && val.length === 7) {
        const [year, month] = val.split('-').map(Number);
        return { month: month - 1, year };
      }
      return new Date(val);
    }
    return val;
  };

  if (Array.isArray(modelValue)) {
    return modelValue.map(parseDate);
  }
  return parseDate(modelValue);
});

const computedFormat = computed(() => {
  if (format !== 'yyyy-MM-dd') return format;
  if (isMonthMode.value) return 'yyyy-MM';
  return format;
});

const handleUpdate = (value: any) => {
  if (!value) {
    emit('update:modelValue', '');
    return;
  }

  const formatDate = (date: any) => {
    // month-picker가 켜져있을 때 { month: 0, year: 2025 } 형태의 객체가 올 수 있음
    if (date && typeof date === 'object' && 'month' in date && 'year' in date) {
      return `${date.year}-${String(date.month + 1).padStart(2, '0')}`;
    }

    if (date instanceof Date) {
      if (isMonthMode.value) {
        return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}`;
      }
      if (useTimePicker) {
        return date.toISOString().slice(0, 16); // YYYY-MM-ddTHH:mm 형식
      }
      // 로컬 타임존 반영하여 YYYY-MM-DD 반환
      const y = date.getFullYear();
      const m = String(date.getMonth() + 1).padStart(2, '0');
      const d = String(date.getDate()).padStart(2, '0');
      return `${y}-${m}-${d}`;
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

<style lang="scss" scoped>
@use './DatePicker.scss';
</style>
