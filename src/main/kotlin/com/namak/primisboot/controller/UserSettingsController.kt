package com.namak.primisboot.controller

import com.namak.primisboot.dto.UserSettingUpdateDto
import com.namak.primisboot.model.UserSetting
import com.namak.primisboot.service.UserSettingService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user-settings")
@Validated
@Tag(name = "User Settings", description = "API for managing user settings")
class UserSettingsController(private val service: UserSettingService) {
    @GetMapping
    @Operation(summary = "Get all user settings", description = "Retrieve all user settings from the database")
    fun getAllUserSettings(): List<UserSetting> = service.findAll()

    @GetMapping("/{id}")
    @Operation(summary = "Get user settings by ID", description = "Retrieve a specific user setting by ID")
    fun getUserSettingsById(
        @PathVariable id: Long,
    ): ResponseEntity<UserSetting> {
        val userSetting = service.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(userSetting)
    }

    @PostMapping
    @Operation(summary = "Create new user setting", description = "Create a new user setting")
    fun createUserSetting(
        @RequestBody @Valid userSetting: UserSetting,
    ): ResponseEntity<UserSetting> {
        val savedUserSetting = service.save(userSetting)
        return ResponseEntity(savedUserSetting, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user settings", description = "Update an existing user setting by ID")
    fun updateUserSettings(
        @PathVariable id: Long,
        @RequestBody userSettings: UserSettingUpdateDto,
    ): ResponseEntity<UserSetting> {
        val existingUserSetting = service.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        if (userSettings.username.isNullOrBlank() || userSettings.email.isNullOrBlank() || userSettings.notificationsEnabled == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val updatedUserSetting =
            existingUserSetting.copy(
                username = userSettings.username,
                email = userSettings.email,
                notificationsEnabled = userSettings.notificationsEnabled,
            )

        return ResponseEntity.ok(service.save(updatedUserSetting))
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update user settings", description = "Partially update an existing user setting by ID")
    fun patchUserSettings(
        @PathVariable id: Long,
        @RequestBody userSettings: UserSettingUpdateDto,
    ): ResponseEntity<UserSetting> {
        val existingUserSetting = service.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        if (userSettings.username.isNullOrBlank() && userSettings.email.isNullOrBlank() && userSettings.notificationsEnabled == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        if (userSettings.username?.isBlank() == true || userSettings.email?.isBlank() == true) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val updatedUserSetting =
            existingUserSetting.copy(
                username = userSettings.username ?: existingUserSetting.username,
                email = userSettings.email ?: existingUserSetting.email,
                notificationsEnabled = userSettings.notificationsEnabled ?: existingUserSetting.notificationsEnabled,
            )

        return ResponseEntity.ok(service.save(updatedUserSetting))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user settings", description = "Delete an existing user setting by ID")
    fun deleteUserSettings(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        service.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        service.deleteById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
