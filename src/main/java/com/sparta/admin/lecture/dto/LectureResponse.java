package com.sparta.admin.lecture.dto;

import com.sparta.admin.instructor.entity.Instructor;
import com.sparta.admin.lecture.entity.Lecture;
import com.sparta.admin.lecture.entity.LectureCategoryEnum;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureResponse {

    private Long id;
    private String lectureName;
    private long price;
    private String description;
    private LectureCategoryEnum category;
    private String instructorName;
    private LocalDate registrationDate;

    public LectureResponse(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.instructorName = lecture.getInstructor().getName();
        this.registrationDate = lecture.getRegistrationDate();
    }
}
