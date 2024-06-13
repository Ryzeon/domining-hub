package me.ryzeon.domininghub.service;

import me.ryzeon.domininghub.dto.post.CreatePostRequest;
import me.ryzeon.domininghub.dto.post.PostLikeRequest;
import me.ryzeon.domininghub.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 01:38
 */
public interface IPostService {

    Optional<Post> createPost(CreatePostRequest request);

    Optional<Post> findById(String id);

    void postLike(PostLikeRequest request);

    void deletePost(String id);

    Page<Post> findAll(Pageable pageable);
}
