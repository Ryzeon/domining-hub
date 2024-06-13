package me.ryzeon.domininghub.dto.post;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 01:51
 */
public record PostLikeRequest(
        String postId,
        String userId,
        boolean like
) {
}
