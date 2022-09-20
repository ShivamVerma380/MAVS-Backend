package com.mavs.backend.entities.product;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "products")
@Component
public class Product {
        @Id
        private String modelNumber;

        private String productId;
        
        private String productName;

        private String productHighlights;
        
        private String productImage1;
        
        private String productImage2;
        
        private String productImage3;
        
        private String productPrice;
        
        private String productVideoLink;
        
        private String productCategory;

        private ArrayList<ProductDescription> productDescriptions;

        private ArrayList<AdditionalFeatures> additionalFeatures;

        

        public Product() {
        }

        public Product(String modelNumber, String productId, String productName, String productHighlights,
                String productImage1, String productImage2, String productImage3, String productPrice, String productVideoLink, String productCategory,
                ArrayList<ProductDescription> productDescriptions,ArrayList<AdditionalFeatures> additionalFeatures) {
            this.modelNumber = modelNumber;
            this.productId = productId;
            this.productName = productName;
            this.productHighlights = productHighlights;
            this.productImage1 = productImage1;
            this.productImage2 = productImage2;
            this.productImage3 = productImage3;
            this.productPrice = productPrice;
            this.productVideoLink = productVideoLink;
            this.productCategory = productCategory;
            this.productDescriptions = productDescriptions;
            this.additionalFeatures = additionalFeatures;
        }

        public String getModelNumber() {
            return modelNumber;
        }

        public ArrayList<AdditionalFeatures> getAdditionalFeatures() {
            return additionalFeatures;
        }

        public void setAdditionalFeatures(ArrayList<AdditionalFeatures> additionalFeatures) {
            this.additionalFeatures = additionalFeatures;
        }

        public void setModelNumber(String modelNumber) {
            this.modelNumber = modelNumber;
        }

        public ArrayList<ProductDescription> getProductDescriptions() {
            return productDescriptions;
        }


        public void setProductDescriptions(ArrayList<ProductDescription> productDescriptions) {
            this.productDescriptions = productDescriptions;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductHighlights() {
            return productHighlights;
        }

        public void setProductHighlights(String productHighlights) {
            this.productHighlights = productHighlights;
        }

        public String getProductImage1() {
            return productImage1;
        }

        public void setProductImage1(String productImage1) {
            this.productImage1 = productImage1;
        }

        public String getProductImage2() {
            return productImage2;
        }

        public void setProductImage2(String productImage2) {
            this.productImage2 = productImage2;
        }

        public String getProductImage3() {
            return productImage3;
        }

        public void setProductImage3(String productImage3) {
            this.productImage3 = productImage3;
        }


        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductVideoLink() {
            return productVideoLink;
        }

        public void setProductVideoLink(String productVideoLink) {
            this.productVideoLink = productVideoLink;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(String productCategory) {
            this.productCategory = productCategory;
        }

        @Override
        public String toString() {
            return "Product [additionalFeatures=" + additionalFeatures + ", modelNumber=" + modelNumber
                    + ", productCategory=" + productCategory + ", productDescriptions=" + productDescriptions
                    + ", productHighlights=" + productHighlights + ", productId=" + productId + ", productImage1="
                    + productImage1 + ", productImage2=" + productImage2 + ", productImage3=" + productImage3
                    + ", productName=" + productName + ", productPrice=" + productPrice + ", productVideoLink="
                    + productVideoLink + "]";
        }


        
}
