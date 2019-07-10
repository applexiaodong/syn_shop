package com.dong.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author: wangxd
 * @create: 2019-06-25
 **/
@Component
public class JwtUtil {

    private static final String TOKEN_KEY = "1cdaef38fd155247702158cb70f8e4f8";//私钥

    /**
     * 创建token
     * */
    public static String createToken(String userId){
        return JWT.create()
                .withAudience(String.valueOf(userId))
                .sign(Algorithm.HMAC256(TOKEN_KEY));
    }

    /**
     * 校验token
     * */
    public static void verify(String token,String userId){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_KEY)).build();
        jwtVerifier.verify(token);
    }

}
