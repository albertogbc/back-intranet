package com.gbc.ocr.DTO;

import java.util.List;

public class EmployeeData {
    private List<String> header;
    private List<Employee> body;

    public EmployeeData(List<String> header, List<Employee> body) {
        this.header = header;
        this.body = body;
    }

    // Getters
    public List<String> getHeader() { return header; }
    public List<Employee> getBody() { return body; }
}