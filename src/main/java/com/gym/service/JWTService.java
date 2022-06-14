package com.gym.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gym.config.JwtPara;
import com.gym.bean.Constant;
import com.gym.config.JwtPara;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class JWTService {
    @Resource
    private JwtPara jwt;

    public String createToken(String userId, String role) {
        return JWT.create()
                .withIssuer(jwt.getIssuer())
                .withSubject(role)
                .withAudience(userId)
                .withIssuedAt(new Date())
                .withExpiresAt(DateUtils.addDays(new Date(), jwt.getExpiresAt()))
                .withClaim("id", userId)
                .withClaim(Constant.ROLE, role)
                .sign(Algorithm.HMAC512(jwt.getIssuer() + userId));
    }

    public boolean expireToken(String token, String userId) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwt.getIssuer() + userId)).build();
        DecodedJWT jwt = verifier.verify(token);
        Date expiresAt = jwt.getExpiresAt();
        return expiresAt.before(new Date());
    }

    public String getAudience(String token) {
        return JWT.decode(token).getAudience().get(0);
    }

    public String getRole(String token) {
        return JWT.decode(token).getSubject();
    }

    public Claim getClaimByName(String token, String name) {
        return JWT.decode(token).getClaim(name);
    }
}
