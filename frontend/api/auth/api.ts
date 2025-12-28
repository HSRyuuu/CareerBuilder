/**
 * Auth 관련 API 함수 정의
 */
import { useApi, HttpMethod } from '@/composables/useApi';
import type {
  TLoginRequest,
  TLoginResponse,
  TUserSignUpRequest,
  TSignupResponse,
  TDuplicateCheckResponse,
  TLogoutResponse,
} from './types';

/**
 * 로그인
 */
export const login = (body: TLoginRequest) => {
  return useApi<TLoginResponse>({
    url: '/api/auth/login',
    method: HttpMethod.POST,
    body,
  });
};

/**
 * 회원가입
 */
export const signup = (body: TUserSignUpRequest) => {
  return useApi<TSignupResponse>({
    url: '/api/auth/signup',
    method: HttpMethod.POST,
    body,
  });
};

/**
 * 사용자명 중복 체크
 */
export const checkUsername = (username: string) => {
  return useApi<TDuplicateCheckResponse>({
    url: '/api/auth/check/username',
    method: HttpMethod.GET,
    params: { username },
  });
};

/**
 * 이메일 중복 체크
 */
export const checkEmail = (email: string) => {
  return useApi<TDuplicateCheckResponse>({
    url: '/api/auth/check/email',
    method: HttpMethod.GET,
    params: { email },
  });
};

/**
 * 로그아웃
 */
export const logout = () => {
  return useApi<TLogoutResponse>({
    url: '/api/auth/logout',
    method: HttpMethod.GET,
  });
};
