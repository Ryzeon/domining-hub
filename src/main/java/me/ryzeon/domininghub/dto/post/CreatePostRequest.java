package me.ryzeon.domininghub.dto.post;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 01:37
 */
public record CreatePostRequest(
        String authorId,
        String title,
        String content,
        String company,
        List<String> files
) {
}
