package me.ryzeon.domininghub.repository;

import me.ryzeon.domininghub.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 19:41
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
