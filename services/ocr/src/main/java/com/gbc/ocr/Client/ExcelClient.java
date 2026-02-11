package com.gbc.ocr.Client;

import com.gbc.ocr.DTO.EmployeeData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="converter-service", url = "http://localhost:8098/api/v1/converter")
public interface ExcelClient {
    @RequestMapping(method = RequestMethod.POST, value = "/excel")
    byte[] generateExcel(EmployeeData employeeData);
}
