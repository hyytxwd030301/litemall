package com.hyy.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class JwtUtil {
    private static String SIGN="JFI(WJEWEN@(";
    private static String token="";
    public static String getToken(Map<String, String> map) {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            builder.withClaim(k,v);
        });
        token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SIGN));

        return token;
    }

    public static DecodedJWT Certification(String token){
        Verification verification = JWT.require(Algorithm.HMAC256(SIGN));
        DecodedJWT verify = verification.build().verify(token);
        return verify;
    }
}
