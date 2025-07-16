package com.example.ef_buscar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // ← ¡CLAVE! Habilita Feign

public class EfBuscarApplication {

    public static void main(String[] args) {
        SpringApplication.run(EfBuscarApplication.class, args);
    }

}