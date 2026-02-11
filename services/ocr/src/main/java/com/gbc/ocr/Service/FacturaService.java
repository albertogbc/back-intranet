package com.gbc.ocr.Service;

import com.gbc.ocr.DTO.Factura;
import com.gbc.ocr.Utils.Converter;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FacturaService {
    private Converter converter;

    public FacturaService(Converter converter) {
        this.converter = converter;
    }

    public Factura obtenerFolio(String texto) {

        Pattern patternFolio = Pattern.compile("folio:\\s*(\\d{4})");
        Matcher matcher = patternFolio.matcher(texto.toLowerCase());
        Pattern patternSerie = Pattern.compile("serie:\\s*(\\w{4})");
        Matcher matcherSerie = patternSerie.matcher(texto.toLowerCase());
        Pattern patternFecha = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
        Matcher matcherFecha = patternFecha.matcher(texto.toLowerCase());

        if (matcher.find() && matcherSerie.find() && matcherFecha.find()) {
            Factura factura = new Factura();
            factura.setFolio(matcher.group(1));
            factura.setSerie(matcherSerie.group(1));
            System.out.println(matcherFecha.group(1));
            factura.setFecha(matcherFecha.group(0));
            return factura;
        } else {
            return null;
        }
    }
}
