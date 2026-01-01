package com.hsryuuu.careerbuilder.domain.plan.model.entity

import com.hsryuuu.careerbuilder.domain.user.appuser.model.entity.AppUser
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "subscriptions")
@EntityListeners(AuditingEntityListener::class)
class Subscription(
    @Id
    @UuidGenerator
    val id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    val user: AppUser,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    var plan: Plan,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var status: SubscriptionStatus = SubscriptionStatus.ACTIVE,

    @Column(name = "started_at", nullable = false)
    var startedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "expired_at")
    var expiredAt: LocalDateTime? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    /**
     * 플랜 변경 시 상태 업데이트 로직 (DDD)
     */
    fun upgrade(nextPlan: Plan) {
        this.plan = nextPlan
        // 필요 시 만료일 갱신 로직 추가
    }
}

enum class SubscriptionStatus { ACTIVE, EXPIRED, CANCELED, PENDING }