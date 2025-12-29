package com.hsryuuu.careerbuilder.domain.ai.service

import com.hsryuuu.careerbuilder.application.exception.ErrorCode
import com.hsryuuu.careerbuilder.application.exception.GlobalException
import com.hsryuuu.careerbuilder.domain.ai.model.ExperienceAnalysisResponse
import com.hsryuuu.careerbuilder.domain.ai.prompt.ExperiencePrompts
import com.hsryuuu.careerbuilder.domain.experience.repository.ExperienceRepository
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ExperienceAiService(
    private val experienceRepository: ExperienceRepository,
    private val chatClient: ChatClient
) {

    @Transactional(readOnly = true)
    fun analyzeExperience(experienceId: UUID, userId: UUID): ExperienceAnalysisResponse {
        val experience = experienceRepository.findByIdOrNull(experienceId)
            ?: throw GlobalException(ErrorCode.EXPERIENCE_NOT_FOUND)

        if (experience.user.id != userId) {
            throw GlobalException(ErrorCode.FORBIDDEN)
        }
        // AI 응답 타입 정의
        val converter = BeanOutputConverter(ExperienceAnalysisResponse::class.java)

        // 프롬프트
        val template = PromptTemplate(ExperiencePrompts.EXPERIENCE_ANALYSIS_PROMPT)
        val variables = mapOf(
            "title" to experience.title,
            "impactSummary" to experience.impactSummary,
            "goalSummary" to experience.goalSummary,
            "additionalSections" to experience.sections.joinToString("\n") { "- [${it.title} / id:(${it.id})] ${it.content}" },
            "format" to converter.format
        )
        val prompt = template.render(variables)

        // AI 분석 요청
        val response = chatClient.prompt()
            .user { userSpec -> userSpec.text(prompt) }
            .call()
            .content()

        if (response.isNullOrBlank()) {
             throw RuntimeException("AI Response is empty")
        }

        // 응답값 변환
        return converter.convert(response)!!
    }
}