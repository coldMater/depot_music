package co.coldflow.depot_music.dto;

import co.coldflow.depot_music.entity.Comment;

import java.time.format.DateTimeFormatter;

public class CommentResponseDto {
    private long id;
    private String authorName;
    private String content;
    private String createdDate;
    private String createdTime;
    private String authorId;
    private String username;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.authorName = comment.getAuthorName();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.createdTime = comment.getCreatedDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.authorId = comment.getCreatedBy();
        this.username = comment.getUsername();
    }

    public long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getUsername() {
        return username;
    }
}
