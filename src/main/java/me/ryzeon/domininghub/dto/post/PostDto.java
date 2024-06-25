package me.ryzeon.domininghub.dto.post;

import me.ryzeon.domininghub.entity.Post;
import me.ryzeon.domininghub.entity.User;
import me.ryzeon.domininghub.shared.storage.entity.File;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 01:35
 */
public record PostDto(
        String id,
        String authorId,
        String authorUsername,
        String title,
        String content,
        Date createdAt,
        String company,
        List<String> files,
        List<String> likes
) {
    public static PostDto fromPost(Post post) {
        return new PostDto(
                post.getId(),
                post.getAuthor().getId(),
                post.getAuthor().getUsername(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getCompany(),
                post.getFiles().stream().map(File::getViewUrl).toList(),
                post.getLikes().stream().map(User::getId).toList()
        );
    }
}
