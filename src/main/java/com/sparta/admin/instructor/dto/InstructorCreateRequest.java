package com.sparta.admin.instructor.dto;

import lombok.Getter;

@Getter
public class InstructorCreateRequest {

    private String name;
    private String experienceYear;
    private String company;
    private String phoneNumber;
    private String introduction;
}
