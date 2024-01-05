package com.sparta.admin.lecture.entity;

import com.sparta.admin.comment.entity.Comment;
import com.sparta.admin.instructor.entity.Instructor;
import com.sparta.admin.lecture.dto.LectureCreateRequest;
import com.sparta.admin.lecture.dto.LectureUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Lecture extends LectureDate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lectureName;
    private long price;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private LectureCategoryEnum category;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "lecture")
    private List<Comment> comments;

    public Lecture(LectureCreateRequest request,Instructor instructor) {
        this.lectureName = request.getLectureName();
        this.price = request.getPrice();
        this.description = request.getDescription();
        this.category = request.getCategory();
        this.instructor = instructor;
    }

    public Lecture update(LectureUpdateRequest request) {
        this.lectureName = request.getLectureName();
        this.price = request.getPrice();
        this.description = request.getDescription();
        this.category = request.getCategory();
        return this;
    }
}
