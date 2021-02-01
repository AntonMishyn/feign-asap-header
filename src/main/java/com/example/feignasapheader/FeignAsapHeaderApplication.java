package com.example.feignasapheader;

import com.example.feignasapheader.client.HelloFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.ConnectException;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
@Slf4j
public class FeignAsapHeaderApplication implements CommandLineRunner {

    private final HelloFeign helloFeign;

    public static void main(String[] args) {
        SpringApplication.run(FeignAsapHeaderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String responseBody = null;
        try {
            responseBody = helloFeign.getHello();
        } catch (FeignException | ConnectException e) {
        }
        log.info(responseBody);
    }
}
