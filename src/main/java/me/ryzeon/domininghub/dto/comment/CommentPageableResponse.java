package me.ryzeon.domininghub.dto.comment;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 02:30
 */
public record CommentPageableResponse(
        List<CommentDto> items,
        int currentPage,
        int totalPages,
        long totalItems
) {
}
