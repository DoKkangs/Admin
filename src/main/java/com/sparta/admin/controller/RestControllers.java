package com.sparta.admin.controller;

import com.sparta.admin.instructor.dto.InstructorCreateRequest;
import com.sparta.admin.instructor.dto.InstructorResponse;
import com.sparta.admin.instructor.dto.InstructorUpdateRequest;
import com.sparta.admin.instructor.service.InstructorService;
import com.sparta.admin.lecture.dto.LectureCreateRequest;
import com.sparta.admin.lecture.dto.LectureResponse;
import com.sparta.admin.lecture.dto.LectureUpdateRequest;
import com.sparta.admin.lecture.entity.Lecture;
import com.sparta.admin.lecture.entity.LectureCategoryEnum;
import com.sparta.admin.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestControllers {

    private final InstructorService instructorService;
    private final LectureService lectureService;


    @PostMapping("/admin/instructor")
    public InstructorResponse saveInstructor(@RequestBody InstructorCreateRequest request){
        return instructorService.saveInstructor(request);
    }

    @PutMapping("/admin/instructor/{instructorId}")
    public InstructorResponse updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorUpdateRequest request){
        return instructorService.updateInstructor(instructorId,request);
    }

    @PostMapping("/admin/lecture")
    public LectureResponse createLecture(@RequestBody LectureCreateRequest request){
        return lectureService.createLecture(request);
    }

    @PutMapping("/admin/lecture/{lectureId}")
    public LectureResponse updateLecture(@PathVariable Long lectureId, @RequestBody LectureUpdateRequest request){
        return lectureService.updateLecture(lectureId,request);
    }

    @GetMapping("/admin/instructor/{instructorId}")
    public InstructorResponse getInstructor(@PathVariable Long instructorId){
        return instructorService.getInstructor(instructorId);
    }

    @GetMapping("/admin/lecture/instructor/{instructorId}")
    public List<LectureResponse> getInstructorLecture(@PathVariable Long instructorId){
        return lectureService.getInstructorLecture(instructorId);
    }

    @GetMapping("/admin/lecture/{category}")
    public List<LectureResponse> getCategoryLecture(@PathVariable LectureCategoryEnum category){
        return lectureService.getCategoryLecture(category);
    }



}
