package me.ryzeon.domininghub.service;

import me.ryzeon.domininghub.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 02:31
 */
public interface ICommentService {

    Page<Comment> findByPostId(String postId, Pageable pageable);

    void deleteComment(String commandId);
}
