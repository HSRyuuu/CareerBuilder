<template>
  <div class="auth-page signup-page">
    <div class="auth-card-wrapper">
      <Card class="auth-card">
        <div class="auth-header">
          <div class="auth-logo" @click="navigateTo('/welcome')">
            <div class="auth-logo-icon">CB</div>
            <span class="auth-logo-text">CareerBuilder</span>
          </div>
          <h1 class="auth-title">시작하기</h1>
          <p class="auth-subtitle">CareerBuilder와 함께 당신의 커리어를 기록하세요.</p>
        </div>

        <form class="auth-form" @submit.prevent="handleSignup">
          <div class="form-field">
            <label class="field-label">사용자 아이디 *</label>
            <div class="field-with-action">
              <Input
                v-model="signupForm.username"
                placeholder="아이디를 입력하세요"
                :size="CommonSize.Medium"
                required
                :disabled="isUsernameChecked"
              />
              <Button
                type="button"
                :variant="ButtonVariant.Secondary"
                :size="CommonSize.Small"
                @click="handleCheckUsername"
                v-if="!isUsernameChecked"
                :loading="isCheckingUsername"
              >
                중복확인
              </Button>
              <div v-else class="checked-group">
                <v-icon color="success">mdi-check-circle</v-icon>
                <button type="button" class="btn-text-change" @click="handleResetUsername">변경</button>
              </div>
            </div>
          </div>

          <div class="form-field">
            <label class="field-label">이메일 *</label>
            <div class="field-with-action">
              <Input
                v-model="signupForm.email"
                type="email"
                placeholder="example@email.com"
                :size="CommonSize.Medium"
                required
                :disabled="isEmailChecked"
              />
              <Button
                type="button"
                :variant="ButtonVariant.Secondary"
                :size="CommonSize.Small"
                @click="handleCheckEmail"
                v-if="!isEmailChecked"
                :loading="isCheckingEmail"
              >
                중복확인
              </Button>
              <div v-else class="checked-group">
                <v-icon color="success">mdi-check-circle</v-icon>
                <button type="button" class="btn-text-change" @click="handleResetEmail">변경</button>
              </div>
            </div>
          </div>

          <div class="form-field">
            <label class="field-label">비밀번호 *</label>
            <div class="field-with-action">
              <Input
                v-model="signupForm.password"
                :type="showPassword ? 'text' : 'password'"
                placeholder="8자 이상의 비밀번호"
                :size="CommonSize.Medium"
                :error="signupForm.password.length > 0 && signupForm.password.length < 8"
                required
              />
              <v-icon
                class="password-toggle"
                @click="showPassword = !showPassword"
              >
                {{ showPassword ? 'mdi-eye-off' : 'mdi-eye' }}
              </v-icon>
            </div>
            <p v-if="signupForm.password.length > 0 && signupForm.password.length < 8" class="error-text">
              비밀번호는 최소 8자 이상이어야 합니다.
            </p>
          </div>

          <div class="form-field">
            <label class="field-label">비밀번호 확인 *</label>
            <div class="field-with-action">
              <Input
                v-model="passwordConfirm"
                :type="showPasswordConfirm ? 'text' : 'password'"
                placeholder="비밀번호를 다시 입력하세요"
                :size="CommonSize.Medium"
                :error="passwordConfirm.length > 0 && !isPasswordMatch"
                required
              />
              <v-icon
                class="password-toggle"
                @click="showPasswordConfirm = !showPasswordConfirm"
              >
                {{ showPasswordConfirm ? 'mdi-eye-off' : 'mdi-eye' }}
              </v-icon>
            </div>
            <p v-if="passwordConfirm.length > 0 && !isPasswordMatch" class="error-text">
              비밀번호가 일치하지 않습니다.
            </p>
          </div>

          <Button
            type="submit"
            :variant="ButtonVariant.Primary"
            :size="CommonSize.Large"
            :round="true"
            class="auth-submit-btn"
            :loading="isLoading"
            :disabled="!isValid"
          >
            회원가입
          </Button>
        </form>

        <div class="auth-divider">
          <span>또는</span>
        </div>

        <div class="social-auth-grid">
          <button class="social-btn kakao" @click="handleSocialLogin('kakao')">
            <v-icon color="#3C1E1E">mdi-comment</v-icon>
            <span>카카오로 시작하기</span>
          </button>
          <button class="social-btn google" @click="handleSocialLogin('google')">
            <v-icon color="#EA4335">mdi-google</v-icon>
            <span>구글로 시작하기</span>
          </button>
        </div>

        <div class="auth-footer">
          이미 계정이 있으신가요?
          <NuxtLink to="/welcome/login">로그인</NuxtLink>
        </div>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
