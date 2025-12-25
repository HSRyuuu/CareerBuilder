/**
 * 메뉴 관리 Composable
 */
import { computed } from 'vue';
import { useMenuStore } from '@/stores/menu';
import { Menu, MENU_ITEMS, getMenuByKey, getMenuKeyByPath, type TMenuKey } from '@/constants/menus';

export const useMenu = () => {
  const menuStore = useMenuStore();

  // 현재 선택된 메뉴 키
  const currentMenu = computed(() => menuStore.currentMenu);

  // 현재 선택된 메뉴 아이템
  const currentMenuItem = computed(() => getMenuByKey(menuStore.currentMenu));

  // 메뉴 선택
  const select = (menuKey: TMenuKey) => {
    menuStore.select(menuKey);
  };

  // path로 메뉴 선택
  const selectByPath = (path: string) => {
    const menuKey = getMenuKeyByPath(path);
    if (menuKey) {
      menuStore.select(menuKey);
    }
  };

  // 메뉴 활성화 확인
  const isActive = (menuKey: TMenuKey): boolean => {
    return menuStore.isActive(menuKey);
  };

  return {
    // State
    currentMenu,
    currentMenuItem,
    menuItems: MENU_ITEMS,

    // Actions
    select,
    selectByPath,
    isActive,

    // Constants (편의를 위해 re-export)
    Menu,
  };
};

