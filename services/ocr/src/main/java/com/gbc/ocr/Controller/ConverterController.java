package com.gbc.ocr.Controller;

import com.gbc.ocr.DTO.Employee;
import com.gbc.ocr.DTO.EmployeeData;
import com.gbc.ocr.DTO.Factura;
import com.gbc.ocr.Service.EmployeeService;
import com.gbc.ocr.Service.FacturaService;
import com.gbc.ocr.Utils.Converter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ocr")
public class ConverterController {
    private final EmployeeService employeeService;
    private Converter converter;
    private FacturaService facturaService;

    public ConverterController(Converter converter, EmployeeService employeeService, FacturaService facturaService) {
        this.converter = converter;
        this.employeeService = employeeService;
        this.facturaService = facturaService;
    }

    @PostMapping(value = "/cargaarchivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void postMethodName(@RequestParam("files") List<MultipartFile> files) throws IOException {
        files.parallelStream().forEach(f -> {
            try {
                converter.convertImageToText(f.getOriginalFilename(), f.getBytes());
                System.out.println(f.getOriginalFilename());
            } catch (Exception e) {
                throw new RuntimeException("No se pudo completar la carga del archivo: " + f.getOriginalFilename(), e);
            }
        });
    }

    @GetMapping("/llamarexcel")
    public byte[] llamarexcel() {

        return employeeService.llamarExcel();
    }

    @PostMapping(value = "/cargafactura", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Factura> obtenerDatosFactura(@RequestParam("files") List<MultipartFile> files) throws IOException {
        List<Factura> facturas = new ArrayList<>();

        files.parallelStream().forEach(f -> {
            try {
                String facturaString = converter.convertImageToText(f.getOriginalFilename(), f.getBytes());
                Factura factura = facturaService.obtenerFolio(facturaString);
                synchronized (facturas) {
                    facturas.add(factura);
                }
            } catch (Exception e) {
                throw new RuntimeException("No se pudo completar la carga del archivo: " + f.getOriginalFilename(), e);
            }
        });

        return facturas;
    }
}
