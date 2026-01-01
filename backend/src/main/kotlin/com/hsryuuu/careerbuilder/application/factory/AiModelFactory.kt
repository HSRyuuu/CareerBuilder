package com.hsryuuu.careerbuilder.application.factory

import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.stereotype.Component

@Component
class AiModelFactory {

    /**
     * 유저의 플랜(현재는 하드코딩)에 따라 적절한 모델 옵션을 반환합니다.
     */
    fun getModelOption(modelName: String): OpenAiChatOptions {
        return OpenAiChatOptions.builder()
            .withModel(modelName)
            .withTemperature(0.7)
            .build()

    }
}