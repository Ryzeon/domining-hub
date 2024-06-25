package me.ryzeon.domininghub.service.impl;

import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.dto.comment.CommentRequest;
import me.ryzeon.domininghub.entity.Comment;
import me.ryzeon.domininghub.entity.CommentType;
import me.ryzeon.domininghub.repository.CommentRepository;
import me.ryzeon.domininghub.service.ICommentService;
import me.ryzeon.domininghub.service.IPostService;
import me.ryzeon.domininghub.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private final IUserService userService;

    @Override
    public Page<Comment> findByPostId(String postId, Pageable pageable) {
        var post = postService.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return commentRepository.findAllByPostAndSubComment(post, false, pageable);
    }

    @Override
    public void deleteComment(String commandId) {
        var comment = commentRepository.findById(commandId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }

    @Override
    public Optional<Comment> createComment(CommentRequest request) {
        var user = userService.findById(request.userId()).orElseThrow(() -> new RuntimeException("Author nor found"));
        var post = postService.findById(request.postId());
        var comment = commentRepository.findById(request.commentId());
        Comment savedComment = null;
        if (post.isPresent()) {
            savedComment = new Comment(user, CommentType.POST, post.get(), request.content(), false, List.of());
            savedComment = commentRepository.save(savedComment);
            return Optional.of(savedComment);
        } else {
            if (comment.isPresent()) {
                savedComment = new Comment(
                        user,
                        CommentType.SUB_COMMENT,
                        null,
                        request.content(),
                        true,
                        List.of());
                savedComment = commentRepository.save(savedComment);
                comment.get().addSubComment(savedComment);
                commentRepository.save(comment.get());
                return Optional.of(savedComment);
            }
        }
        return Optional.empty();
    }
}
