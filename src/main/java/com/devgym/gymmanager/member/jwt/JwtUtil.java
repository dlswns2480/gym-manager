package com.devgym.gymmanager.member.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
public class JwtUtil {

    public static String createAccessToken(String memberName, String secretKey, Long accessExpiredMs){
        Claims claims = Jwts.claims();
        claims.put("memberName", memberName);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public static String createRefreshToekn(String memberName, String secretKey, Long refreshExpiredMs){
        Claims claims = Jwts.claims();
        claims.put("memberName", memberName);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static boolean isExpired(String token, String secretKey) {
        log.info(secretKey);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }
    public static String getMemberName(String token, String secretKey){
        log.info("secret key : {}", secretKey);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("memberName", String.class);
    }
}
