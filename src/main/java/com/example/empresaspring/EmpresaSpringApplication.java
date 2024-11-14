package com.example.empresaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.empresaspring.*"})
public class EmpresaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpresaSpringApplication.class, args);
    }

}
