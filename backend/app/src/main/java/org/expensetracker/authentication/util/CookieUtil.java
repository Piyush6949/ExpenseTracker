package org.expensetracker.authentication.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;

@NoArgsConstructor
public class CookieUtil {

    public static void addAccessTokenCookie(HttpServletResponse httpServletResponse, String token){

        ResponseCookie responseCookie = ResponseCookie.from("access_token",token)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge((60*60*24))
                .build();

        httpServletResponse.addHeader("Set-Cookie", responseCookie.toString());

    }

    public static String getCookieValue(HttpServletRequest httpServletRequest, String name) {
        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }

}
