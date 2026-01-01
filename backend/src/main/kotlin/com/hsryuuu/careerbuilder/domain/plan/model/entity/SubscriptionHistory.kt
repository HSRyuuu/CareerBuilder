package com.hsryuuu.careerbuilder.domain.plan.model.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "subscription_history")
class SubscriptionHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    val subscription: Subscription,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prev_plan_id")
    val prevPlan: Plan? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next_plan_id", nullable = false)
    val nextPlan: Plan,

    @Column(name = "change_reason", length = 100)
    val changeReason: String? = null,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun create(
        subscription: Subscription,
        prevPlan: Plan?,
        nextPlan: Plan,
        changeReason: String?
    ): SubscriptionHistory {
        return SubscriptionHistory(
            subscription = subscription,
            prevPlan = prevPlan,
            nextPlan = nextPlan,
            changeReason = changeReason,
        )
    }
}

