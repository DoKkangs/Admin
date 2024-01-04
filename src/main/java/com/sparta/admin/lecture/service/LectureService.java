package com.sparta.admin.lecture.service;

import com.sparta.admin.instructor.dto.InstructorCreateRequest;
import com.sparta.admin.instructor.dto.InstructorResponse;
import com.sparta.admin.instructor.dto.InstructorUpdateRequest;
import com.sparta.admin.instructor.entity.Instructor;
import com.sparta.admin.instructor.repository.InstructorRepository;
import com.sparta.admin.instructor.service.InstructorService;
import com.sparta.admin.lecture.dto.LectureCreateRequest;
import com.sparta.admin.lecture.dto.LectureResponse;
import com.sparta.admin.lecture.dto.LectureUpdateRequest;
import com.sparta.admin.lecture.entity.Lecture;
import com.sparta.admin.lecture.entity.LectureCategoryEnum;
import com.sparta.admin.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    @Transactional
    public LectureResponse createLecture(LectureCreateRequest request) {
        Instructor instructor = instructorRepository.findByName(request.getInstructorName());
        Lecture lecture = new Lecture(request,instructor);
        lectureRepository.save(lecture);
        LectureResponse lectureResponse = new LectureResponse(lecture);
        return lectureResponse;
    }

    @Transactional
    public LectureResponse updateLecture(Long lectureId, LectureUpdateRequest request) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(IllegalArgumentException::new);
        Lecture updateLecture = lecture.update(request);
        return new LectureResponse(updateLecture);
    }

    public List<LectureResponse> getInstructorLecture(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 강사를 찾을 수 없습니다"));

        List<Lecture> instructorLectures = lectureRepository
                .findAllByInstructorOrderByRegistrationDateDesc(instructor);

        return instructorLectures.stream()
                .map(LectureResponse::new) // 강의를 강의 응답 객체로 변환
                .collect(Collectors.toList());
    }

    @Transactional
    public List<LectureResponse> getCategoryLecture(LectureCategoryEnum category) {
        List<Lecture> lectures = lectureRepository.findAllByCategoryOrderByRegistrationDateDesc(category);
        return lectures.stream().map(LectureResponse::new).collect(Collectors.toList());
    }
}
