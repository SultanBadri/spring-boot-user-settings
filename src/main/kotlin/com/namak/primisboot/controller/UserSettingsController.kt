package com.namak.primisboot.controller

import com.namak.primisboot.dto.UserSettingUpdateDto
import com.namak.primisboot.model.UserSetting
import com.namak.primisboot.service.UserSettingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
class UserSettingsController(private val service: UserSettingService) {
    @GetMapping
    fun getAllUserSettings(): List<UserSetting> = service.findAll()

    @GetMapping("/{id}")
    fun getUserSettingsById(
        @PathVariable id: Long,
    ): ResponseEntity<UserSetting> {
        val userSetting = service.findById(id)
        return if (userSetting != null) {
            ResponseEntity.ok(userSetting)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createUserSetting(
        @RequestBody userSetting: UserSetting,
    ): ResponseEntity<UserSetting> {
        val savedUserSetting = service.save(userSetting)
        return ResponseEntity(savedUserSetting, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
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
    fun patchUserSettings(
        @PathVariable id: Long,
        @RequestBody userSettings: UserSettingUpdateDto,
    ): ResponseEntity<UserSetting> {
        val existingUserSetting = service.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        if (userSettings.username.isNullOrBlank() && userSettings.email.isNullOrBlank() && userSettings.notificationsEnabled == null) {
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
    fun deleteUserSettings(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        return if (service.findById(id) != null) {
            service.deleteById(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
