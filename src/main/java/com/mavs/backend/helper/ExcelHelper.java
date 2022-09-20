package com.mavs.backend.helper;

import java.io.InputStream;
import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xslf.model.ParagraphPropertyFetcher.ParaPropFetcher;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.BsonBinary;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
// import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

// import com.brewingjava.mahavir.daos.HomepageComponents.DealsDao;
// import com.brewingjava.mahavir.daos.HomepageComponents.ShopByBrandsDao;
// import com.brewingjava.mahavir.daos.categories.CategoriesToDisplayDao;
// import com.brewingjava.mahavir.daos.offers.OfferPosterDao;
// import com.brewingjava.mahavir.daos.product.FilterCriteriasDao;
// import com.brewingjava.mahavir.entities.HomepageComponents.BrandCategory;
// import com.brewingjava.mahavir.entities.HomepageComponents.BrandOfferPoster;
// import com.brewingjava.mahavir.entities.HomepageComponents.Deals;
// import com.brewingjava.mahavir.entities.HomepageComponents.ShopByBrands;
// import com.brewingjava.mahavir.entities.categories.CategoriesToDisplay;
// import com.brewingjava.mahavir.entities.categories.SubCategories;
// import com.brewingjava.mahavir.entities.categories.SubSubCategories;
// import com.brewingjava.mahavir.entities.offers.OfferPosters;
// import com.brewingjava.mahavir.entities.product.Factors;
// import com.brewingjava.mahavir.entities.product.FilterCriterias;
import com.mavs.backend.entities.product.Product;
import com.mavs.backend.daos.product.ProductDao;
import com.mavs.backend.entities.product.FreeItem;
// import com.brewingjava.mahavir.entities.product.ProductDescription;
// import com.brewingjava.mahavir.entities.product.ProductVariants;

import lombok.val;
import lombok.var;

