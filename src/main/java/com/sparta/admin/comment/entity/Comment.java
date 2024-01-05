package com.sparta.admin.comment.entity;

import com.sparta.admin.comment.dto.CommentCreateRequest;
import com.sparta.admin.comment.dto.CommentUpdateRequest;
import com.sparta.admin.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Comment(String content, Lecture lecture) {
        this.content = content;
        this.lecture = lecture;
    }

    public Comment update(String content) {
        this.content = content;
        return this;
    }
}
