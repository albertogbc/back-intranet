package com.gbc.labels.dto;


import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public class DocumentRequest {


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private MultipartFile file;
}
