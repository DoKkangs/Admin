package com.sparta.admin.comment.dto;

import com.sparta.admin.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {

    private Long id;
    private String content;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
