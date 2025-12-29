package com.hsryuuu.careerbuilder.domain.ai.handler

import com.hsryuuu.careerbuilder.domain.ai.event.ExperienceAnalysisEvent
import com.hsryuuu.careerbuilder.domain.ai.model.ExperienceAnalysisResponse
import com.hsryuuu.careerbuilder.domain.ai.model.type.AiRequestStatus
import com.hsryuuu.careerbuilder.domain.ai.repository.AiRequestRepository
import com.hsryuuu.careerbuilder.domain.ai.service.AiGenerationService
import com.hsryuuu.careerbuilder.domain.experience.repository.ExperienceRepository
import org.slf4j.LoggerFactory
import org.springframework.ai.converter.BeanOutputConverter
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ExperienceAnalysisEventListener(
    private val aiRequestRepository: AiRequestRepository,
    private val experienceRepository: ExperienceRepository,
    private val aiGenerationService: AiGenerationService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Async("aiExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleExperienceAnalysis(event: ExperienceAnalysisEvent) {
        val aiRequest = aiRequestRepository.findById(event.aiRequestId).orElse(null)
        if (aiRequest == null) {
            log.error("AiRequest not found id: {}", event.aiRequestId)
            return
        }

        try {
            // 1. 상태 변경: PROCESSING
            aiRequest.status = AiRequestStatus.PROCESSING
            aiRequestRepository.saveAndFlush(aiRequest)

            // 2. Experience 데이터 조회
            val experience = experienceRepository.findById(event.experienceId).orElseThrow {
                IllegalArgumentException("Experience not found id: ${event.experienceId}")
            }

            // 3. AI 호출 (Service 위임)
            val chatResponse = aiGenerationService.analyzeExperience(experience)
                    ?: throw IllegalStateException("AI Response is null")
            val usage = chatResponse.metadata.usage
            val model = chatResponse.metadata.model
            val providerId = chatResponse.metadata.id
            val result = chatResponse.result
            val output = result.output


            // 4. 결과 로깅 및 성공 처리
            log.info("AI Analysis Completed. RequestID: {}", aiRequest.id)
            log.info("Usage - Prompt: {}, Completion: {}, Total: {}", usage.promptTokens, usage.generationTokens, usage.totalTokens)
            log.info("Content: {}", output.content)
            aiRequest.complete(
                aiProviderId = providerId,
                modelName = model,
                promptTokens = usage.promptTokens.toInt(),
                completionTokens = usage.generationTokens.toInt(),
                totalTokens = usage.totalTokens.toInt(),
                rawResponse = output.content
            )

            val converter = BeanOutputConverter(ExperienceAnalysisResponse::class.java)
            val responseContent = converter.convert(output.content)


        } catch (e: Exception) {
            log.error("AI Analysis Failed for request: ${aiRequest.id}", e)
            aiRequest.fail(e.message ?: "Unknown error")
        } finally {
            aiRequestRepository.save(aiRequest)
        }
    }
}
