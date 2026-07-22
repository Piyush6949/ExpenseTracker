package org.expensetracker.authentication.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.expensetracker.authentication.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretString;

    public String jwts(String username, User.Role role){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
        Date issAt = new Date();
        Date expAt = new Date(issAt.getTime() + 15*60*1000);
        return Jwts.builder()
                .subject(username)
                .issuedAt(issAt)
                .expiration(expAt)
                .signWith(key)
                .claim("role",role)
                .compact();
    }

    public Claims getClaims(String jwt){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
            return Jwts
                    .parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
    }
}
