package com.gbc.converter.Controller;

import com.gbc.converter.Service.ConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/converter")
public class ConverterController {
    private ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }
    @PostMapping("/excel")
    public byte[] obtenerExcel(@RequestBody String datos){

        return converterService.generarExcel(datos);
    }
}
