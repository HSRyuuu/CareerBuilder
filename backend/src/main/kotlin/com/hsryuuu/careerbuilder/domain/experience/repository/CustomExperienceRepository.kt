package com.hsryuuu.careerbuilder.domain.experience.repository

import com.hsryuuu.careerbuilder.common.dto.type.SortDirection
import com.hsryuuu.careerbuilder.domain.experience.model.dto.ExperienceStatsSummary
import com.hsryuuu.careerbuilder.domain.experience.model.dto.ExperienceWithAnalysisResponse
import com.hsryuuu.careerbuilder.domain.experience.model.entity.Experience
import com.hsryuuu.careerbuilder.domain.experience.model.entity.ExperienceStatus
import com.hsryuuu.careerbuilder.domain.experience.model.type.ExperienceSortKey
import com.hsryuuu.careerbuilder.domain.user.appuser.model.entity.AppUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CustomExperienceRepository {
    /**
     * 경험 검색 (QueryDSL 기반)
     *
     * @param searchKeyword 검색어 (title 또는 background)
     * @param pageable 페이징 및 정렬 정보
     * @return Page<Experience>
     */
    fun searchExperience(
        userId: UUID,
        searchKeyword: String?,
        status: ExperienceStatus?,
        sortKey: ExperienceSortKey,
        sortDirection: SortDirection?,
        pageable: Pageable
    ): Page<Experience>

    /**
     * 경험 통계 요약 조회 (QueryDSL 기반)
     */
    fun getStatsSummary(user: AppUser): ExperienceStatsSummary

    /**
     * 경험 및 AI 분석 결과 조회
     */
    fun getExperienceWithAnalysis(experienceId: UUID, userId: UUID): ExperienceWithAnalysisResponse?
}