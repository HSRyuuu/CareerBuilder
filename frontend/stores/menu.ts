/**
 * 메뉴 상태 관리 Store
 */
import { defineStore } from 'pinia';
import { ref } from 'vue';
import { Menu, type TMenuKey } from '@/constants/menus';

export const useMenuStore = defineStore('menu', () => {
  // 현재 선택된 메뉴 키
  const currentMenu = ref<TMenuKey>(Menu.Home);

  // 메뉴 선택
  const select = (menuKey: TMenuKey) => {
    currentMenu.value = menuKey;
  };

  // 현재 메뉴 확인
  const isActive = (menuKey: TMenuKey): boolean => {
    return currentMenu.value === menuKey;
  };

  return {
    currentMenu,
    select,
    isActive,
  };
});

