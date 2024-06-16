package me.ryzeon.domininghub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/ping", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = " Ping", description = "Ping Controller")

public class PingController {

    @GetMapping
    public String ping() {
        return "Pong!";
    }
}
