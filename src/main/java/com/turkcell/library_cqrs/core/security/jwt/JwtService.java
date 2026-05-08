package com.turkcell.library_cqrs.core.security.jwt;

import java.time.Instant;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


import java.util.Date;

@Service 
@EnableConfigurationProperties(JwtProperties.class)
public class JwtService {

    private final JwtProperties jwtProperties;
    private final SecretKey signingKey;

    public JwtService(JwtProperties jwtProperties, SecretKey signingKey) {
        this.jwtProperties = jwtProperties;
        this.signingKey = signingKey;
    }

    public String generate(UUID userId, String studentNo) {

        Instant now = Instant.now();
        // JWT oluşturma işlemi burada gerçekleştirilecek
        // Örneğin, JJWT kütüphanesi kullanarak token oluşturabilirsiniz
        // Token oluştururken, jwtProperties içindeki secret ve expirationInSeconds değerlerini kullanabilirsiniz
        // Bu sadece bir örnektir, gerçek token oluşturulmalıdır
        
        return Jwts.builder()
            .issuer(this.jwtProperties.getIssuer())
            .subject(userId.toString())
            .claim("studentNo", studentNo)
            .claim("deneme", "deneme")
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(jwtProperties.getExpirationInSeconds())))
            .signWith(signingKey)
            .compact();
    }

}
