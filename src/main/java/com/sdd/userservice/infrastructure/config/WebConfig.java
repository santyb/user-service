package com.sdd.userservice.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor()).addPathPatterns("/**");
    }

    /**
     * Wraps every request/response in caching wrappers so that
     * LoggingInterceptor can read the bodies without consuming the streams.
     */
    @Bean
    public OncePerRequestFilter contentCachingFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain)
                    throws ServletException, IOException {

                ContentCachingRequestWrapper wrappedRequest =
                        new ContentCachingRequestWrapper(request);
                ContentCachingResponseWrapper wrappedResponse =
                        new ContentCachingResponseWrapper(response);

                try {
                    filterChain.doFilter(wrappedRequest, wrappedResponse);
                } finally {
                    // Must copy cached body back to the original response
                    wrappedResponse.copyBodyToResponse();
                }
            }
        };
    }
}
