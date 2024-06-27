package me.ryzeon.domininghub.dto.comment;

import me.ryzeon.domininghub.entity.Comment;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 02:26
 */
public record CommentDto(
        String id,
        String content,
        String userId,
        String username,
        String userFullName,
        String postId,
        String commentType,
        boolean subComment,
        List<CommentDto> subComments
) {

    public static CommentDto fromComment(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor().getId(),
                comment.getAuthor().getUsername(),
                comment.getAuthor().getFullName(),
                comment.getPost().getId(),
                comment.getType().name(),
                comment.isSubComment(),
                comment.getSubComments().stream().map(CommentDto::fromComment).toList()
        );
    }
}
