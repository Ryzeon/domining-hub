package me.ryzeon.domininghub.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:56
 */
@Document
@Getter
public class Comment {

    @Id
    private String id;

    @DBRef
    private User author;

    private CommentType type;

    @DBRef
    private Post post;

    private String content;

    private boolean  subComment;

    @DBRef
    private List<Comment> subComments;

    @CreatedDate
    private Date createdAt;

    public Comment() {
    }

    void addSubComment(Comment comment) {
        subComments.add(comment);
    }
}
