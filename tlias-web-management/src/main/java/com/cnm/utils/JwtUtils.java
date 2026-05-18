package com.cnm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils
{
    private static final String SECRET_KEY="d2NubWxnYg==d2NubWxnYg==d2NubWxnYg==d2NubWxnYg==";
    private static final long EXPIRATION_TIME=12*60*60*1000;
    /**
     *生成JWT令牌
     * @param claims 令牌中包含的信息
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String,Object> claims)
    {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .compact();
    }
    public static Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
