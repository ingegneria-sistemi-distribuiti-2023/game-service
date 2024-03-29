package com.isd.game.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiPathFilter extends OncePerRequestFilter {
    @Value("${app.service.secret}")
    private String SECRET_APP;
    private final static String SECRET_HEADER = "Secret-Key";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiPathFilter.class);


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.startsWith("/app/public") || path.contains("swagger") || path.equals("/v3/api-docs")  || path.equals("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }

        String secretFromApp = request.getHeader(SECRET_HEADER);

        if (!SECRET_APP.equals(secretFromApp)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);

    }
}
