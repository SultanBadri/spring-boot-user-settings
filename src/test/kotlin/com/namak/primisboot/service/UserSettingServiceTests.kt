package com.namak.primisboot.service

import com.namak.primisboot.model.UserSetting
import com.namak.primisboot.repository.UserSettingsRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.Optional

class UserSettingServiceTests {
    private lateinit var service: UserSettingService
    private val repository: UserSettingsRepository = mock()

    @BeforeEach
    fun setup() {
        service = UserSettingService(repository)
    }

    @Test
    fun testFindById() {
        val userSetting = UserSetting(id = 1, username = "testUser", email = "test@example.com", notificationsEnabled = true)
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(userSetting))

        val result = service.findById(1)
        assertEquals(userSetting, result)
    }

    @Test
    fun testSaveUserSetting() {
        val userSetting = UserSetting(id = 1, username = "testUser", email = "test@example.com", notificationsEnabled = true)
        Mockito.`when`(repository.save(userSetting)).thenReturn(userSetting)

        val result = service.save(userSetting)
        assertEquals(userSetting, result)
    }
}
