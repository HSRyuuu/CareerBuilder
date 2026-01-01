<template>
  <v-dialog
    v-model="isOpen"
    :max-width="maxWidth"
    :persistent="persistent"
    class="document-modal"
  >
    <v-card class="document-modal-card">
      <!-- 헤더: 모달 이름 + 닫기 버튼 -->
      <div class="modal-header">
        <span class="modal-name">{{ name }}</span>
        <button class="modal-close-btn" @click="handleClose">
          <v-icon size="20">mdi-close</v-icon>
        </button>
      </div>

      <!-- 본문 영역 -->
      <div class="modal-body">
        <!-- 제목 -->
        <h2 class="modal-title">{{ title }}</h2>

        <!-- 본문 콘텐츠 (slot) -->
        <div class="modal-content">
          <slot />
        </div>
      </div>

      <!-- 하단 버튼 영역 (optional slot) -->
      <div v-if="$slots.actions" class="modal-actions">
        <slot name="actions" />
      </div>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { computed } from 'vue';

export type TDocumentModalProps = {
  modelValue: boolean;
  name?: string;
  title: string;
  maxWidth?: string | number;
  persistent?: boolean;
};

const {
  modelValue,
  name = '',
  title,
  maxWidth = 600,
  persistent = false,
} = defineProps<TDocumentModalProps>();

const emit = defineEmits<{
  'update:modelValue': [boolean];
  close: [void];
}>();

const isOpen = computed({
  get: () => modelValue,
  set: (value: boolean) => emit('update:modelValue', value),
});

const handleClose = () => {
  isOpen.value = false;
  emit('close');
};
</script>

<style lang="scss" scoped>
.document-modal-card {
  border-radius: 16px !important;
  overflow: hidden;
  background: var(--bg-primary) !important;
}

.modal-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 20px;
  border-bottom: 1px solid var(--border-color);
  background: var(--bg-secondary);
}

.modal-name {
  font-size: 13px;
  font-weight: 500;
  color: var(--text-tertiary);
  letter-spacing: 0.02em;
}

.modal-close-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 32px;
  height: 32px;
  border: none;
  border-radius: 8px;
  background: transparent;
  color: var(--text-tertiary);
  cursor: pointer;
  transition: all 0.2s ease;

  &:hover {
    background: var(--bg-tertiary);
    color: var(--text-primary);
  }
}

.modal-body {
  padding: 24px;
  max-height: 70vh;
  overflow-y: auto;

  &::-webkit-scrollbar {
    width: 6px;
  }

  &::-webkit-scrollbar-track {
    background: transparent;
  }

  &::-webkit-scrollbar-thumb {
    background: var(--border-color);
    border-radius: 3px;

    &:hover {
      background: var(--text-tertiary);
    }
  }
}

.modal-title {
  font-size: 22px;
  font-weight: 700;
  color: var(--text-primary);
  margin: 0 0 20px 0;
  line-height: 1.3;
}

.modal-content {
  font-size: 14px;
  color: var(--text-secondary);
  line-height: 1.7;

  :deep(p) {
    margin: 0 0 12px 0;

    &:last-child {
      margin-bottom: 0;
    }
  }

  :deep(h3) {
    font-size: 16px;
    font-weight: 600;
    color: var(--text-primary);
    margin: 20px 0 12px 0;

    &:first-child {
      margin-top: 0;
    }
  }

  :deep(ul),
  :deep(ol) {
    margin: 12px 0;
    padding-left: 20px;
  }

  :deep(li) {
    margin-bottom: 8px;
  }

  :deep(strong) {
    font-weight: 600;
    color: var(--text-primary);
  }
}

.modal-actions {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 12px;
  padding: 16px 24px;
  border-top: 1px solid var(--border-color);
  background: var(--bg-secondary);
}
</style>