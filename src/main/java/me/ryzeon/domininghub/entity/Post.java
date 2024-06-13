package me.ryzeon.domininghub.entity;

import me.ryzeon.domininghub.shared.storage.entity.File;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:51
 */
@Document
public class Post {

    @Id
    private String id;

    @DBRef
    private User user;

    private String title;
    private String content;

    @CreatedDate
    private Date createdAt;

    private String company;

    @DBRef
    private List<File> files;

    private int likes;
}
