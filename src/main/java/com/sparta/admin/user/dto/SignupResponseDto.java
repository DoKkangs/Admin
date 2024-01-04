package com.sparta.admin.user.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private Long id;
    private String email;
    private String password;
    private String department;
    private boolean manager;
}
