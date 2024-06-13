package me.ryzeon.domininghub.entity;

import lombok.Getter;
import me.ryzeon.domininghub.shared.storage.entity.File;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:51
 */
@Document
@Getter
public class Post {

    @Id
    private String id;

    @DBRef
    private User author;

    private String title;
    private String content;

    @CreatedDate
    private Date createdAt;

    private String company;

    @DBRef
    private List<File> files;

    @DBRef
    private Set<User> likes;

    public Post(User author, String title, String content, String company, List<File> files) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.company = company;
        this.files = files;
        this.likes = Set.of();
    }

    public Post() {

    }

    public void addLike(User  user) {
        this.likes.add(user);
    }

    public void removeLike(User user) {
        this.likes.removeIf(like -> like.getId().equals(user.getId()));
    }
}
