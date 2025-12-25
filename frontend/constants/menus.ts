/**
 * 메뉴 상수 정의
 */

// 메뉴 키 상수
export const Menu = {
  Home: 'HOME',
  Career: 'CAREER',
} as const;

export type TMenuKey = (typeof Menu)[keyof typeof Menu];

// 메뉴 아이템 타입
export type TMenuItem = {
  key: TMenuKey;
  path: string;
  label: string;
  icon: string;
};

// 메뉴 목록
export const MENU_ITEMS: TMenuItem[] = [
  { key: Menu.Home, path: '/home', label: '홈', icon: 'mdi-home' },
  { key: Menu.Career, path: '/career', label: '경험', icon: 'mdi-file-document' },
];

// 메뉴 키로 메뉴 아이템 찾기
export const getMenuByKey = (key: TMenuKey): TMenuItem | undefined => {
  return MENU_ITEMS.find((item) => item.key === key);
};

// path로 메뉴 키 찾기
export const getMenuKeyByPath = (path: string): TMenuKey | undefined => {
  const menu = MENU_ITEMS.find((item) => path.startsWith(item.path));
  return menu?.key;
};
