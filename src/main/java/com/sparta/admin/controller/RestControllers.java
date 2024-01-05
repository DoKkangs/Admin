package com.sparta.admin.controller;

import com.sparta.admin.comment.dto.CommentCreateRequest;
import com.sparta.admin.comment.dto.CommentResponse;
import com.sparta.admin.comment.dto.CommentUpdateRequest;
import com.sparta.admin.comment.service.CommentService;
import com.sparta.admin.instructor.dto.InstructorCreateRequest;
import com.sparta.admin.instructor.dto.InstructorResponse;
import com.sparta.admin.instructor.dto.InstructorUpdateRequest;
import com.sparta.admin.instructor.service.InstructorService;
import com.sparta.admin.lecture.dto.LectureCreateRequest;
import com.sparta.admin.lecture.dto.LectureResponse;
import com.sparta.admin.lecture.dto.LectureUpdateRequest;
import com.sparta.admin.lecture.entity.LectureCategoryEnum;
import com.sparta.admin.lecture.service.LectureService;
import com.sparta.admin.user.dto.SignupRequestDto;
import com.sparta.admin.user.dto.UserResponseDto;
import com.sparta.admin.user.entity.UserRoleEnum;
import com.sparta.admin.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class RestControllers {

    private final InstructorService instructorService;
    private final LectureService lectureService;
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/instructor")
    public ResponseEntity<InstructorResponse> saveInstructor(@RequestBody InstructorCreateRequest request){
        InstructorResponse response = instructorService.saveInstructor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PutMapping("/instructor/{instructorId}")
    public ResponseEntity<InstructorResponse> updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorUpdateRequest request){
        InstructorResponse response = instructorService.updateInstructor(instructorId, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/lecture")
    public ResponseEntity<LectureResponse> createLecture(@RequestBody LectureCreateRequest request){
        LectureResponse response = lectureService.createLecture(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PutMapping("/lecture/{lectureId}")
    public ResponseEntity<LectureResponse> updateLecture(@PathVariable Long lectureId, @RequestBody LectureUpdateRequest request){
        LectureResponse response = lectureService.updateLecture(lectureId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<InstructorResponse> getInstructor(@PathVariable Long instructorId){
        InstructorResponse response = instructorService.getInstructor(instructorId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<LectureResponse> getLecture(@PathVariable Long lectureId){
        LectureResponse response = lectureService.getLecture(lectureId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lecture/instructor/{instructorId}")
    public ResponseEntity<List<LectureResponse>> getInstructorLecture(@PathVariable Long instructorId){
        List<LectureResponse> response = lectureService.getInstructorLecture(instructorId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lecture/category/{category}")
    public ResponseEntity<List<LectureResponse>> getCategoryLecture(@PathVariable LectureCategoryEnum category){
        List<LectureResponse> response = lectureService.getCategoryLecture(category);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        UserResponseDto responseDto = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/lecture/{lecture_id}/comments")
    public CommentResponse commentSave(@PathVariable Long lectureId, @RequestBody CommentCreateRequest request) {
        return commentService.commentSave(lectureId,request);
    }

    @PutMapping("/lecture/{lecture_id}/comments/{commentId}")
    public CommentResponse commentUpdate(@PathVariable Long lecture_id, @PathVariable Long commentId,
                                         @RequestBody CommentUpdateRequest request){
        return commentService.commentUpdate(lecture_id,commentId,request);
    }

    @DeleteMapping("/lecture/{lecture_id}/comments/{commentId}")
    public void deleteComment(@PathVariable Long lectureId,
                              @PathVariable Long commentId){
        commentService.deleteComment(lectureId,commentId);
    }

}
