package com.namak.primisboot.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
@Tag(name = "Hello World", description = "Says hello")
class HelloController {
    @GetMapping
    @Operation(summary = "Get Hello World message", description = "Returns a simple Hello World message")
    fun sayHello(): String = "Hello World!"
}
