package com.devgym.gymmanager.auth.utils;

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
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorization == null){
            log.info("authrization이 없습니다.");
            filterChain.doFilter(request, response);
            return;
        }
        if (!authorization.startsWith("Bearer")){
            log.info("toekn이 Bearer가 아닙니다");
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.split(" ")[1];
        log.info("token : {}", token);
        if(JwtUtil.isExpired(token, secretKey)){
            log.info("Token이 만료되었다.");
            filterChain.doFilter(request, response);
            return;
        }
        String memberName = JwtUtil.getMemberName(token, secretKey);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberName, null, List.of(new SimpleGrantedAuthority("USER")));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
