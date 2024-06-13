package me.ryzeon.domininghub.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:56
 */
@Document
public class Comment {

    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Post post;

    private String content;

    @CreatedDate
    private Date createdAt;
}
