package com.example.myportfolio.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        logger.warn("Filtering request....");
        
        String requestHeader = request.getHeader("Authorization");

        // Check if the Authorization header contains a valid Bearer token
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String token = requestHeader.substring(7);
            String username = jwtHelper.getUsernameFromToken(token);
            
            String path = request.getRequestURI();
            
            if (path.equals("/projects/all")) {
            	filterChain.doFilter(request, response);
            	return;
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.info("JWT token found for user: " + username);
                
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (!jwtHelper.isTokenExpired(token)) {
                    logger.info("JWT token is valid for user: " + username);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    logger.warn("JWT token is expired for user: " + username);
                }
            }
        } else {
            logger.warn("JWT token is missing or invalid in the request header.");
        }

        filterChain.doFilter(request, response);
    }
}
