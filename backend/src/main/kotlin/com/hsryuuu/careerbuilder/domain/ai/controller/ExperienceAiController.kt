package com.hsryuuu.careerbuilder.domain.ai.controller

import com.hsryuuu.careerbuilder.application.security.AuthManager
import com.hsryuuu.careerbuilder.domain.experience.service.ExperienceAiService
import com.hsryuuu.careerbuilder.domain.ai.model.ExperienceAnalysisResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "Experience AI", description = "경험 AI 분석 API")
@RestController
@RequestMapping("/api/v1/ai/experiences")
class ExperienceAiController(
    private val experienceAiService: ExperienceAiService,

    private val authManager: AuthManager
) {
    @Operation(summary = "경험 AI 분석 요청", description = "AI 경험 분석을 요청합니다.")
    @PostMapping("/{experienceId}/analyze")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun requestAnalyzeExperience(@PathVariable experienceId: UUID){
        val userId = authManager.getCurrentUserIdOrElseThrow()
        experienceAiService.requestAnalysis(userId, experienceId)
    }
}