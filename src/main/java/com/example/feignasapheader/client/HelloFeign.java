package com.example.feignasapheader.client;

import com.example.feignasapheader.config.HelloClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.ConnectException;

@FeignClient(name = "helloClient", configuration = HelloClientConfig.class, url = "${asap.client.hello.resource.server.url}")
public interface HelloFeign {

    @GetMapping
    String getHello() throws ConnectException;
}
