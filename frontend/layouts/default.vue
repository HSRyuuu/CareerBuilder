<template>
  <div class="layout-modern">
    <!-- 상단 헤더 -->
    <header class="layout-header">
      <div class="layout-header-left">
        <div class="layout-logo">
          <div class="layout-logo-icon">CB</div>
          <span class="layout-logo-text">CareerBuilder</span>
        </div>
      </div>
      <div class="layout-header-right">
        <slot name="header-controls" />
      </div>
    </header>

    <!-- 좌측 사이드바 -->
    <aside :class="['layout-sidebar', { 'layout-sidebar--collapsed': isSidebarCollapsed }]">
      <!-- 토글 버튼 -->
      <Button
        :variant="ButtonVariant.Secondary"
        :size="CommonSize.Small"
        icon-only
        :icon="isSidebarCollapsed ? 'mdi-menu' : 'mdi-menu-open'"
        class="layout-sidebar-toggle"
        @click="toggleSidebar"
      />

      <!-- 사용자 정보 -->
      <div
        :class="['layout-sidebar-user', { 'layout-sidebar-user--collapsed': isSidebarCollapsed }]"
      >
        <div v-if="isSidebarCollapsed" class="layout-sidebar-user-icon-only">
          <v-icon>mdi-account-circle</v-icon>
        </div>
        <template v-else>
          <div class="layout-sidebar-user-avatar">
            <v-icon>mdi-account-circle</v-icon>
          </div>
          <div class="layout-sidebar-user-info">
            <div class="layout-sidebar-user-name">사용자</div>
            <div class="layout-sidebar-user-email">user@example.com</div>
          </div>
        </template>
      </div>

      <!-- 메뉴 리스트 -->
      <nav class="layout-sidebar-nav">
        <NuxtLink
          v-for="item in menu.menuItems"
          :key="item.key"
          :to="item.path"
          class="layout-sidebar-nav-item"
          :class="{ 'layout-sidebar-nav-item--active': menu.isActive(item.key) }"
          @click="menu.select(item.key)"
        >
          <div class="layout-sidebar-nav-item-icon">
            <v-icon>{{ item.icon }}</v-icon>
          </div>
          <transition name="fade">
            <span v-if="!isSidebarCollapsed" class="layout-sidebar-nav-item-label">
              {{ item.label }}
            </span>
          </transition>
        </NuxtLink>
      </nav>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main :class="['layout-main', { 'layout-main--expanded': isSidebarCollapsed }]">
      <div class="layout-content">
        <slot />
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import Button from '@/components/atoms/Button/Button.vue';
import { ButtonVariant, CommonSize } from '@/constants/enums/style-enum';
import { useMenu } from '@/composables/useMenu';

const route = useRoute();
const menu = useMenu();
const isSidebarCollapsed = ref(false);

// 페이지 이동 시 메뉴 상태 동기화
watch(
  () => route.path,
  (path) => {
    menu.selectByPath(path);
  },
  { immediate: true }
);

const toggleSidebar = () => {
  isSidebarCollapsed.value = !isSidebarCollapsed.value;
};
</script>

<style lang="scss" scoped>
@use '@/styles/layouts/default-layout.scss';
</style>
