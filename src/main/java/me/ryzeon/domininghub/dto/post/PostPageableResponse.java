package me.ryzeon.domininghub.dto.post;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 02:02
 */
public record PostPageableResponse (
        List<PostDto> posts,
        int currentPage,
        int totalPages,
        long totalItems
) {
}
