package com.devgym.gymmanager.auth.utils;

import com.devgym.gymmanager.exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.devgym.gymmanager.exception.ErrorCode.*;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorization == null){
            filterChain.doFilter(request, response);
            throw new CustomException(NOT_EXIST_TOKEN);
        }
        if (!authorization.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            throw new CustomException(NOT_BEARER_TOKEN);
        }
        String token = authorization.split(" ")[1];

        if(JwtUtil.isExpired(token, secretKey)){ //토큰이 만료된 경우,
            filterChain.doFilter(request, response);
            throw new CustomException(EXPIRED_TOKEN);
        }
        String memberName = JwtUtil.getMemberName(token, secretKey);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberName, null, List.of(new SimpleGrantedAuthority("USER")));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication); //이제 이 authentication에 접근하여 name값 접근 가능
        filterChain.doFilter(request, response);
    }
}
