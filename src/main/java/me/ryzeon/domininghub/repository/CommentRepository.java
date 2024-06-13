package me.ryzeon.domininghub.repository;

import me.ryzeon.domininghub.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 19:40
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
