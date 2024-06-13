package me.ryzeon.domininghub.service.impl;

import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.entity.Comment;
import me.ryzeon.domininghub.repository.CommentRepository;
import me.ryzeon.domininghub.service.ICommentService;
import me.ryzeon.domininghub.service.IPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 02:31
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final IPostService postService;

    @Override
    public Page<Comment> findByPostId(String postId, Pageable pageable) {
        var post = postService.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return commentRepository.findAllByPost(post, pageable);
    }

    @Override
    public void deleteComment(String commandId) {
        var comment = commentRepository.findById(commandId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}
