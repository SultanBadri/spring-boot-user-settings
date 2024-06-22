package com.namak.primisboot.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "users")
data class UserSetting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @field:NotBlank(message = "Username is mandatory")
    val username: String,
    @field:Email(message = "Invalid email format")
    val email: String,
    val notificationsEnabled: Boolean? = false,
)
