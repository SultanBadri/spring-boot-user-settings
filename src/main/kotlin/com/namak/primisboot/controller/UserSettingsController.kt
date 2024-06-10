package com.namak.primisboot.controller

import com.namak.primisboot.model.UserSetting
import com.namak.primisboot.service.UserSettingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    ): UserSetting = service.save(userSetting)

    @PutMapping("/{id}")
    fun updateUserSettings(
        @PathVariable id: Long,
        @RequestBody userSettings: UserSetting,
    ): ResponseEntity<UserSetting> {
        return if (service.findById(id) != null) {
            ResponseEntity.ok(service.save(userSettings))
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
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
