<template>
  <div class="ai-hub-page">
    <PageHeader
      title="AI 커리어 허브"
      subtitle="AI 기능을 통해 당신의 커리어 전문성을 한 단계 더 높여보세요."
    />

    <div class="content-wrapper">
      <!-- AI 피처 카드 그리드 -->
      <div class="ai-features-grid">
        <div class="ai-feature-card theme-purple" @click="handleAiAnalysisRequest">
          <div class="card-icon">
            <v-icon color="white" size="32">mdi-brain</v-icon>
          </div>
          <div class="card-content">
            <h3 class="card-title">AI 경험 분석 요청</h3>
            <p class="card-description">
              등록된 경험을 심층 분석하여 핵심 역량을 추출하고 논리적인 STAR 구조로 재구성합니다.
            </p>
            <div class="card-footer">
              <span class="action-text">지금 분석하기</span>
              <v-icon size="small">mdi-arrow-right</v-icon>
            </div>
          </div>
        </div>

        <div class="ai-feature-card theme-blue" @click="handleFeedback">
          <div class="card-tag">Coming Soon</div>
          <div class="card-icon">
            <v-icon color="white" size="32">mdi-comment-check-outline</v-icon>
          </div>
          <div class="card-content">
            <h3 class="card-title">전체 커리어 피드백</h3>
            <p class="card-description">
              전체 경험 데이터를 종합적으로 분석하여 커리어 방향성과 개선점을 제안받으세요.
            </p>
            <div class="card-footer">
              <span class="action-text">준비 중</span>
              <v-icon size="small">mdi-lock-outline</v-icon>
            </div>
          </div>
        </div>

        <div class="ai-feature-card theme-emerald" @click="handleResumeCreate">
          <div class="card-tag">Coming Soon</div>
          <div class="card-icon">
            <v-icon color="white" size="32">mdi-file-document-edit-outline</v-icon>
          </div>
          <div class="card-content">
            <h3 class="card-title">AI 이력서 생성</h3>
            <p class="card-description">
              관리된 경험 데이터를 바탕으로 지원 직무에 최적화된 맞춤형 이력서를 자동 생성합니다.
            </p>
            <div class="card-footer">
              <span class="action-text">준비 중</span>
              <v-icon size="small">mdi-lock-outline</v-icon>
            </div>
          </div>
        </div>
      </div>

      <!-- AI 분석 프로세스 안내 -->
      <div class="ai-process-section u-ai-bg-soft">
        <h3 class="section-title u-ai-text-gradient">AI 분석 프로세스</h3>
        <div class="process-steps">
          <div class="step">
            <div class="step-num">01</div>
            <div class="step-info">
              <div class="step-title">경험 선택 및 요청</div>
              <div class="step-desc">분석을 원하는 프로젝트나 업무 경험을 선택합니다.</div>
            </div>
          </div>
          <div class="step-arrow">
            <v-icon>mdi-chevron-right</v-icon>
          </div>
          <div class="step">
            <div class="step-num">02</div>
            <div class="step-info">
              <div class="step-title">심층 분석 진행</div>
              <div class="step-desc">AI가 문맥을 분석하여 핵심 성과와 역량을 추출합니다.</div>
            </div>
          </div>
          <div class="step-arrow">
            <v-icon>mdi-chevron-right</v-icon>
          </div>
          <div class="step">
            <div class="step-num">03</div>
            <div class="step-info">
              <div class="step-title">결과 확인 및 보완</div>
              <div class="step-desc">분석된 내용을 확인하고 더 전문적으로 내용을 다듬습니다.</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import PageHeader from '@/components/organisms/PageHeader/PageHeader.vue';
import { MENU_URLS } from '~/constants/menus';

definePageMeta({
  layout: 'default',
});

const toast = useToast();

const handleAiAnalysisRequest = () => {
  navigateTo(MENU_URLS.AI_EXPERIENCE);
};

const handleFeedback = () => {
  toast.warning('전체 커리어 피드백 기능은 현재 준비 중입니다.');
};

const handleResumeCreate = () => {
  toast.warning('AI 이력서 생성 기능은 현재 준비 중입니다.');
};
</script>

<style lang="scss" scoped>
.ai-hub-page {
  padding: 26px;
  animation: fadeIn 0.5s ease;
}

.content-wrapper {
  margin-top: 24px;
  display: flex;
  flex-direction: column;
  gap: 32px;
}

.ai-features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 24px;
}

.ai-feature-card {
  position: relative;
  background: var(--bg-primary);
  border: 1px solid var(--border-color);
  border-radius: 20px;
  padding: 32px;
  cursor: pointer;
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 4px;
    background: var(--card-gradient);
    opacity: 0;
    transition: opacity 0.3s ease;
  }

  &:hover {
    transform: translateY(-8px);
    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
    border-color: transparent;

    &::before {
      opacity: 1;
    }

    .card-footer .v-icon {
      transform: translateX(4px);
    }
  }

  &.theme-purple {
    --card-gradient: linear-gradient(135deg, #8b5cf6 0%, #6366f1 100%);
    --accent-color: #8b5cf6;
  }

  &.theme-blue {
    --card-gradient: linear-gradient(135deg, #3b82f6 0%, #2563eb 100%);
    --accent-color: #3b82f6;
  }

  &.theme-emerald {
    --card-gradient: linear-gradient(135deg, #10b981 0%, #059669 100%);
    --accent-color: #10b981;
  }

  .card-tag {
    position: absolute;
    top: 16px;
    right: 16px;
    background: var(--bg-secondary);
    color: var(--text-tertiary);
    font-size: 10px;
    font-weight: 800;
    padding: 4px 10px;
    border-radius: 100px;
    border: 1px solid var(--border-color);
    text-transform: uppercase;
    letter-spacing: 0.5px;
  }

  .card-icon {
    width: 64px;
    height: 64px;
    border-radius: 18px;
    background: var(--card-gradient);
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 24px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  }

  .card-title {
    font-size: 20px;
    font-weight: 800;
    color: var(--text-primary);
    margin-bottom: 12px;
    letter-spacing: -0.5px;
  }

  .card-description {
    font-size: 14px;
    color: var(--text-secondary);
    line-height: 1.6;
    margin-bottom: 24px;
    word-break: keep-all;
  }

  .card-footer {
    display: flex;
    align-items: center;
    gap: 8px;
    color: var(--accent-color);
    font-size: 14px;
    font-weight: 700;

    .v-icon {
      transition: transform 0.3s ease;
    }
  }
}

.ai-process-section {
  padding: 48px;
  border-radius: 24px;
  text-align: center;

  .section-title {
    font-size: 24px;
    margin-bottom: 40px;
  }

  .process-steps {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 32px;

    @media (max-width: 960px) {
      flex-direction: column;
      gap: 24px;
    }
  }

  .step {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 16px;

    .step-num {
      width: 40px;
      height: 40px;
      background: white;
      border: 1px solid var(--border-color);
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 16px;
      font-weight: 900;
      color: var(--primary-color);
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
    }

    .step-info {
      .step-title {
        font-size: 16px;
        font-weight: 700;
        margin-bottom: 6px;
        color: var(--text-primary);
      }
      .step-desc {
        font-size: 13px;
        color: var(--text-secondary);
        line-height: 1.5;
        word-break: keep-all;
      }
    }
  }

  .step-arrow {
    color: var(--text-tertiary);
    @media (max-width: 960px) {
      transform: rotate(90deg);
    }
  }
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(10px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>