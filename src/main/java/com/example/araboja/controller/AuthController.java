package com.example.araboja.controller;

import com.example.araboja.data.dto.MemberJoinDto;
import com.example.araboja.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinDto memberJoinDto) {

        try {
            if (authService.addMember(memberJoinDto)) {
                return ResponseEntity.ok("회원가입 성공");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");

    }

}