// 1. 외부 라이브러리 import
import { ref, reactive, computed } from 'vue';

// 2. 프로젝트 내부 import
import { ButtonVariant, CommonSize } from '@/constants/enums/style-enum';

// 3. API/Composables import
import { signup, checkUsername, checkEmail } from '@/api/auth/api';

// 4. Type import
import type { TUserSignUpRequest } from '@/api/auth/types';

// 5. 로컬 컴포넌트 import
import Card from '@/components/molecules/Card/Card.vue';
import Button from '@/components/atoms/Button/Button.vue';
import Input from '@/components/atoms/Input/Input.vue';

// 9. Ref/Reactive 선언
definePageMeta({
  layout: 'full-page',
});

const authStore = useAuthStore();
const toast = useToast();
const isLoading = ref(false);

const isCheckingUsername = ref(false);
const isUsernameChecked = ref(false);

const isCheckingEmail = ref(false);
const isEmailChecked = ref(false);

const showPassword = ref(false);
const showPasswordConfirm = ref(false);

const signupForm = reactive<TUserSignUpRequest>({
  username: '',
  email: '',
  password: '',
});

const passwordConfirm = ref('');

// 10. Computed 선언
const isPasswordMatch = computed(() => {
  return signupForm.password === passwordConfirm.value;
});

const isValid = computed(() => {
  return (
    signupForm.username &&
    signupForm.email &&
    signupForm.password.length >= 8 &&
    isPasswordMatch.value &&
    isUsernameChecked.value &&
    isEmailChecked.value
  );
});

// 11. 함수 선언
const handleCheckUsername = async () => {
  if (!signupForm.username) {
    toast.error('아이디를 입력해주세요.');
    return;
  }
  
  if (signupForm.username.length < 4) {
    toast.error('아이디는 4자 이상이어야 합니다.');
    return;
  }
  
  isCheckingUsername.value = true;
  try {
    const { data, error } = await checkUsername(signupForm.username);
    if (error) {
      toast.error('중복 확인 중 오류가 발생했습니다.');
      return;
    }
    
    if (data?.exists) {
      toast.error('이미 사용 중인 아이디입니다.');
    } else {
      toast.success('사용 가능한 아이디입니다.');
      isUsernameChecked.value = true;
    }
  } finally {
    isCheckingUsername.value = false;
  }
};

const handleResetUsername = () => {
  isUsernameChecked.value = false;
};

const handleCheckEmail = async () => {
  if (!signupForm.email) {
    toast.error('이메일을 입력해주세요.');
    return;
  }
  
  const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
  if (!emailRegex.test(signupForm.email)) {
    toast.error('올바른 이메일 형식이 아닙니다.');
    return;
  }
  
  isCheckingEmail.value = true;
  try {
    const { data, error } = await checkEmail(signupForm.email);
    if (error) {
      toast.error('중복 확인 중 오류가 발생했습니다.');
      return;
    }
    
    if (data?.exists) {
      toast.error('이미 사용 중인 이메일입니다.');
    } else {
      toast.success('사용 가능한 이메일입니다.');
      isEmailChecked.value = true;
    }
  } finally {
    isCheckingEmail.value = false;
  }
};

const handleResetEmail = () => {
  isEmailChecked.value = false;
};

const handleSignup = async () => {
  if (!isValid.value || isLoading.value) return;
  
  isLoading.value = true;
  try {
    const { error } = await signup(signupForm);
    
    if (error) {
      toast.error('회원가입에 실패했습니다. 다시 시도해주세요.');
      return;
    }
    
    toast.success('회원가입이 완료되었습니다! 로그인해주세요.');
    navigateTo('/welcome/login');
  } catch (e) {
    console.error('Signup Error:', e);
    toast.error('오류가 발생했습니다.');
  } finally {
    isLoading.value = false;
  }
};

const handleSocialLogin = (platform: string) => {
  toast.info(`${platform} 회원가입은 준비 중입니다.`);
};
</script>

<style lang="scss" scoped>
@use '@/styles/pages/auth-page.scss';
</style>
