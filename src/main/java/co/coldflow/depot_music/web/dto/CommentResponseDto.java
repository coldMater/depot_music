package co.coldflow.depot_music.web.dto;

import co.coldflow.depot_music.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private String authorName;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.authorName = comment.getAuthorName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedDate();
    }
}
