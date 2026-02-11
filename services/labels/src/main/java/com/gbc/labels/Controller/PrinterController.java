package com.gbc.labels.Controller;

import com.gbc.labels.Service.PrinterService;
import com.gbc.labels.dto.EpaLabelRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PrinterController {
    private PrinterService printerService;

    public PrinterController(PrinterService printerService) {
        this.printerService = printerService;
    }

    @PostMapping("/imprimir")
    public String imprimir(@RequestBody EpaLabelRequest epaLabelRequest) throws IOException {
        printerService.imprimir(epaLabelRequest);
        String mensaje = "Ha impreso con exito";
        return mensaje;
    }
}
