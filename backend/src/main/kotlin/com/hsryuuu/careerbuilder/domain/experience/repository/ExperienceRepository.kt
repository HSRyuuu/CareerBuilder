package com.hsryuuu.careerbuilder.domain.experience.repository

import com.hsryuuu.careerbuilder.domain.experience.model.entity.Experience
import com.hsryuuu.careerbuilder.domain.experience.model.entity.ExperienceStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ExperienceRepository : JpaRepository<Experience, UUID>, CustomExperienceRepository {
    fun findByUserId(userId: UUID): List<Experience>
    fun findByUserIdAndStatus(userId: UUID, status: ExperienceStatus): List<Experience>
    fun existsByIdAndUserId(id: UUID, userId: UUID): Boolean
}