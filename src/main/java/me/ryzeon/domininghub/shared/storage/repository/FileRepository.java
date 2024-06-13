package me.ryzeon.domininghub.shared.storage.repository;

import me.ryzeon.domininghub.shared.storage.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/11/24 @ 17:10
 */
@Repository
public interface FileRepository extends MongoRepository<File, String> {
}
