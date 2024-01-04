package com.sparta.admin.user.service;

import com.sparta.admin.user.dto.SignupRequestDto;
import com.sparta.admin.user.dto.UserResponseDto;
import com.sparta.admin.user.entity.User;
import com.sparta.admin.user.entity.UserRoleEnum;
import com.sparta.admin.user.jwt.JwtUtil;
import com.sparta.admin.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN
//    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public UserResponseDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String enterPassword = requestDto.getPassword();
        if (enterPassword == null || enterPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

        String password = passwordEncoder.encode(enterPassword);


        // email 중복확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.STAFF;
        if (requestDto.isManager()) {
            if (!("curriculum".equals(requestDto.getDepartment()) || "development".equals(requestDto.getDepartment()))) {
                throw new IllegalArgumentException("권한을 가진 부서가 아닙니다.");
            }
            role = UserRoleEnum.MANAGER;
        }

        if(!(requestDto.getDepartment().equals("marketing") || requestDto.getDepartment().equals("curriculum")
        || requestDto.getDepartment().equals("development"))){
            throw new IllegalArgumentException();
        }
        String department = requestDto.getDepartment();
        // 사용자 등록
        User user = new User(email, password, department, role);
        userRepository.save(user);
        return new UserResponseDto(user);
    }
}