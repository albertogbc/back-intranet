package com.gbc.ocr.DTO;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;
    private boolean isManager;

    public Employee(String name, int age, String department, double salary, boolean isManager) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
        this.isManager = isManager;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public boolean isManager() { return isManager; }
}