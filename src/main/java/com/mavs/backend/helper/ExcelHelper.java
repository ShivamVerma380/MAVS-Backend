package com.mavs.backend.helper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
import com.mavs.backend.daos.solution.SolutionCategoryDao;
import com.mavs.backend.daos.solution.SolutionDao;
import com.mavs.backend.entities.product.AdditionalFeatures;
import com.mavs.backend.entities.product.FreeItem;
import com.mavs.backend.entities.product.Product;
import com.mavs.backend.entities.product.ProductDescription;
import com.mavs.backend.entities.solution.Solution;
import com.mavs.backend.entities.solution.SolutionBenefits;
import com.mavs.backend.entities.solution.SolutionCategory;
import com.mavs.backend.entities.solution.SolutionFeatures;

import lombok.val;

import java.awt.image.BufferedImage;

@Component
public class ExcelHelper{

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public ProductDao productDao;

    @Autowired
    public SolutionDao solutionDao;

    @Autowired
    public SolutionCategoryDao solutionCategoryDao;

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
                        case 10:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null) break;
                                if(value.trim().equals("-")){
                                    break;
                                }
                                String[] productDesc = value.split("#");
                                ArrayList<ProductDescription> list  = new ArrayList<>(); 
                                for(int i=0;i<productDesc.length;i++){
                                    String products[] = productDesc[i].split(";");
                                    ProductDescription productDescription = new ProductDescription(products[0],products[1],products[2]);
                                    list.add(productDescription);
                                }
                                product.setProductDescriptions(list);

                            } catch (Exception e) {
                                // e.printStackTrace();
                                System.out.println("Product Description:"+product.getModelNumber());
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                            }
                        break;
                        case 11:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(product==null) break;
                                if(value.trim().equals("-")){
                                    break;
                                }
                                String[] addtionalFeat = value.split("#");
                                ArrayList<AdditionalFeatures> list  = new ArrayList<>(); 
                                for(int i=0;i<addtionalFeat.length;i++){
                                    String features[] = addtionalFeat[i].split(";");
                                    AdditionalFeatures additionalFeatures = new AdditionalFeatures(features[0], features[1]);
                                    list.add(additionalFeatures);
                                }
                                product.setAdditionalFeatures(list);

                            } catch (Exception e) {
                                // e.printStackTrace();
                                System.out.println("Additional Features:"+product.getModelNumber());
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
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
            responseMessage.setMessage("Products saved successfully"+message);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> addExcelSolutions(InputStream inputStream){
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
            XSSFSheet sheet = workbook.getSheet("Solutions");
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
                Solution solution = new Solution();
                String destinationFile = "sample.jpg";
                boolean flag = true;

                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch(cid){
                        case 0:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(value.trim().equals("-")){
                                    solution=null;
                                    break;
                                }
                                solution.setTitle(value);
                            } catch (Exception e) {
                                System.out.println("title:"+formatter.formatCellValue(cell));
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 1:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solution==null || value.trim().equals("-")){
                                    solution.setDescription("");
                                    break;
                                }
                                solution.setDescription(value);
                            } catch (Exception e) {
                                System.out.println("Solution Description:"+solution.getTitle());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 2:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solution==null || value.trim().equals("-")) {
                                    solution.setCoverimg("");
                                    break;
                                }
                                solution.setCoverimg(value);
                            } catch (Exception e) {
                                System.out.println("cover image:"+solution.getTitle());
                                flag=false;
                                // e.printStackTrace();
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                            }
                        break;
                        case 3:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solution==null || value.trim().equals("-")) break;
                                
                                solution.setSolcategory(value);
                                
                            } catch (Exception e) {
                                System.out.println("category:"+solution.getTitle());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 4:
                        try {
                            value = formatter.formatCellValue(cell);
                            if(solution==null || value.trim().equals("-")) break;
                            solution.setSolimg1(value);
                            
                        } catch (Exception e) {
                            System.out.println("Solution Image 1:"+solution.getTitle());
                            // e.printStackTrace();
                            flag = false;
                        }
                        break;
                        case 5:
                        try {
                            value = formatter.formatCellValue(cell);
                            if(solution==null || value.trim().equals("-")) break;
                            
                            solution.setSolimg2(value);
                        } catch (Exception e) {
                            System.out.println("Solution Image 2:"+solution.getTitle());
                            // e.printStackTrace();
                            flag = false;
                        }
                        break;
                        case 6:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solution==null || value.trim().equals("-")) break;
                                
                                solution.setSolimg3(value);
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("Solution image 3:"+solution.getTitle());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 7:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solution==null) break;
                                if(value.trim().equals("-")){
                                    break;
                                }
                                String[] productsused = value.split(";");

                                HashSet<String> hashSet = new HashSet<>();
                                for(int i=0;i<productsused.length;i++){
                                    try {
                                        Product product = productDao.findProductBymodelNumber(productsused[i]);
                                        if(product!=null){
                                            hashSet.add(productsused[i]);
                                        }
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        e.printStackTrace();
                                    }
                                }
                                solution.setProductused(new ArrayList<>(hashSet));

                            } catch (Exception e) {
                                // e.printStackTrace();
                                System.out.println("Products used:"+solution.getTitle());
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                            }
                        break;
                        case 8:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solution==null) break;
                                if(value.trim().equals("-")){
                                    break;
                                }
                                String[] solutionFeat = value.split("#");
                                ArrayList<SolutionFeatures> list  = new ArrayList<>(); 
                                for(int i=0;i<solutionFeat.length;i++){
                                    String features[] = solutionFeat[i].split(";");
                                    SolutionFeatures solutionFeatures = new SolutionFeatures(features[0], features[1], features[2]);
                                    list.add(solutionFeatures);
                                }
                                solution.setSolutionFeatures(list);

                            } catch (Exception e) {
                                // e.printStackTrace();
                                System.out.println("Solution Features:"+solution.getTitle());
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                            }
                        break;
                        case 9:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solution==null) break;
                                if(value.trim().equals("-")){
                                    break;
                                }
                                String[] solutionBenef = value.split("#");
                                ArrayList<SolutionBenefits> list  = new ArrayList<>(); 
                                for(int i=0;i<solutionBenef.length;i++){
                                    String benefits[] = solutionBenef[i].split(";");
                                    SolutionBenefits solutionBenefits = new SolutionBenefits(benefits[0], benefits[1], benefits[2]);
                                    list.add(solutionBenefits);
                                }
                                solution.setSolutionBenefits(list);

                            } catch (Exception e) {
                                // e.printStackTrace();
                                System.out.println("Solution Benefits:"+solution.getTitle());
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                            }
                        break;
                        
                        default:
                        break;
                    }
                    cid++;
                }

                try {
                    if(flag || solution!=null || !solution.getTitle().equals("")){

                    
                        solutionDao.save(solution);
                    }
                    else{
                        System.out.println("Product Details not saved"+solution.getTitle());
                        message+=solution.getTitle()+",";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                rowNumber++;
            }
            responseMessage.setMessage("Solutions saved successfully:"+message);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> addExcelSolCategory(InputStream inputStream){
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
            XSSFSheet sheet = workbook.getSheet("SolutionCategory");
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
                SolutionCategory solutionCategory = new SolutionCategory();
                String destinationFile = "sample.jpg";
                boolean flag = true;

                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch(cid){
                        case 0:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(value.trim().equals("-")){
                                    solutionCategory=null;
                                    break;
                                }
                                solutionCategory.setCategory(value);
                            } catch (Exception e) {
                                System.out.println("Category Name:"+formatter.formatCellValue(cell));
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 1:
                        try {
                            value = formatter.formatCellValue(cell);
                            if(solutionCategory==null || value.trim().equals("-")){
                                solutionCategory.setCatimg("");
                                break;
                            }
                            solutionCategory.setCatimg(value);
                        } catch (Exception e) {
                            System.out.println("Category Image:"+solutionCategory.getCategory());
                            // e.printStackTrace();
                            flag = false;
                        }
                        break;
                        case 2:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solutionCategory==null || value.trim().equals("-")){
                                    
                                    solutionCategory.setCatdescription("");
                                    break;
                                }
                                solutionCategory.setCatdescription(value);
                            } catch (Exception e) {
                                System.out.println("Category Description:"+solutionCategory.getCategory());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 3:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(solutionCategory==null) break;
                                if(value.trim().equals("-")){
                                    break;
                                }
                                String[] solutions = value.split(";");

                                HashSet<String> hashSet = new HashSet<>();
                                ArrayList<Solution> finalSolutions = new ArrayList<>();
                                for(int i=0;i<solutions.length;i++){
                                    try {
                                        Solution solution = solutionDao.findSolutionByTitle(solutions[i]);
                                        if(solution!=null){
                                            hashSet.add(solutions[i]);
                                            finalSolutions.add(solution);
                                        }
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        e.printStackTrace();
                                    }
                                }
                                solutionCategory.setSolutions(finalSolutions);

                            } catch (Exception e) {
                                // e.printStackTrace();
                                System.out.println("Products used:"+solutionCategory.getCategory());
                                // responseMessage.setMessage(e.getMessage());
                                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                            }
                        break;
                        default:
                        break;
                    }
                    cid++;
                }

                try {
                    if(flag || solutionCategory!=null || !solutionCategory.getCategory().equals("")){

                        solutionCategoryDao.save(solutionCategory);
                    }
                    else{
                        System.out.println("Category Details not saved"+solutionCategory.getCategory());
                        message+=solutionCategory.getCategory()+",";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                rowNumber++;
            }
            responseMessage.setMessage("Solution Categories saved successfully:"+message);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    
}
