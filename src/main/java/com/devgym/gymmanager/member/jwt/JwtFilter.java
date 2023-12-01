package com.devgym.gymmanager.member.jwt;

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

import static com.devgym.gymmanager.common.exception.ErrorCode.*;


@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final String secretKey;
    private final String refreshSecretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        String type = request.getHeader("Token-Type");
        if(authorization == null){
            log.info(NOT_EXIST_TOKEN.getMessage());
            filterChain.doFilter(request, response);
            return;
        }
        if (!authorization.startsWith("Bearer")){
            log.info(NOT_BEARER_TOKEN.getMessage());
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.split(" ")[1];

        String memberName = "";
        if(type.equals("access")){
            log.info("In ACCESS");
            if(JwtUtil.isExpired(token, secretKey)){ //토큰이 만료된 경우,
                log.info(EXPIRED_TOKEN.getMessage());
                filterChain.doFilter(request, response);
                return;
            }
            memberName = JwtUtil.getMemberName(token, secretKey);
        }
        else if(type.equals("refresh")){
            log.info("In REFRESH");
            if(JwtUtil.isExpired(token, refreshSecretKey)){ //토큰이 만료된 경우,
                log.info(EXPIRED_TOKEN.getMessage());
                filterChain.doFilter(request, response);
                return;
            }
            memberName = JwtUtil.getMemberName(token, refreshSecretKey);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberName, null, List.of(new SimpleGrantedAuthority("USER")));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication); //이제 이 authentication에 접근하여 name값 접근 가능
        filterChain.doFilter(request, response);
    }
}
