package com.hsryuuu.careerbuilder.domain.ai.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.UUID

data class ExperienceAnalysisResponse(
    // 1. 경험 전체 요약 및 피드백
    val overallSummary: String,
    val overallFeedback: String,

    // 2. 핵심 요약(목표) 피드백 및 수정
    val goalImprovement: ImprovementDetail,

    // 3. 핵심 성과 피드백 및 수정
    val impactImprovement: ImprovementDetail,

    // 4. 각 섹션별 피드백 및 수정 (STAR/PAR 등 적용)
    val sectionImprovements: List<SectionImprovement>,

    // 추가: 추천 키워드
    val recommendedKeywords: List<String>
)

data class ImprovementDetail(
    val feedback: String,
    val improvedContent: String
)

data class SectionImprovement(
    val sectionId: UUID,
    val sectionTitle: String,
    val feedback: String,
    val improvedContent: String,
    val reasoning: String,
    val method: String, // "STAR", "PAR", "SHORT"
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val breakdown: MethodBreakdown?
)

data class MethodBreakdown(
    val situation: String? = null,
    val task: String? = null,
    val problem: String? = null,
    val action: String? = null,
    val result: String? = null
)