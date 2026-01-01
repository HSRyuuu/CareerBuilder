package com.hsryuuu.careerbuilder.application.config

import com.hsryuuu.careerbuilder.domain.ai.prompt.AiRolePrompts
import org.springframework.ai.chat.client.ChatClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig {

    @Bean
    fun chatClient(builder: ChatClient.Builder): ChatClient {
        val defaultRule = AiRolePrompts.CAREER_COACH_ROLE
        return builder
            .defaultSystem(defaultRule)
            .build()
    }
}
