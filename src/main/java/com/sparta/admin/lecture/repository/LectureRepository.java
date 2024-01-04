package com.sparta.admin.lecture.repository;


import com.sparta.admin.instructor.entity.Instructor;
import com.sparta.admin.lecture.entity.Lecture;
import com.sparta.admin.lecture.entity.LectureCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findByInstructorId(Long id);

    List<Lecture> findAllByInstructorOrderByRegistrationDateDesc(Instructor instructor);
    List<Lecture> findAllByCategoryOrderByRegistrationDateDesc(LectureCategoryEnum category);
}
