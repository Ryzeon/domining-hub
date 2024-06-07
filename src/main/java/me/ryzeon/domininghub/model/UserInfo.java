package me.ryzeon.domininghub.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:50
 */
@Data
@Document
public class UserInfo {

    @Id
    private ObjectId id;

    private String position;
    private String company;
    private String about;
}
