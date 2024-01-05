package com.sparta.admin.comment.repository;

import com.sparta.admin.comment.entity.Comment;
import com.sparta.admin.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByLectureAndId(Lecture lecture, Long commentId);
}
