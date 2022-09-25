package com.mavs.backend.controllers.solution;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mavs.backend.helper.ResponseMessage;
import com.mavs.backend.services.solution.SolutionService;

@RestController
@CrossOrigin(origins = "*")
public class SolutionController {

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public SolutionService solutionService;

    
    @PostMapping("/add-solution")
    public ResponseEntity<?> addSolution(@RequestHeader("Authorization") String authorization,@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("coverimg") String coverimg,@RequestParam("solcategory") String solcategory,@RequestParam("solimg1") String solimg1,@RequestParam("solimg2") String solimg2,@RequestParam("solimg3") String solimg3,@RequestParam("productsused") List<String> productsused){
        try {
            return solutionService.addSolution(title, description, coverimg,solcategory, solimg1, solimg2, solimg3, productsused, authorization);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);

        }
    }

    @PostMapping("/solutionfeatures/{title}")
    public ResponseEntity<?> addSolutionFeatures(@RequestHeader("Authorization") String authorization,@PathVariable("title") String title,@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("icon") String icon){
        try {
            return solutionService.addSolutionFeatures(name,description,icon,title,authorization);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @PostMapping("/solutionbenefits/{title}")
    public ResponseEntity<?> addSolutionBenefits(@RequestHeader("Authorization") String authorization,@PathVariable("title") String title,@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("icon") String icon){
        try {
            return solutionService.addSolutionBenefits(name, description, icon, title, authorization);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @GetMapping("/solutions")
    public ResponseEntity<?> getAllSolutions(){
        try {
            return solutionService.getAllSolutions();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @PostMapping("/solcategory")
    public ResponseEntity<?> addSolutionCategory(@RequestHeader("Authorization") String authorization,@RequestParam("category") String category,@RequestParam("catimg") String catimg,@RequestParam("catdescription") String catdescription){
        try {
            return solutionService.addSolutionCategory(category, catimg, catdescription, authorization);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @GetMapping("/getsolcategory")
    public ResponseEntity<?> getSolutionCategories(){
        try {
            return solutionService.getSolutionCategories();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @GetMapping("/getsolcategorydetail")
    public ResponseEntity<?> getSolutionCategoriesDetail(){
        try {
            return solutionService.getSolutionCategoryDetails();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}
