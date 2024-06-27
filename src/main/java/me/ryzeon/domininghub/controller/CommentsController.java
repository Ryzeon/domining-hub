package me.ryzeon.domininghub.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.dto.comment.CommentDto;
import me.ryzeon.domininghub.dto.comment.CommentPageableResponse;
import me.ryzeon.domininghub.dto.comment.CommentRequest;
import me.ryzeon.domininghub.entity.Comment;
import me.ryzeon.domininghub.service.ICommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/11/24 @ 18:25
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/comments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Comments", description = "Comments Endpoints")
@AllArgsConstructor
public class CommentsController {

    private final ICommentService commentService;


    @ApiResponse(
            responseCode = "201",
            description = "Comment Created",
            content = @Content(schema = @Schema(implementation = CommentDto.class))
    )
    @PostMapping
    public ResponseEntity<CommentDto> createPost(@RequestBody CommentRequest request) {
        var comment = commentService.createComment(request).orElseThrow(() -> new RuntimeException("Error with creation of comment"));
        return new ResponseEntity<>(CommentDto.fromComment(comment), HttpStatus.CREATED);
    }

    @ApiResponse(
            responseCode = "200",
            description = "Comments found",
            content = @Content(schema = @Schema(implementation = CommentPageableResponse.class))
    )
    @GetMapping("{id}")
    public ResponseEntity<CommentPageableResponse> getComments(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<CommentDto> comments = commentService.findByPostId(id, paging).map(CommentDto::fromComment);
        return ResponseEntity.ok(
                new CommentPageableResponse(
                        comments.getContent(),
                        comments.getNumber(),
                        comments.getTotalPages(),
                        comments.getTotalElements()
                )
        );
    }

    @ApiResponse(
            responseCode = "200",
            description = "Comment deleted",
            content = @Content()
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
