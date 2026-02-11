package com.gbc.ocr.Service;

import com.gbc.ocr.Client.ExcelClient;
import com.gbc.ocr.DTO.Employee;
import com.gbc.ocr.DTO.EmployeeData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private ExcelClient excelClient;

    private List<String> header;
    private List<Employee> body;
    private EmployeeData employeeData;

    public EmployeeService(ExcelClient excelClient) {

        header = new ArrayList<>();
        header.add("Name");
        header.add("Age");
        header.add("Department");
        header.add("Salary");
        header.add("IsManager");

        // Inicialización de body
        body = new ArrayList<>();
        body.add(new Employee("David", 32, "Accounting", 5000, true));
        body.add(new Employee("Paul", 52, "Admin", 6000, true));
        body.add(new Employee("Adam", 45, "Accounting", 4000, false));
        body.add(new Employee("Ben", 35, "Engineering", 2000, false));
        body.add(new Employee("Sean", 45, "Accounting", 3000, false));

        // Creación del objeto EmployeeData
        employeeData = new EmployeeData(header, body);
        this.excelClient = excelClient;
    }


    public EmployeeData getEmployeeData() {
        return employeeData;
    }

    public byte[] llamarExcel(){
        return excelClient.generateExcel(getEmployeeData());

    }
}