@Component
public class ExcelHelper {

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

    
    public ResponseEntity<?> addProducts(InputStream inputStream) {
        try {

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
                Product productDetail = new Product();
                
                String destinationFile = "sample.jpg";
                boolean flag = true;
                
                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch(cid){
                        case 0:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(value.trim().equals("-")){
                                    productDetail=null;
                                    break;
                                }
                                productDetail.setProductId(value);
                                
                            } catch (Exception e) {
                                System.out.println("Product Id:"+formatter.formatCellValue(cell));
                                // e.printStackTrace();
                                flag = false;
                                //TODO: handle exception
                            }
                        case 1:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(value.trim().equals("-")){
                                    productDetail=null;
                                    break;
                                }
                                productDetail.setModelNumber(value);
                            } catch (Exception e) {
                                System.out.println("Model Number:"+formatter.formatCellValue(cell));
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 2:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")){
                                    productDetail.setProductName("");
                                    break;
                                } 
                                productDetail.setProductName(value);
                            } catch (Exception e) {
                                System.out.println("Product Name:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 3:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setProductImage1(value);
                                // imageUrl = new URL(value);
                                // image = ImageIO.read(imageUrl);
                                // byteArrayOutputStream = new ByteArrayOutputStream();
                                // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                                // fileName = "sample.jpeg";
                                // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                                // productDetail.setProductImage1(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                            } catch (Exception e) {
                                System.out.println("Product Image 1:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 4:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setProductImage2(value);
                                // imageUrl = new URL(value);
                                // image = ImageIO.read(imageUrl);
                                // byteArrayOutputStream = new ByteArrayOutputStream();
                                // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                                // fileName = "sample.jpeg";
                                // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                                // productDetail.setProductImage2(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                            } catch (Exception e) {
                                System.out.println("Product Image 2:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 5:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setProductImage3(value);
                                // imageUrl = new URL(value);
                                // image = ImageIO.read(imageUrl);
                                // byteArrayOutputStream = new ByteArrayOutputStream();
                                // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                                // fileName = "sample.jpeg";
                                // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                                // productDetail.setProductImage3(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                            } catch (Exception e) {
                                System.out.println("Product Image 3:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 6:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setProductImage4(value);
                                // imageUrl = new URL(value);
                                // image = ImageIO.read(imageUrl);
                                // byteArrayOutputStream = new ByteArrayOutputStream();
                                // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                                // fileName = "sample.jpeg";
                                // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                                // productDetail.setProductImage4(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                            } catch (Exception e) {
                                System.out.println("Product Image 4:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 7:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setProductImage5(value);
                                // imageUrl = new URL(value);
                                // image = ImageIO.read(imageUrl);
                                // byteArrayOutputStream = new ByteArrayOutputStream();
                                // ImageIO.write(image,"jpeg",byteArrayOutputStream);
                                // fileName = "sample.jpeg";
                                // multipartFile = new MockMultipartFile(fileName,fileName,"jpeg",byteArrayOutputStream.toByteArray());
                                // productDetail.setProductImage5(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                            } catch (Exception e) {
                                System.out.println("Product Image 5:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 8:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setProductPrice(value);
                            } catch (Exception e) {
                                System.out.println("Product Price:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 9:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setOfferPrice(value);
                            } catch (Exception e) {
                                System.out.println("Offer Price:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
                        case 10:
                            try {
                                value = formatter.formatCellValue(cell);
                                if(productDetail==null || value.trim().equals("-")) break;
                                productDetail.setProductCategory(value);
                            } catch (Exception e) {
                                System.out.println("Categories:"+productDetail.getModelNumber());
                                // e.printStackTrace();
                                flag = false;
                            }
                        break;
        //                 case 11:
        //                     try {
        //                         value = formatter.formatCellValue(cell);
        //                         if(productDetail==null || value.trim().equals("-")) {
        //                             productDetail.setProductHighlights("");
        //                             break;
        //                         }productDetail.setProductHighlights(value);
        //                     } catch (Exception e) {
        //                         System.out.println("Product Highlights:"+productDetail.getModelNumber());
        //                         flag=false;
                                
        //                     }
        //                 break;
        //                 case 12:
        //                     try {
        //                         value = formatter.formatCellValue(cell);
        //                         if(value.trim().equals("")){
        //                             productDetail.setSubCategoryMap(new HashMap<>());
        //                             break;
        //                         } 
        //                         HashMap<String,String> map = new HashMap<>();
        //                         String arr[]= value.split(",");
        //                         for(int i=0;i<arr.length;i++){
        //                             String subCat[] = arr[i].split(":");
        //                             map.put(subCat[0],subCat[1]);
        //                         }
        //                         productDetail.setSubCategoryMap(map);
        //                     } catch (Exception e) {
        //                         flag = false;
        //                         System.out.println("Sub Categories:"+productDetail.getModelNumber());
        //                         // System.out.println(formatter.formatCellValue(cell));
        //                         // e.printStackTrace();
        //                     }
                            
        //                 break;
        //                 case 13:
        //                     try {
        //                         HashMap<String,HashMap<String,String>> productInfo = new HashMap<>();
        //                         value = formatter.formatCellValue(cell);
        //                         if(value.trim().equals("-")){
        //                             productDetail.setProductInformation(new HashMap<>());
        //                             break;
        //                         }
        //                         String array[] = value.split("#");
        //                         for(int i=0;i<array.length;i++){
        //                             //System.out.println(array[i]);
        //                             String subSplit[] = array[i].split("\\[");
        //                             HashMap<String,String> mp = new HashMap<>();
        //                         // System.out.println(subSplit.length);
        //                             String x = subSplit[1];
        //                             String innermap = x.substring(0,x.length()-1);
        //                             //System.out.println("0:"+subSplit[0]+"\t1:"+innermap);
        //                             String keyValue[] = innermap.split(";");
        //                             for(int j=0;j<keyValue.length;j++){
        //                                 //System.out.println("KeyValue:"+keyValue[j]);
        //                                 String pair[] = keyValue[j].split("=");
        //                                 try {
        //                                     //System.out.println("pair[0]="+pair[0]+"\tpair[1]="+pair[1]);
        //                                     mp.put(pair[0],pair[1]);
        //                                 } catch (Exception e) {
        //                                     // e.printStackTrace();
        //                                     flag = false;
        //                                     System.out.println("Product Information:"+productDetail.getModelNumber());
        //                                 }
        //                             }
        //                             productInfo.put(subSplit[0],mp);
        //                         }
        //                         productDetail.setProductInformation(productInfo);
        //                     } catch (Exception e) {
        //                         flag = false;
        //                         System.out.println("Product Information:"+productDetail.getModelNumber());
        //                         // System.out.println(formatter.formatCellValue(cell));
        //                         // e.printStackTrace();
        //                     }
        //                 break;
        //                 case 14:
        //                     try {
        //                         HashMap<String,String> productVariants = new HashMap<>();
        //                         value = formatter.formatCellValue(cell);
        //                         if(productDetail==null) break;
        //                         if(value.trim().equals("-")){
        //                             productDetail.setVariants(new HashMap<>());
        //                             break;
        //                         }
        //                         String array[] = value.split(";");
        //                         for(int i=0;i<array.length;i++){
        //                             // array[i] = array[i].trim();
        //                             String pair[] = array[i].split("=");
        //                             // System.out.println("pair[0]="+pair[0]+"\tpair[1]="+pair[1]);
        //                             productVariants.put(pair[0],pair[1]);
        //                         }
        //                         System.out.println(productDetail.getModelNumber()+"\t"+productVariants);
        //                         // productDetail.setVariants(productVariants);
        //                         productDetail.setVariants(productVariants);
        //                     } catch (Exception e) {
        //                         flag = false;
        //                         System.out.println("Product Variants:"+productDetail.getModelNumber());
        //                         // System.out.println(formatter.formatCellValue(cell));
        //                         // e.printStackTrace();
        //                     }
        //                 break;
        //                 case 15:
        //                     try {
        //                         value = formatter.formatCellValue(cell);
        //                         if(productDetail==null || value.trim().equals("-")) break;
        //                         String array[] = value.split(";");
        //                         ArrayList<String> defaultVariants = new ArrayList<>();
        //                         for(int i=0;i<array.length;i++){
        //                             defaultVariants.add(array[i].trim());
        //                         }
        //                         productDetail.setDefaultVariant(defaultVariants);
        //                     } catch (Exception e) {
        //                         flag = false;
        //                         System.out.println("Default variants:"+productDetail.getModelNumber());
        //                     }
        //                 break;
        //                 case 16:
        //                     try {
        //                         value = formatter.formatCellValue(cell);
        //                         if(productDetail==null) break;
        //                         if(value.trim().equals("-")){
        //                             productDetail.setVariantTypes(new HashMap<>());
        //                             break;
        //                         }
        //                         HashMap<String,ArrayList<String>> variantTypes = new HashMap<>();
        //                         String array[] = value.split("#");
        //                         for(int i=0;i<array.length;i++){
        //                             String pair[] = array[i].split("\\[");
        //                             pair[1] = pair[1].substring(0,pair[1].length()-1);
        //                             String values[] = pair[1].split(";");
        //                             ArrayList<String> list = new ArrayList<>();
        //                             for(int j=0;j<values.length;j++){
        //                                 list.add(values[j]);
        //                             }
        //                             System.out.println("pair[0]="+pair[0]+"\tpair[1]="+pair[1]);
        //                             variantTypes.put(pair[0],list);
        //                         }
        //                         productDetail.setVariantTypes(variantTypes);

        //                     } catch (Exception e) {
        //                         flag = false;
        //                         System.out.println("Product Variant Types:"+productDetail.getModelNumber());
        //                     }
        //                 break;
        //                 case 17:
        //                     try {
        //                         value = formatter.formatCellValue(cell);
        //                         if(value.trim().equals("-")){    
        //                             // System.out.println("In if free item"); 
        //                             freeItem = null; 
        //                             break;
        //                         }
        //                         freeItem = new FreeItem();
        //                         String arrays[] = value.split(";");
        //                         freeItem.setName(arrays[0]);
        //                         freeItem.setPrice(arrays[1]);
        //                         imageUrl = new URL(arrays[2]);
        //                         image = ImageIO.read(imageUrl);
        //                         byteArrayOutputStream = new ByteArrayOutputStream();
        //                         ImageIO.write(image,"jpg",byteArrayOutputStream);
        //                         fileName = "sample.jpg";
    
        //                         multipartFile = new MockMultipartFile(fileName,fileName,"jpg",byteArrayOutputStream.toByteArray());
        //                         freeItem.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
        //                         System.out.println(freeItem.toString());                    
        //                     } catch (Exception e) {
        //                         flag = false;
        //                         System.out.println("Free Item:"+productDetail.getModelNumber());
        //                         // System.out.println(formatter.formatCellValue(cell));
        //                         // e.printStackTrace();
        //                     }
        //                 break;
        //                 case 18:
        //                     try {
        //                         value = formatter.formatCellValue(cell);
        //                         if(productDetail==null) break;
        //                         if(value.trim().equals("")){
        //                             productDetail.setFiltercriterias(new HashMap<>());
        //                             break;
        //                         }
        //                         HashMap<String,String> filterCriterias = new HashMap<>();
        //                         String keyValue[] = value.split(";");
        //                         for(int i=0;i<keyValue.length;i++){
        //                             String pair[] = keyValue[i].split("=");
        //                             filterCriterias.put(pair[0].trim(),pair[1].trim());
        //                         }
        //                         productDetail.setFiltercriterias(filterCriterias);
                                
        //                     } catch (Exception e) {
        //                         flag = false;
        //                         System.out.println("Filter Criterias:"+productDetail.getModelNumber());
        //                         // System.out.println(formatter.formatCellValue(cell));
        //                         // e.printStackTrace();
        //                     }   
        //                 break;
        //                 case 19:
        //                     try {
        //                         value = formatter.formatCellValue(cell);
        //                         if(productDetail==null) break;
        //                         if(value.trim().equals("-")){
        //                             break;
        //                         }
        //                         String[] productDesc = value.split("#");
        //                         ArrayList<ProductDescription> list  = new ArrayList<>(); 
        //                         for(int i=0;i<productDesc.length;i++){
        //                             String product[] = productDesc[i].split(";");
        //                             ProductDescription productDescription = new ProductDescription(product[0],product[1],product[2]);
        //                             list.add(productDescription);
        //                         }
        //                         productDetail.setProductDescriptions(list);

        //                     } catch (Exception e) {
        //                         // e.printStackTrace();
        //                         System.out.println("Product Description:"+productDetail.getModelNumber());
        //                         // responseMessage.setMessage(e.getMessage());
        //                         // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        //                     }

                        default:
                        break;
                    }
                    cid++;
                }
                try {
                    if(flag || productDetail!=null || !productDetail.getModelNumber().equals(""))
                        productDao.save(productDetail);
                    else
                        System.out.println("Product Details not saved"+productDetail.getModelNumber());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                rowNumber++;
            }
            responseMessage.setMessage("Products saved successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}
