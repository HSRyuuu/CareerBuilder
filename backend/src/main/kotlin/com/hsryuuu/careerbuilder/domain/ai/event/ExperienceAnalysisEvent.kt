package com.hsryuuu.careerbuilder.domain.ai.event

import java.util.UUID

data class ExperienceAnalysisEvent(
    val aiRequestId: UUID,
    val experienceId: UUID,
    val userId: UUID
)
