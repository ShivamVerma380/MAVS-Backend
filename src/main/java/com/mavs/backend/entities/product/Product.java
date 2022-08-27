package com.mavs.backend.entities.product;

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
        
        private String productImage4;
        
        private String productImage5;
        
        private String productPrice;
        
        private String productVideoLink;
        
        private String productCategory;

        private String offerPrice;

        public Product() {
        }

        public Product(String modelNumber, String productId, String productName, String productHighlights,
                String productImage1, String productImage2, String productImage3, String productImage4,
                String productImage5, String productPrice, String productVideoLink, String productCategory,
                String offerPrice) {
            this.modelNumber = modelNumber;
            this.productId = productId;
            this.productName = productName;
            this.productHighlights = productHighlights;
            this.productImage1 = productImage1;
            this.productImage2 = productImage2;
            this.productImage3 = productImage3;
            this.productImage4 = productImage4;
            this.productImage5 = productImage5;
            this.productPrice = productPrice;
            this.productVideoLink = productVideoLink;
            this.productCategory = productCategory;
            this.offerPrice = offerPrice;
        }

        public String getModelNumber() {
            return modelNumber;
        }

        public void setModelNumber(String modelNumber) {
            this.modelNumber = modelNumber;
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

        public String getProductImage4() {
            return productImage4;
        }

        public void setProductImage4(String productImage4) {
            this.productImage4 = productImage4;
        }

        public String getProductImage5() {
            return productImage5;
        }

        public void setProductImage5(String productImage5) {
            this.productImage5 = productImage5;
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

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        @Override
        public String toString() {
            return "Product [modelNumber=" + modelNumber + ", offerPrice=" + offerPrice + ", productCategory="
                    + productCategory + ", productHighlights=" + productHighlights + ", productId=" + productId
                    + ", productImage1=" + productImage1 + ", productImage2=" + productImage2 + ", productImage3="
                    + productImage3 + ", productImage4=" + productImage4 + ", productImage5=" + productImage5
                    + ", productName=" + productName + ", productPrice=" + productPrice + ", productVideoLink="
                    + productVideoLink + "]";
        }
}
