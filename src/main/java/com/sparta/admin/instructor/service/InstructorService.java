package com.sparta.admin.instructor.service;

import com.sparta.admin.instructor.dto.InstructorCreateRequest;
import com.sparta.admin.instructor.dto.InstructorResponse;
import com.sparta.admin.instructor.dto.InstructorUpdateRequest;
import com.sparta.admin.instructor.entity.Instructor;
import com.sparta.admin.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Transactional
    public InstructorResponse saveInstructor(InstructorCreateRequest request) {
        Instructor instructor = new Instructor(request);
        Instructor saveInstructor = instructorRepository.save(instructor);
        InstructorResponse instructorResponse = new InstructorResponse(saveInstructor);
        return instructorResponse;
    }


    @Transactional
    public InstructorResponse updateInstructor(Long instructorId, InstructorUpdateRequest request) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(IllegalArgumentException::new);
        Instructor updateInstructor = instructor.update(request);
        return new InstructorResponse(updateInstructor);
    }

    @Transactional
    public InstructorResponse getInstructor(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(IllegalArgumentException::new);
        return new InstructorResponse(instructor);
    }
}
