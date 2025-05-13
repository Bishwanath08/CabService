package org.service.cabService.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

        private final String SECRET_KEY = "this_is_a_very_secret_key_change_it";
        private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

        private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        public String generateToken(String mobile, String role) {
            return Jwts.builder()
                    .setSubject(mobile)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }

        public String extractMobile(String token) {
            return getClaims(token).getSubject();
        }

        public boolean isTokenValid(String token) {
            try {
                getClaims(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        private Claims getClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
    }
