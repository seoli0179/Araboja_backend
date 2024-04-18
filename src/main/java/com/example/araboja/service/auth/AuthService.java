package com.example.araboja.service.auth;

import com.example.araboja.data.dto.MemberJoinDto;
import com.example.araboja.data.entity.Member;
import com.example.araboja.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean addMember(MemberJoinDto dto) {

        if (memberRepository.findById(dto.getEmail()).isPresent()) return false;
        else {
            try {
                Member member2 = dto.toEntity();
                member2.setPassword(passwordEncoder.encode(member2.getPassword()));
                memberRepository.save(member2);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return false;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return memberRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));

    }
}
