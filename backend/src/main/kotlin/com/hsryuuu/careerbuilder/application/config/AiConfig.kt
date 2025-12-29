package com.hsryuuu.careerbuilder.application.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig {

    @Bean
    fun chatClient(builder: ChatClient.Builder): ChatClient {
        val defaultRule =
            """
                당신은 전문적인 커리어 코치이자 이력서 컨설턴트입니다. 
                사용자의 직무 경험을 효과적으로 개선하도록 돕되, 다음 지침을 엄격히 준수하세요.
                1. **사실 근거**: 사용자가 제공한 기술 스택과 성과 데이터 내에서만 작성하세요. 
                    제공되지 않은 아키텍처나 기술을 임의로 추론하여 추가하지 마세요.
                2. **논리 구조**: 상황에 따라 STAR, PAR, 단순 3-5줄 요약 중 최적의 방식을 선택하세요.
                3. **어조**: 전문적이고 결과 중심적으로 작성하되, 사실에 기반해야 합니다.
                4. **언어**: 모든 답변은 한국어로 작성하세요.
                - STAR: Situation(상황), Task(목표), Action(행동), Result(결과)
                - PAR: Problem/Challenge(문제 또는 도전과제), Action(행동), Result(결과)
            """;
        return builder
            .defaultSystem(defaultRule)
            .build()
    }
}
