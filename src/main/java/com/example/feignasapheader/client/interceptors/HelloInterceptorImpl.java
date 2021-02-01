package com.example.feignasapheader.client.interceptors;

import com.atlassian.asap.api.Jwt;
import com.atlassian.asap.api.JwtBuilder;
import com.atlassian.asap.api.client.http.AuthorizationHeaderGenerator;
import com.atlassian.asap.api.exception.CannotRetrieveKeyException;
import com.atlassian.asap.api.exception.InvalidTokenException;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Objects;

public class HelloInterceptorImpl implements RequestInterceptor {
    private final String AUTHORIZATION_HEADER = "Authorization";

    private final AuthorizationHeaderGenerator authorizationHeaderGenerator;
    private final Jwt jwtPrototype;

    public HelloInterceptorImpl(AuthorizationHeaderGenerator authorizationHeaderGenerator, Jwt jwtPrototype) {
        this.authorizationHeaderGenerator = Objects.requireNonNull(authorizationHeaderGenerator);
        this.jwtPrototype = Objects.requireNonNull(jwtPrototype);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Jwt jwt = JwtBuilder.newFromPrototype(jwtPrototype).build();
        try {
            requestTemplate.header(AUTHORIZATION_HEADER, authorizationHeaderGenerator.generateAuthorizationHeader(jwt));
        } catch (InvalidTokenException e) {
            throw new RuntimeException("Invalid prototype token", e);
        } catch (CannotRetrieveKeyException e) {
            e.printStackTrace();
        }
    }
}
