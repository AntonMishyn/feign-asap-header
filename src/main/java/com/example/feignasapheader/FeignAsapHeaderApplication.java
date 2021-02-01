package com.example.feignasapheader;

import com.example.feignasapheader.client.HelloFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class FeignAsapHeaderApplication implements CommandLineRunner {

    private final HelloFeign helloFeign;

    public static void main(String[] args) {
        SpringApplication.run(FeignAsapHeaderApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String response = helloFeign.getHello();
        System.out.println(response);
    }
}
