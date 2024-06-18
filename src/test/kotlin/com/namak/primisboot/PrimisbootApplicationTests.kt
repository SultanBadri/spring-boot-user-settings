package com.namak.primisboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PrimisbootApplicationTests {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun testGetAllUserSettings() {
        val response: ResponseEntity<String> = restTemplate.getForEntity("http://localhost:$port/api/user-settings", String::class.java)
        assert(response.statusCode == HttpStatus.OK)
    }

    @Test
    fun testGetUserSettingById_NotFound() {
        val response: ResponseEntity<String> = restTemplate.getForEntity("http://localhost:$port/api/user-settings/999", String::class.java)
        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }
}
