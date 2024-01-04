package com.sparta.admin.lecture.dto;

import com.sparta.admin.lecture.entity.LectureCategoryEnum;
import lombok.Getter;

@Getter
public class LectureUpdateRequest {

    private String lectureName;
    private long price;
    private String description;
    private LectureCategoryEnum category;
}
