package com.sparta.admin.instructor.repository;


import com.sparta.admin.instructor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor findByName(String instructorName);
}
