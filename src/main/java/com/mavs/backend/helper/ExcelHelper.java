package com.mavs.backend.helper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mavs.backend.daos.product.ProductDao;
import com.mavs.backend.entities.product.FreeItem;
import com.mavs.backend.entities.product.Product;

import java.awt.image.BufferedImage;

@Component
public class ExcelHelper{

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public ProductDao productDao;

    public static boolean checkFileType(MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            return true;
        return false;
    }

    public ResponseEntity<?> addExcelProducts(InputStream inputStream){
        try {
            String message="";
            DataFormatter formatter = new DataFormatter();
            PictureData pict;
            byte[] data;
            String value;
            URL imageUrl;
            String fileName;
            MultipartFile multipartFile;
            BufferedImage image;
            ByteArrayOutputStream byteArrayOutputStream;

            XSSFWorkbook workbook =  new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Products");
            int rowNumber=0;

            Iterator<Row> iterator = sheet.iterator();
            FreeItem freeItem=null;
            while(iterator.hasNext()){
                Row row = iterator.next();
                if(rowNumber<1){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid=0;
                Product product = new Product();
                String destinationFile = "sample.jpg";
                boolean flag = true;

                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch(cid){
                        case 0:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(value.trim().equals("-")){
                                    product=null;
                                    break;
                                }
                                product.setModelNumber(value);
                            } catch (Exception e) {
                                System.out.println("Model Number:"+formatter.formatCellValue(cell));
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 1:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null || value.trim().equals("-")){
                                    product.setProductName("");
                                    break;
                                } 
                                product.setProductName(value);
                            } catch (Exception e) {
                                System.out.println("Product Name:"+product.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 2:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null || value.trim().equals("-")) {
                                    product.setProductHighlights("");
                                    break;
                                }product.setProductHighlights(value);
                            } catch (Exception e) {
                                System.out.println("Product Highlights:"+product.getModelNumber());
                                flag=false;
                                // e.printStackTrace();
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                            }
                        break;
                        case 3:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null || value.trim().equals("-")) break;
                                product.setProductImage1(value);
                                // imageUrl = new URL(value);
                                // image = ImageIO.read(imageUrl);
                                // byteArrayOutputStream = new ByteArrayOutputStream();
                                // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                                // fileName = "sample.jpeg";
                                // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                                // product.setProductImage1(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                            } catch (Exception e) {
                                System.out.println("Product Image 1:"+product.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 4:
                        try {
                            value = formatter.formatCellValue(cell);
                            if(product==null || value.trim().equals("-")) break;
                            product.setProductImage2(value);
                            // imageUrl = new URL(value);
                            // image = ImageIO.read(imageUrl);
                            // byteArrayOutputStream = new ByteArrayOutputStream();
                            // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                            // fileName = "sample.jpeg";
                            // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                            // productDetail.setProductImage1(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                        } catch (Exception e) {
                            System.out.println("Product Image 2:"+product.getModelNumber());
                            // e.printStackTrace();
                            flag = false;
                        }
                        break;
                        case 5:
                        try {
                            value = formatter.formatCellValue(cell);
                            if(product==null || value.trim().equals("-")) break;
                            product.setProductImage3(value);
                            // imageUrl = new URL(value);
                            // image = ImageIO.read(imageUrl);
                            // byteArrayOutputStream = new ByteArrayOutputStream();
                            // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                            // fileName = "sample.jpeg";
                            // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                            // productDetail.setProductImage1(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                        } catch (Exception e) {
                            System.out.println("Product Image 3:"+product.getModelNumber());
                            // e.printStackTrace();
                            flag = false;
                        }
                        break;
                        case 6:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null || value.trim().equals("-")) break;
                                product.setProductVideoLink(value);
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("Product Video Link:"+product.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 7:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null || value.trim().equals("-")) break;
                                product.setProductPrice(value);
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("Product Price:"+product.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 8:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null || value.trim().equals("-")) break;
                                product.setProductCategory(value);
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("Product Category:"+product.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 9:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null || value.trim().equals("-")) break;
                                product.setIndex(value);
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("Index:"+product.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        default:
                        break;
                    }
                    cid++;
                }

                try {
                    if(flag || product!=null || !product.getModelNumber().equals(""))
                        productDao.save(product);
                    else{
                        System.out.println("Product Details not saved"+product.getModelNumber());
                        message+=product.getModelNumber()+",";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                rowNumber++;
            }
            responseMessage.setMessage("Products saved successfully\nNot Saved for:"+message);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}
