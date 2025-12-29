package com.hsryuuu.careerbuilder.domain.ai.prompt

object ExperiencePrompts {
    val EXPERIENCE_ANALYSIS_PROMPT = """
            다음의 직무 경험을 분석하여 전문적인 개선안을 제공해 주세요. 
            작성 시 3가지 방식(STAR, PAR, 3줄 요약) 중 해당 경험의 성격에 가장 적합한 논리 구조를 선택해 주세요.
            - STAR: Situation(상황), Task(목표), Action(행동), Result(결과)
            - PAR: Problem/Challenge(문제 또는 도전과제), Action(행동), Result(결과)
            
            경험 제목: {title}
            핵심 성과: {impactSummary}
            목표: {goalSummary}
            
            추가 섹션: 
                {additionalSections}
      
            [작성 지침]
            1. 전체 분석: 이 경험의 전체 내용을 인사 담당자가 매력적으로 느낄 수 있도록 한 문단으로 요약하고, 전반적인 개선 방향을 피드백해 주세요.
            2. 핵심 요약(목표) 개선: 사용자가 작성한 '목표'가 직무 역량을 잘 드러내는지 분석하고, 더 전문적인 용어를 사용하여 수정 예시를 제안해 주세요.
            3. 핵심 성과 개선: '성과' 부분이 수치화되었는지, 결과 중심적인지 확인하여 피드백하고 개선된 문장을 제안해 주세요.
            4. 세부 섹션 개선: 
                - 각 섹션별로 구체적인 피드백과 함께 재작성된 내용을 제공하세요.
                - STAR나 PAR 선택 시, 각 단계별(S,T,A,R 등) 내용을 'breakdown'에 상세히 채워주세요.
            5. 모든 답변은 한국어로 작성하며, 전문적이고 신뢰감 있는 비즈니스 어조를 유지하세요.
            
            {format}
        """.trimIndent()
}