package com.cynen.ssd.util;


import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("jwt.config")
public class JwtUtils {

    private  String key;
    private  long ttl;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String creatJWT(String id, String subject, List<String> roles){

        Long now = System.currentTimeMillis() ;
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,key)
                .claim("roles",roles);
        if (ttl > 0) {
            builder.setExpiration(new Date(now + ttl));
        }
        return builder.compact();
    }


    public Claims parseJWT(String jwtString){
        try{
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtString).getBody();
            return claims;
        }catch (ExpiredJwtException exception){
            System.out.println("过期了....");
        }
        return null;
    }

}
