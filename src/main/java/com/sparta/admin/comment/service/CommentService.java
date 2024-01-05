package com.sparta.admin.comment.service;

import com.sparta.admin.comment.dto.CommentCreateRequest;
import com.sparta.admin.comment.dto.CommentResponse;
import com.sparta.admin.comment.dto.CommentUpdateRequest;
import com.sparta.admin.comment.entity.Comment;
import com.sparta.admin.comment.repository.CommentRepository;
import com.sparta.admin.lecture.entity.Lecture;
import com.sparta.admin.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final LectureRepository lectureRepository;

    public CommentResponse commentSave(Long lectureId, CommentCreateRequest request) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(IllegalArgumentException::new);
        Comment comment = new Comment(request.getContent(), lecture);
        commentRepository.save(comment);
        CommentResponse response = new CommentResponse(comment);
        return response;
    }

    @Transactional
    public CommentResponse commentUpdate(Long lectureId, Long commentId, CommentUpdateRequest request) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(IllegalArgumentException::new);
        Comment comment = commentRepository.findByLectureAndId(lecture,commentId)
                .orElseThrow(IllegalArgumentException::new);
        Comment updateComment = comment.update(request.getContent());
        return new CommentResponse(updateComment);
    }

    @Transactional
    public void deleteComment(Long lectureId, Long commentId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(IllegalArgumentException::new);
        Comment comment = commentRepository.findByLectureAndId(lecture,commentId)
                .orElseThrow(IllegalArgumentException::new);
        commentRepository.delete(comment);
    }
}
