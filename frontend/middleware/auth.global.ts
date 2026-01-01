import { useAuthStore } from '@/stores/auth';
import { MENU_URLS } from '~/constants/menus';

export default defineNuxtRouteMiddleware((to) => {
  const authStore = useAuthStore();

  // 1. 비로그인 상태일 때
  if (!authStore.isAuthenticated) {
    console.log(to.path)
    // /welcome 으로 시작하지 않는 경로로 접근 시 /welcome으로 리다이렉트
    if (!to.path.startsWith(MENU_URLS.WELCOME)) {
        if(to.path !== '/' ){
            useToast().warning('로그인 후 이용해주세요.');
        }
        return navigateTo(MENU_URLS.WELCOME);
    }
  }

  // 2. 로그인 상태일 때
  if (authStore.isAuthenticated) {
    // 로그인한 사용자가 /welcome 경로에 접근 시 /home으로 리다이렉트
    if (to.path.startsWith(MENU_URLS.WELCOME) || to.path === '/') {
      return navigateTo(MENU_URLS.HOME);
    }
  }
});
