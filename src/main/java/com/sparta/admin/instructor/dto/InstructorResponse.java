package com.sparta.admin.instructor.dto;

import com.sparta.admin.instructor.entity.Instructor;
import lombok.Getter;

@Getter
public class InstructorResponse {

    private Long id;
    private String name;
    private String experienceYear;;
    private String company;
    private String phoneNumber;
    private String introduction;

    public InstructorResponse(Instructor saveInstructor) {
        this.id = saveInstructor.getId();
        this.name = saveInstructor.getName();
        this.experienceYear = saveInstructor.getExperienceYear();
        this.company = saveInstructor.getCompany();
        this.phoneNumber = saveInstructor.getPhoneNumber();
        this.introduction = saveInstructor.getIntroduction();
    }
}
