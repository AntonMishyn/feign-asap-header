package com.example.feignasapheader.config;

import com.atlassian.asap.api.Jwt;
import com.atlassian.asap.api.JwtBuilder;
import com.atlassian.asap.api.SigningAlgorithm;
import com.atlassian.asap.api.client.http.AuthorizationHeaderGenerator;
import com.atlassian.asap.core.client.http.AuthorizationHeaderGeneratorImpl;
import com.example.feignasapheader.client.interceptors.HelloInterceptorImpl;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.net.URI;

public class HelloClientConfig {

    @Value("${asap.client.hello.keyId}")
    private String keyId;
    @Value("${asap.client.hello.audience}")
    private String audience;
    @Value("${asap.client.hello.issuer}")
    private String issuer;
    @Value("${asap.client.hello.privateKey}")
    private String privateKeyRepoUrl;

    @Bean
    RequestInterceptor requestInterceptor() {
        AuthorizationHeaderGenerator authorizationHeaderGenerator =
                AuthorizationHeaderGeneratorImpl.createDefault(URI.create(privateKeyRepoUrl));
        Jwt jwtPrototype = JwtBuilder.newJwt()
                .keyId(keyId)
                .algorithm(SigningAlgorithm.RS256)
                .audience(audience)
                .issuer(issuer)
                .build();

        return new HelloInterceptorImpl(authorizationHeaderGenerator, jwtPrototype);
    }

    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

}
