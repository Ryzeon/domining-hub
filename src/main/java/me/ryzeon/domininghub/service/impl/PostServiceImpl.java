package me.ryzeon.domininghub.service.impl;

import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.dto.post.CreatePostRequest;
import me.ryzeon.domininghub.dto.post.PostLikeRequest;
import me.ryzeon.domininghub.entity.Post;
import me.ryzeon.domininghub.repository.PostRepository;
import me.ryzeon.domininghub.service.IPostService;
import me.ryzeon.domininghub.service.IUserService;
import me.ryzeon.domininghub.shared.storage.entity.File;
import me.ryzeon.domininghub.shared.storage.repository.FileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 01:40
 */
@Service
@AllArgsConstructor
public class PostServiceImpl implements IPostService {

    private final IUserService userService;
    private final PostRepository postRepository;
    private final FileRepository fileRepository;

    @Override
    public Optional<Post> createPost(CreatePostRequest request) {
        var author = userService.findById(request.authorId()).orElseThrow(() -> new RuntimeException("User not found"));
        var files = request.files() == null ?
                new ArrayList<File>() :
                request.files().stream().map(id -> fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"))).toList();
        var post = new Post(
                author,
                request.title(),
                request.content(),
                request.company(),
                files
        );
        return Optional.of(postRepository.save(post));
    }

    @Override
    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    @Override
    public void postLike(PostLikeRequest request) {
        var post = postRepository.findById(request.postId()).orElseThrow(() -> new RuntimeException("Post not found"));
        var author = userService.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (request.like()) {
            post.addLike(author);
        } else {
            System.out.printf("Removing like from %s%n", author.getId());
            post.removeLike(author);
        }
        postRepository.save(post);
    }

    @Override
    public void deletePost(String id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
