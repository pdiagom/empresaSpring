package com.example.empresaspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/empresa")
public class PrincipalController {

    @GetMapping
    public Map<String, String> home() {
        return Map.of(
                "empleados", "/empleados",
                "nominas", "/nominas"
        );
    }
}
