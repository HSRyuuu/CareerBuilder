<template>
  <div class="layout-welcome">
    <!-- 상단 헤더 (필요시 추가) -->
    <header class="layout-header welcome-header">
      <div class="layout-header-left">
        <div class="layout-logo" @click="navigateTo('/')">
          <div class="layout-logo-icon">CB</div>
          <span class="layout-logo-text">CareerBuilder</span>
        </div>
      </div>
    </header>

    <main class="layout-main-full">
      <slot />
    </main>
  </div>
</template>

<script setup lang="ts">
// Welcome 레이아웃은 사이드바가 없음
</script>

<style lang="scss" scoped>
.layout-welcome {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
}

.layout-header {
  height: 64px;
  padding: 0 48px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: white;
  border-bottom: 1px solid #e2e8f0;
  position: sticky;
  top: 0;
  z-index: 100;

  .layout-header-left {
    display: flex;
    align-items: center;
  }

  .layout-logo {
    display: flex;
    align-items: center;
    gap: 12px;
    cursor: pointer;

    .layout-logo-icon {
      width: 32px;
      height: 32px;
      background: linear-gradient(135deg, #2563eb 0%, #1e40af 100%);
      border-radius: 8px;
      color: white;
      display: flex;
      align-items: center;
      justify-content: center;
      font-weight: 800;
      font-size: 14px;
    }

    .layout-logo-text {
      font-size: 18px;
      font-weight: 700;
      color: #1e293b;
      letter-spacing: -0.5px;
    }
  }
}

.layout-main-full {
  flex: 1;
  width: 100%;
}
</style>
