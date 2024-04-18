package com.example.araboja.handler;

import com.example.araboja.data.dto.Token;
import com.example.araboja.data.entity.Member;
import com.example.araboja.data.repository.MemberRepository;
import com.example.araboja.service.auth.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginFormAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private static final String HEADER_AUTH = "Authorization";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        if (authentication != null && authentication.getPrincipal() != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                String userId = ((UserDetails) authentication.getPrincipal()).getUsername();

                Optional<Member> member = memberRepository.findById(userId);

                if (member.isPresent()) {
                    System.out.println("Current user ID: " + userId);
                    Token token = jwtUtil.generateToken(member.get());

                    //CookieUtils.addCookie(response,"accessToken", token.getAccessToken(),1800);
                    //CookieUtils.addCookieNotHttpOnly(response,"loginFlag", "True",1800);
                    //CookieUtils.addCookie(response,"Authorization", "Bearer " + token.getAccessToken(),3600);

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setHeader("accessToken", token.getAccessToken());
                    response.setHeader("refreshToken", token.getRefreshToken());
                    response.getWriter().write("로그인 성공");
                    return;
                }

            }
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("로그인 실패");

    }

}
