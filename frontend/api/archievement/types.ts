/**
 * Achievement(성과) API 타입 정의
 */
import type {
  AchievementSectionKind,
  AchievementStatus,
  WorkType,
  ContributionLevel,
} from '@/types/achievement-types';

/**
 * 성과 블록 생성 요청 타입
 */
export type TAchievementSectionCreate = {
  kind: AchievementSectionKind | string;
  title: string;
  content: string;
  sortOrder: number;
};

/**
 * 성과 블록 응답 타입
 */
export type TAchievementSection = {
  id: string;
  achievementId: string;
  kind: AchievementSectionKind | string;
  title: string;
  content: string;
  sortOrder: number;
  createdAt: string;
  updatedAt: string;
};

/**
 * 성과 생성 요청 타입
 */
export type TAchievementCreate = {
  title: string;
  orgName?: string;
  roleTitle?: string;
  durationStart: string;
  durationEnd?: string;
  workType?: WorkType | string;
  contributionLevel?: ContributionLevel | string;
  goalSummary?: string;
  impactSummary?: string;
  skills?: string;
  status?: AchievementStatus | string;
  sections: TAchievementSectionCreate[];
};

/**
 * 성과 수정 요청 타입
 */
export type TAchievementUpdate = TAchievementCreate;

/**
 * 성과 응답 타입
 */
export type TAchievement = {
  id: string;
  userId: string;
  title: string;
  orgName?: string;
  roleTitle?: string;
  durationStart: string;
  durationEnd?: string;
  workType?: WorkType | string;
  contributionLevel?: ContributionLevel | string;
  goalSummary?: string;
  impactSummary?: string;
  skills?: string;
  status: AchievementStatus | string;
  createdAt: string;
  updatedAt: string;
  sections: TAchievementSection[];
};

/**
 * 성과 목록 조회 파라미터 타입
 */
export type TAchievementListParams = {
  page?: number;
  size?: number;
  sort?: string;
  status?: AchievementStatus | string;
  keyword?: string;
};

/**
 * 페이지네이션 응답 타입
 */
export type TPageResponse<T> = {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
};
