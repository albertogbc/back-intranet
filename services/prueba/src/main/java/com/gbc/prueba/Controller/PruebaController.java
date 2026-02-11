package com.gbc.prueba.Controller;

import com.gbc.prueba.Model.Saludo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/prueba")
public class PruebaController {

    @GetMapping("/saludar")
    public Saludo saludar(@RequestHeader Map<String,String> headers) {
        Saludo saludo = Saludo.builder().titulo("hola").descripcion("mundo").build();
        System.out.println("Hola");
        System.out.println("Este es el rol " + headers.get("role"));
        return saludo;
        }
}
