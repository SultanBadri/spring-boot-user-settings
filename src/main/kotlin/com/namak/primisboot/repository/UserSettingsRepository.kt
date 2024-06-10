package com.namak.primisboot.repository

import com.namak.primisboot.model.UserSetting
import org.springframework.data.jpa.repository.JpaRepository

interface UserSettingsRepository : JpaRepository<UserSetting, Long>
