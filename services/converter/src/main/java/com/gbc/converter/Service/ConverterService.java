package com.gbc.converter.Service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@Service
public class ConverterService {
    byte[] bytes;
    public byte[] generarExcel(String datos) {
        ObjectMapper om = new ObjectMapper();

        try {
            System.out.println(datos);
            String data = om.writeValueAsString(datos);
            JsonNode node = om.readTree(datos);
            JsonNode header = node.get("header");
            Iterator<JsonNode> it = header.iterator();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(" Employee Details");
            Row row = sheet.createRow(0);
            int colNum = 0;
            while(it.hasNext()) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(it.next().asText());
            }
            JsonNode body = node.get("body");
            int rowNum = 1;
            colNum = 0;
            int i = 0;
            JsonNode rowNode;
            while(i < body.size()) {
                rowNode = body.get(i++);
                Row bodyRow = sheet.createRow(rowNum++);
                Cell nameCell = bodyRow.createCell(colNum++);
                Cell ageCell = bodyRow.createCell(colNum++);
                Cell depCell = bodyRow.createCell(colNum++);
                Cell salCell = bodyRow.createCell(colNum++);
                Cell manCell = bodyRow.createCell(colNum++);
                nameCell.setCellValue(rowNode.get("name").asText());
                ageCell.setCellValue(rowNode.get("age").asInt());
                depCell.setCellValue(rowNode.get("department").asText());
                salCell.setCellValue(rowNode.get("salary").asInt());
                manCell.setCellValue(rowNode.get("manager").asBoolean());
                colNum = 0;
            }

            //FileOutputStream outputStream = new FileOutputStream("C:/Users/alberto.alvarez/Documents/output.xlsx");
            //wb.write(outputStream);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                wb.write(bos);
            } finally {
                bos.close();
            }
            bytes = bos.toByteArray();


            System.out.println(" Excel file generated");

        } catch (JsonProcessingException e1) {
            e1.printStackTrace();

        } catch (IOException e1) {
            e1.printStackTrace();

        }
        return bytes;
    }

}
