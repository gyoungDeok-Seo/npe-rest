package com.app.nperest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 URL패턴에 대해 CORS설정을 적용
        registry.addMapping("/**")
                // allowedOriginPatterns 메소드는 지정된 패턴과 일치하는 출처(도메인)에서 오는 요청을 허용
                // "http://*:3000"은 HTTP 프로토콜을 사용하는 모든 도메인의 3000번 포트를 통해 오는 요청을 허용
//                .allowedOriginPatterns("http://*:3000")
                .allowedOriginPatterns("http://*:3000")
                // 모든 HTTP 메소드 (GET, POST, PUT, DELETE 등)의 요청을 허용
                .allowedMethods("*")
                // 요청에서 사용할 수 있는 HTTP 헤더를 지정
                .allowedHeaders("*")
                // 크로스 도메인 요청에서 인증 관련 쿠키 또는 인증 정보를 요청과 함께 보낼 수 있도록 허용
                // 보안상의 이유로, allowedOrigins에 *를 사용할 경우 allowCredentials는 false로 설정해야 하나,
                // 특정 도메인이 명시되어 있거나 패턴이 사용된 경우 true로 설정할 수 있다.
                .allowCredentials(true)
                // 브라우저가 preflight 요청의 결과를 캐싱하는 시간을 초 단위로 설정
                // Preflight 요청은 본 요청을 보내기 전에 서버가 요청을 허용하는지를 확인하기 위해 브라우저가 서버로 보내는 사전 요청
                .maxAge(3600);
    }
}
