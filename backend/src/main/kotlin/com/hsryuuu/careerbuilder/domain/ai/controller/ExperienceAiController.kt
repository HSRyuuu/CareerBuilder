package com.hsryuuu.careerbuilder.domain.ai.controller

import com.hsryuuu.careerbuilder.application.security.AuthManager
import com.hsryuuu.careerbuilder.domain.ai.service.ExperienceAiService
import com.hsryuuu.careerbuilder.domain.ai.model.ExperienceAnalysisResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "Experience AI", description = "경험 AI 분석 API")
@RestController
@RequestMapping("/api/v1/ai/experiences")
class ExperienceAiController(
    private val experienceAiService: ExperienceAiService,
    private val authManager: AuthManager
) {

    @Operation(summary = "경험 분석 및 개선 제안", description = "AI를 사용하여 경험을 분석하고 개선된 문구와 키워드를 제안합니다.")
    @PostMapping("/{experienceId}/analyze")
    fun analyzeExperience(
        @PathVariable experienceId: UUID
    ): ResponseEntity<ExperienceAnalysisResponse> {
        val userId = authManager.getCurrentUserIdOrElseThrow()

        val response = experienceAiService.analyzeExperience(experienceId, userId)
        return ResponseEntity.ok(response)
    }
}