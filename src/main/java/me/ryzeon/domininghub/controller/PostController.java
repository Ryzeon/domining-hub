package me.ryzeon.domininghub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/11/24 @ 18:26
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/post", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Post", description = "Post Endpoints")
@AllArgsConstructor
public class PostController {
}
