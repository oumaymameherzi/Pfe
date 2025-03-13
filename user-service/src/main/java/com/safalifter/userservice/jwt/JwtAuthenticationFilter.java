package com.safalifter.userservice.jwt;

import com.safalifter.userservice.jwt.JwtUtil;
import com.safalifter.userservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                log.info("JWT Token: {}", token); // Log the token

                Claims claims = jwtUtil.getClaims(token); // Use JwtUtil to extract claims
                log.info("JWT Claims: {}", claims); // Log the claims

                String username = claims.getSubject();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("User authenticated: {}", username); // Log successful authentication
                }
            }
        } catch (ExpiredJwtException ex) {
            log.error("JWT expired", ex);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT expired");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT", ex);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported JWT");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT", ex);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
        } catch (Exception ex) {
            log.error("Error during JWT authentication", ex);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error during JWT authentication");
        }

        filterChain.doFilter(request, response);
    }
}