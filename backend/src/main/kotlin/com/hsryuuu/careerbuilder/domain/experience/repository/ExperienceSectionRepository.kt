package com.hsryuuu.careerbuilder.domain.experience.repository

import com.hsryuuu.careerbuilder.domain.experience.model.entity.ExperienceSection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ExperienceSectionRepository : JpaRepository<ExperienceSection, UUID> {
    fun findByExperienceIdOrderBySortOrderAsc(experienceId: UUID): List<ExperienceSection>
    fun deleteByExperienceId(experienceId: UUID)
}