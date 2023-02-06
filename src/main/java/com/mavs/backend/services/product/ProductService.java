package com.mavs.backend.services.product;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mavs.backend.daos.admin.AdminDao;
import com.mavs.backend.daos.product.ProductCategoryDao;
import com.mavs.backend.daos.product.ProductDao;
import com.mavs.backend.entities.admin.Admin;
import com.mavs.backend.entities.product.AdditionalFeatures;
import com.mavs.backend.entities.product.Product;
import com.mavs.backend.entities.product.ProductCategory;
import com.mavs.backend.entities.product.ProductDescription;
import com.mavs.backend.helper.JwtUtil;
import com.mavs.backend.helper.ProductCategoryModelsResponse;
import com.mavs.backend.helper.ProductCategoryResponse;
import com.mavs.backend.helper.ProductsDetailsResponse;
import com.mavs.backend.helper.ResponseMessage;
import com.mavs.backend.services.CustomUserDetailsService;

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

    @Autowired
    public ProductCategoryDao productCategoryDao;
    
    public ResponseEntity<?> addProductDetail( String modelNumber,String productName, String productHighlights,
            String productPrice, String productImage1, String productImage2, String productImage3,String videoLink,String productCategory,ArrayList<String> imgSrc,String authorization) {
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
            productDetail.setProductCategory(productCategory);
            productDetail.setProductVideoLink(videoLink);
            productDetail.setProductPrice(productPrice);
            productDetail.setProductName(productName);
            productDetail.setImgsrc(imgSrc);
            productDetail.setIndex("0");
            
            
            
            productDao.save(productDetail);
            responseMessage.setMessage("Model saved successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);

        }catch(Exception e){
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> deleteAllProducts(String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can delete product");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            List<Product> products = productDao.findAll();
            if(products!=null){
                productDao.deleteAll();
            }
            responseMessage.setMessage("all products deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity deleteProductCategories(String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can delete product");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            List<ProductCategory> productCategories = productCategoryDao.findAll();
            if(productCategories!=null){
                productCategoryDao.deleteAll();
            }

            responseMessage.setMessage("product categories deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            
        } catch (Exception e) {
            // TODO: handle exception
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
                
                productsDetailsResponse.setProductPrice(productDetails.get(i).getProductPrice());
                productsDetailsResponse.setProductImage1(productDetails.get(i).getProductImage1());
                productsDetailsResponse.setProductImage2(productDetails.get(i).getProductImage2());
                productsDetailsResponse.setProductImage3(productDetails.get(i).getProductImage3());
                productsDetailsResponse.setVideoLink(productDetails.get(i).getProductVideoLink());
                productsDetailsResponse.setProductHighlights(productDetails.get(i).getProductHighlights());
                productsDetailsResponse.setProductDescriptions(productDetails.get(i).getProductDescriptions());
                productsDetailsResponse.setImgSrc(productDetails.get(i).getImgsrc());
                productsDetailsResponse.setIndex(productDetails.get(i).getIndex());
                
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

    public ResponseEntity<?> getProductById(String model){
        try {
            Product product = productDao.findProductBymodelNumber(model);
            return ResponseEntity.status(HttpStatus.OK).body(product);
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

    public ResponseEntity<?> addAditionalFeatures(String authorization,String title,String description,String modelNumber){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admin can add additional features");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            Product product = productDao.findProductBymodelNumber(modelNumber);
            if(product==null){
                responseMessage.setMessage("Product Not found");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            ArrayList<AdditionalFeatures> list = product.getAdditionalFeatures();
            if(list==null){
                list = new ArrayList<>();
            }
            AdditionalFeatures additionalFeatures = new AdditionalFeatures(title,description);
            list.add(additionalFeatures);
            product.setAdditionalFeatures(list);
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

    public ResponseEntity<?> addProductCategory(String authorization,String productcategory,List<String> modelNum){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admin can add product category");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            HashSet<String> models = new HashSet<>();
            for(int i=0;i<modelNum.size();i++){
                try {
                    Product product = productDao.findProductBymodelNumber(modelNum.get(i));
                    if(product!=null){
                        models.add(modelNum.get(i));
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                
            }
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductcategory(productcategory);
            productCategory.setModelNum(new ArrayList<>(models));
            productCategoryDao.save(productCategory);

            responseMessage.setMessage("product category added successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            
            

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getProductCategory(){
        try {
            List<ProductCategory> productCategory = productCategoryDao.findAll();
            // List<Product> product = productDao.findAll();
            List<ProductCategoryResponse> productCategoryResponses = new ArrayList<>();
            for(int i=0;i<productCategory.size();i++){
                ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
                productCategoryResponse.setTitle(productCategory.get(i).getProductcategory());
                List<ProductCategoryModelsResponse> models = new ArrayList<>();
                for(int j=0;j<productCategory.get(i).getModelNum().size();j++){
                    Product product = productDao.findProductBymodelNumber(productCategory.get(i).getModelNum().get(j));
                    
                    ProductCategoryModelsResponse model = new ProductCategoryModelsResponse();
                    model.setModelNum(product.getModelNumber());
                    model.setProductname(product.getProductName());
                    model.setProductimg(product.getProductImage1());
                    models.add(model);
                    
                }
                productCategoryResponse.setProducts(models);

                productCategoryResponses.add(productCategoryResponse);
                    
            }

            return ResponseEntity.status(HttpStatus.OK).body(productCategoryResponses);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}
