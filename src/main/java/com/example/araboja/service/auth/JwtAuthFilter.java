package com.example.araboja.service.auth;

import com.example.araboja.data.entity.Member;
import com.example.araboja.data.repository.MemberRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private static final String HEADER_AUTH = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String atc = request.getHeader(HEADER_AUTH);

        if (Objects.equals(atc, "") || atc == null) {
            doFilter(request, response, filterChain);
            return;
        }

        if (!jwtUtil.verifyToken(atc)) {
            throw new JwtException("Access Token 만료!");
        }

        if (jwtUtil.verifyToken(atc)) {

            Member member = memberRepository.findById(jwtUtil.getUid(atc)).orElseThrow(IllegalStateException::new);
            Authentication auth = getAuthentication(member);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        filterChain.doFilter(request, response);

    }

    public Authentication getAuthentication(Member member) {
        return new UsernamePasswordAuthenticationToken(member, "", member.getAuthorities());
    }

}
