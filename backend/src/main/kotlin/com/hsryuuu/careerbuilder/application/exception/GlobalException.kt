package com.hsryuuu.careerbuilder.application.exception

class GlobalException(
    val errorCode: ErrorCode,
    override val cause: Throwable? = null,
    val data: Any? = null
) : RuntimeException(errorCode.message, cause)
