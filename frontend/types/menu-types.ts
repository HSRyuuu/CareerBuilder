/**
 * 메뉴 관련 타입 정의
 */

export interface MenuItem {
  path: string;
  label: string;
  icon: string;
  children?: MenuItem[];
}

export interface BreadcrumbItem {
  label: string;
  path?: string;
}

