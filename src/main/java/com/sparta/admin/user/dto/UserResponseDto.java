package com.sparta.admin.user.dto;

import com.sparta.admin.user.entity.User;
import com.sparta.admin.user.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String email;
    private String password;
    private String department;
    private UserRoleEnum role;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.department = user.getDepartment();
        this.role = user.getRole();
    }
}
