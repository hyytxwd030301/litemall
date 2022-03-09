package com.hyy.wx.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Calendar;
import java.util.Map;

public class WxJwtUtil {
    private static String SIGN="X-Litemall-Token";
    public static String getToken(Integer id){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,7);
        String token = JWT.create().withClaim("id", id)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    public static Integer getUserId(String token ){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

        Integer id = decodedJWT.getClaim("userId").asInt();
        return id;

    }
}
