package me.ryzeon.domininghub.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.dto.post.CreatePostRequest;
import me.ryzeon.domininghub.dto.post.PostDto;
import me.ryzeon.domininghub.dto.post.PostLikeRequest;
import me.ryzeon.domininghub.dto.post.PostPageableResponse;
import me.ryzeon.domininghub.service.IPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    private final IPostService postService;

    @ApiResponse(
            responseCode = "201",
            description = "Post created",
            content = @Content(schema = @Schema(implementation = PostDto.class))
    )
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequest request) {
        var post = postService.createPost(request).orElseThrow(() -> new RuntimeException("Post not created"));
        return new ResponseEntity<>(PostDto.fromPost(post), HttpStatus.CREATED);
    }

    @ApiResponse(
            responseCode = "200",
            description = "Post found",
            content = @Content(schema = @Schema(implementation = PostDto.class))
    )
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        var post = postService.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return ResponseEntity.ok(PostDto.fromPost(post));
    }

    @ApiResponse(
            responseCode = "200",
            description = "Post liked",
            content = @Content()
    )
    @PostMapping("like")
    public ResponseEntity<PostDto> likePost(@RequestBody PostLikeRequest request) {
        postService.postLike(request);
        return ResponseEntity.ok().build();
    }

    @ApiResponse(
            responseCode = "200",
            description = "Post unliked",
            content = @Content()
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponse(
            responseCode = "200",
            description = "Posts found with pagination",
            content = {
                    @Content(schema = @Schema(implementation = PostPageableResponse.class))
            }
    )
    @GetMapping
    public ResponseEntity<PostPageableResponse> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<PostDto> postPage = postService.findAll(paging).map(PostDto::fromPost);
        return ResponseEntity.ok(
                new PostPageableResponse(
                        postPage.getContent(),
                        postPage.getNumber(),
                        postPage.getTotalPages(),
                        postPage.getTotalElements()
                )
        );
    }
}
