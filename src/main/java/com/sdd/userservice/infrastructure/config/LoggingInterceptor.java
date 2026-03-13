package com.sdd.userservice.infrastructure.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    private static final String START_TIME_ATTR = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(START_TIME_ATTR, System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute(START_TIME_ATTR);
        long executionTime = System.currentTimeMillis() - startTime;

        String requestBody = extractRequestBody(request);
        String responseBody = extractResponseBody(response);

        log.info("[{}] {} | status={} | requestBody={} | responseBody={} | time={}ms",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                requestBody.isBlank() ? "(empty)" : requestBody,
                responseBody.isBlank() ? "(empty)" : responseBody,
                executionTime);
    }

    private String extractRequestBody(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper wrapper) {
            byte[] bytes = wrapper.getContentAsByteArray();
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return "";
    }

    private String extractResponseBody(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper wrapper) {
            byte[] bytes = wrapper.getContentAsByteArray();
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return "";
    }
}
