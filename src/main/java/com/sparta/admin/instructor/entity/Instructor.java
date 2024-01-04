package com.sparta.admin.instructor.entity;

import com.sparta.admin.instructor.dto.InstructorCreateRequest;
import com.sparta.admin.instructor.dto.InstructorUpdateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String experienceYear;
    private String company;
    private String phoneNumber;
    private String introduction;

    public Instructor(InstructorCreateRequest request) {
        this.name = request.getName();
        this.experienceYear = request.getExperienceYear();
        this.company = request.getCompany();
        this.phoneNumber = request.getPhoneNumber();
        this.introduction = request.getIntroduction();
    }

    public Instructor update(InstructorUpdateRequest request) {
        this.experienceYear = request.getExperienceYear();
        this.company = request.getCompany();
        this.phoneNumber = request.getPhoneNumber();
        this.introduction = request.getIntroduction();
        return this;
    }
}
