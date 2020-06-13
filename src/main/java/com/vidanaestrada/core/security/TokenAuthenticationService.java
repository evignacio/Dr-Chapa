package com.vidanaestrada.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    private static final Long expirationTime =  860_000_000L;
    private static final String secretWord = "klapaucius";
    private static final String tokenPrefix = "Bearer";
    private static final String  headerString = "Authorization";


    static void setTokenInHttpServeletResponse(HttpServletResponse httpServletResponse, AccountCredential accountCredential) throws IOException, JSONException {
        String jwt = getTokenJwt(accountCredential.getId());

        JSONObject jsonContentResponse = new JSONObject();
        jsonContentResponse.put("accessToken",jwt);

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf8");
        httpServletResponse.getWriter().write(String.valueOf(jsonContentResponse));
    }

    private static String getTokenJwt(Long subject) {

        return Jwts.builder()
                .setSubject(subject.toString())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretWord)
                .compact();
    }

    static Authentication authenticationFilter(HttpServletRequest httpServletRequest) {

        String tokenJwt = httpServletRequest.getHeader(headerString);

        if(tokenJwt != null) {

            Long subject =  Long.parseLong(Jwts.parser()
                    .setSigningKey(secretWord)
                    .parseClaimsJws(tokenJwt.replace(tokenPrefix, ""))
                    .getBody()
                    .getSubject()
            );

            if (!StringUtils.isEmpty(subject)) {
                httpServletRequest.setAttribute("truckerId", subject);
                return new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
            }

        }
        return null;
    }
}
