/**
 * AI API 함수
 */
import { useApi, HttpMethod } from '@/composables/useApi';
import type { TAiAnalysisRequest } from './type';

/**
 * 경험 AI 분석 요청
 */
export const requestAiAnalysis = (experienceId: string, body: TAiAnalysisRequest) => {
  return useApi<null>({
    url: `/api/v1/ai/experiences/${experienceId}/analyze`,
    method: HttpMethod.POST,
    body,
  });
};
