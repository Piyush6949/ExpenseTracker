package org.expensetracker.authentication.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.expensetracker.authentication.service.CustomUserDetailService;
import org.expensetracker.authentication.util.CookieUtil;
import org.expensetracker.authentication.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = CookieUtil.getCookieValue(request, "access_token");

        if (accessToken != null) {
            try {

                Claims claims = jwtUtil.getClaims(accessToken);
                String username = claims.getSubject();

                UserDetails userDetails =
                        customUserDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,null,null
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (ExpiredJwtException e) {
                // Token expired
            } catch (SignatureException e) {
                // Invalid signature
            } catch (MalformedJwtException e) {
                // Invalid JWT format
            } catch (UnsupportedJwtException e) {
                // Unsupported JWT
            } catch (IllegalArgumentException e) {
                // Token is null or empty
            }
        }

        filterChain.doFilter(request, response);
    }
}
