package com.hsryuuu.careerbuilder.application.security

import com.hsryuuu.careerbuilder.domain.user.appuser.model.entity.AppUser
import java.util.*

data class UserInfo(
    val id: UUID?,
    val username: String,
    val email: String,
    val role: UserRole
) {
    companion object {
        fun from(appUser: AppUser): UserInfo {
            return UserInfo(
                id = appUser.id,
                email = appUser.email,
                username = appUser.username,
                role = UserRole.ROLE_USER
            )
        }
    }
}
