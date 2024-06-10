package com.namak.primisboot.service

import com.namak.primisboot.model.UserSetting
import com.namak.primisboot.repository.UserSettingsRepository
import org.springframework.stereotype.Service

@Service
class UserSettingService(private val repository: UserSettingsRepository) {
    fun findAll(): List<UserSetting> = repository.findAll()

    fun findById(id: Long): UserSetting? = repository.findById(id).orElse(null)

    fun save(userSetting: UserSetting): UserSetting = repository.save(userSetting)

    fun deleteById(id: Long) = repository.deleteById(id)
}
