package com.gbc.ocr.Utils;

import org.springframework.stereotype.Component;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class Converter {

    public String convertImageToText(String nombre, byte[] input) throws Exception {
        Tesseract tesseract = new Tesseract();


        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");

        File tempFile = File.createTempFile(nombre, ".pdf");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error al crear el archivo temporal", e);
        }

        try {

            String text = tesseract.doOCR(tempFile);
            return text;

        } catch (TesseractException e) {
            e.printStackTrace();

            throw new Exception("Error al procesar la imagen", e);

        }
    }

}

/* finally {

            if (!tempFile.delete()) {
                System.err.println("No se pudo eliminar el archivo temporal: " + tempFile.getAbsolutePath());
            }
        }*/
