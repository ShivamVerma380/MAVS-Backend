package com.mavs.backend.services.product;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mavs.backend.daos.admin.AdminDao;
import com.mavs.backend.daos.product.ProductDao;
import com.mavs.backend.entities.admin.Admin;
import com.mavs.backend.entities.product.Product;
import com.mavs.backend.entities.product.ProductDescription;
import com.mavs.backend.helper.JwtUtil;
import com.mavs.backend.helper.ProductsDetailsResponse;
import com.mavs.backend.helper.ResponseMessage;

@Repository
@Component
public class ProductService {

    @Autowired
    public ProductDao productDao;

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public AdminDao adminDao;

    @Autowired
    public Admin admin;
    
    public ResponseEntity<?> addProductDetail( String modelNumber,String productName, String productHighlights,
            String productPrice,String offerPrice, String productImage1, String productImage2, String productImage3,
            String productImage4, String productImage5,String authorization) {
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can add product");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            Product productDetail = new Product();
            productDetail.setModelNumber(modelNumber);
            productDetail.setProductHighlights(productHighlights);
            productDetail.setProductImage1(productImage1);
            productDetail.setProductImage2(productImage2);
            productDetail.setProductImage3(productImage3);
            productDetail.setProductImage4(productImage4);
            productDetail.setProductImage5(productImage5);
            
            productDetail.setProductPrice(productPrice);
            productDetail.setProductName(productName);
            productDetail.setOfferPrice(offerPrice);
            
            
            productDao.save(productDetail);
            responseMessage.setMessage("Model saved successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);

        }catch(Exception e){
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getProducts(){
        try {
            List<Product> productDetails = productDao.findAll();
            List<ProductsDetailsResponse> productsDetailsResponses = new ArrayList<>();
            for(int i=0;i<productDetails.size();i++){
                ProductsDetailsResponse productsDetailsResponse = new ProductsDetailsResponse();
                productsDetailsResponse.setModelNumber(productDetails.get(i).getModelNumber());
                productsDetailsResponse.setProductName(productDetails.get(i).getProductName());
                productsDetailsResponse.setOfferPrice(productDetails.get(i).getOfferPrice());
                productsDetailsResponse.setProductPrice(productDetails.get(i).getProductPrice());
                productsDetailsResponse.setProductImage1(productDetails.get(i).getProductImage1());
                productsDetailsResponse.setProductHighlights(productDetails.get(i).getProductHighlights());
                
                productsDetailsResponses.add(productsDetailsResponse);
                
            }
            return ResponseEntity.status(HttpStatus.OK).body(productsDetailsResponses);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> addDescription(String authorization,String modelNumber,String title,String description,String image){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admin can add description");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            Product product = productDao.findProductBymodelNumber(modelNumber);
            if(product==null){
                responseMessage.setMessage("Product Not found");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            ArrayList<ProductDescription> list = product.getProductDescriptions();
            if(list==null){
                list = new ArrayList<>();
            }
            ProductDescription productDescription = new ProductDescription(title,description,image);
            list.add(productDescription);
            product.setProductDescriptions(list);
            productDao.save(product);
            responseMessage.setMessage("Product Details updated Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}
