package me.ryzeon.domininghub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.shared.storage.service.IFileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/11/24 @ 18:26
 */
@Controller
@RequestMapping(value = "/api/v1/files", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Files", description = "Files Controller")
@AllArgsConstructor
public class FilesController {

    private final IFileService fileService;
}
