package me.ryzeon.domininghub.dto.comment;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 02:29
 */
public record CommentRequest(
        String content,
        String userId,
        // If postId is null, it means that the comment is a sub-comment
        String postId,
        // If commentId is null, it means that the comment is a post
        String commentId
) {
    public CommentRequest {
        if (postId == null) {
            postId = "";
        }
        if (commentId == null) {
            commentId = "";
        }
    }
}
