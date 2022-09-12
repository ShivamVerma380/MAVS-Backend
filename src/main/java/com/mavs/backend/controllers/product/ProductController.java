package com.mavs.backend.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mavs.backend.helper.ResponseMessage;
import com.mavs.backend.services.product.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    public ProductService productService;

    @Autowired
    public ResponseMessage responseMessage;
    
    @PostMapping("/add-product")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> addProduct(@RequestParam("modelNumber") String modelNumber,@RequestParam("productName") String productName,@RequestParam("productHighlights") String productHighlights,@RequestParam("productPrice") String productPrice,@RequestParam("offerPrice") String offerPrice,@RequestParam("productImage1") String productImage1,
    @RequestParam("productImage2")String productImage2,@RequestParam("productImage3") String productImage3,@RequestParam("productImage4") String productImage4,@RequestParam("productImage5") String productImage5){
        try{
            return productService.addProductDetail(modelNumber, productName, productHighlights, productPrice, offerPrice, productImage1, productImage2, productImage3, productImage4, productImage5);
        }
        catch(Exception e){
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

}
