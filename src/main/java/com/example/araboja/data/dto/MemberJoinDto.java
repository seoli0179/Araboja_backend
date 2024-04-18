package com.example.araboja.data.dto;

import com.example.araboja.data.entity.Member;
import com.example.araboja.data.state.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberJoinDto {

    private String email;
    private String password;
    private String name;

    public Member toEntity() {

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(name);
        member.setRoles(Role.USER.getValue());
        return member;

    }

}
