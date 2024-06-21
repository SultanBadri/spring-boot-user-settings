package com.namak.primisboot.dto

data class UserSettingUpdateDto(
    val username: String? = null,
    val email: String? = null,
    val notificationsEnabled: Boolean? = null,
)
