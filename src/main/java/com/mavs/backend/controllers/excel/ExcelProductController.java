package com.mavs.backend.controllers.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mavs.backend.helper.ExcelHelper;
import com.mavs.backend.helper.ResponseMessage;

@RestController
@Component
public class ExcelProductController {

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public ExcelHelper excelHelper;

    @PostMapping("/excel/products")
    public ResponseEntity<?> addProducts(@RequestParam("file") MultipartFile file){
        try {
            return excelHelper.addProducts(file.getInputStream());
        } catch (Exception e) {
            // e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}
