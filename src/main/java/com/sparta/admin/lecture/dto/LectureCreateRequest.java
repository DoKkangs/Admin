package com.sparta.admin.lecture.dto;

import com.sparta.admin.instructor.entity.Instructor;
import com.sparta.admin.lecture.entity.LectureCategoryEnum;
import lombok.Getter;

@Getter
public class LectureCreateRequest {

    private String lectureName;
    private long price;
    private String description;
    private LectureCategoryEnum category;
    private String instructorName;
}
