package com.mavs.backend.services.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mavs.backend.daos.admin.AdminDao;
import com.mavs.backend.daos.product.ProductDao;
import com.mavs.backend.daos.solution.SolutionCategoryDao;
import com.mavs.backend.daos.solution.SolutionDao;
import com.mavs.backend.entities.admin.Admin;
import com.mavs.backend.entities.product.Product;
import com.mavs.backend.entities.solution.Solution;
import com.mavs.backend.entities.solution.SolutionBenefits;
import com.mavs.backend.entities.solution.SolutionCategory;
import com.mavs.backend.entities.solution.SolutionFeatures;
import com.mavs.backend.helper.JwtUtil;
import com.mavs.backend.helper.ResponseMessage;
import com.mavs.backend.helper.SolCategoryResponse;

@Component
public class SolutionService {

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public AdminDao adminDao;

    @Autowired
    public Admin admin;

    @Autowired
    public SolutionDao solutionDao;

    @Autowired
    public ProductDao productDao;

    @Autowired
    public SolutionCategoryDao solutionCategoryDao;

    // first upload solutions then categories
    public ResponseEntity<?> addSolution(String title,String description,String coverimg,String solcategory,String solimg1,String solimg2,String solimg3,List<String> productsused,String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can add product");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            Solution solution = new Solution();
            solution.setTitle(title);
            solution.setDescription(description);
            solution.setCoverimg(coverimg);
            solution.setSolcategory(solcategory);
            solution.setSolimg1(solimg1);
            solution.setSolimg2(solimg2);
            solution.setSolimg3(solimg3);
            HashSet<String> hashSet = new HashSet<>();
            for(int i=0;i<productsused.size();i++){
                try {
                    Product product = productDao.findProductBymodelNumber(productsused.get(i));
                    if(product!=null){
                        hashSet.add(productsused.get(i));
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            solution.setProductused(new ArrayList<>(hashSet));
            solutionDao.save(solution);


            responseMessage.setMessage("Solution saved successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);



        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> addSolutionFeatures(String name,String description,String icon,String title,String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can add solution features");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            Solution solution = solutionDao.findSolutionByTitle(title);
            if(solution==null){
                responseMessage.setMessage("Solution Not found for which you are adding features");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            ArrayList<SolutionFeatures> list = solution.getSolutionFeatures();
            if(list==null){
                list = new ArrayList<>();
            }
            SolutionFeatures solutionFeatures = new SolutionFeatures(name, description, icon);
            list.add(solutionFeatures);
            solution.setSolutionFeatures(list);
            solutionDao.save(solution);

            responseMessage.setMessage("Solution Details updated Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> addSolutionBenefits(String name,String description,String icon,String title,String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can add solution benefits");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            Solution solution = solutionDao.findSolutionByTitle(title);
            if(solution==null){
                responseMessage.setMessage("Solution Not found for which you are adding benefits");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            ArrayList<SolutionBenefits> list = solution.getSolutionBenefits();
            if(list==null){
                list = new ArrayList<>();
            }
            SolutionBenefits solutionBenefits = new SolutionBenefits(name, icon, description);
            list.add(solutionBenefits);
            solution.setSolutionBenefits(list);
            solutionDao.save(solution);

            responseMessage.setMessage("Solution Details updated Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> deleteAllSolutions(String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can delete solution");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            List<Solution> solutions = solutionDao.findAll();
            if(solutions!=null){
                solutionDao.deleteAll();
            }
            List<SolutionCategory> solutionCategories = solutionCategoryDao.findAll();
            if(solutionCategories!=null){
                solutionCategoryDao.deleteAll();
            }
            responseMessage.setMessage("all solutions deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> deleteSolutionsById(String authorization,String title){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can delete solution");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            Solution solution = solutionDao.findSolutionByTitle(title);
            if(solution!=null){
                solutionDao.delete(solution);
            }

            List<SolutionCategory> solutionCategories = solutionCategoryDao.findAll();
            for(int i=0;i<solutionCategories.size();i++){
                SolutionCategory solutionCategory = solutionCategories.get(i);
                List<Solution> solutions = solutionCategory.getSolutions();
                for(int j=0;j<solutions.size();j++){
                    if(title.equals(solutions.get(j).getTitle())){
                        solutionCategory.getSolutions().remove(j);
                        solutionCategoryDao.save(solutionCategory);
                    }
                }
            }

            responseMessage.setMessage(" solution deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getAllSolutions(){
        try {
            List<Solution> solutions = solutionDao.findAll();
            List<Solution> solutionResponses = new ArrayList<>();
            for(int i=0;i<solutions.size();i++){
                Solution solutionResponse = new Solution();
                solutionResponse.setTitle(solutions.get(i).getTitle());
                solutionResponse.setDescription(solutions.get(i).getDescription());
                solutionResponse.setCoverimg(solutions.get(i).getCoverimg());
                solutionResponse.setSolimg1(solutions.get(i).getSolimg1());
                solutionResponse.setSolimg2(solutions.get(i).getSolimg2());
                solutionResponse.setSolimg3(solutions.get(i).getSolimg3());
                solutionResponse.setProductused(solutions.get(i).getProductused());
                solutionResponse.setSolutionFeatures(solutions.get(i).getSolutionFeatures());
                solutionResponse.setSolutionBenefits(solutions.get(i).getSolutionBenefits());

                solutionResponses.add(solutionResponse);

            }
            return ResponseEntity.status(HttpStatus.OK).body(solutionResponses);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getSolutionById(String title){
        try {
            Solution solution = solutionDao.findSolutionByTitle(title);
            return ResponseEntity.status(HttpStatus.OK).body(solution);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            
        }
    }

    public ResponseEntity<?> addSolutionCategory(String category,String catimg,String catdescription,String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can add solution features");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }
            SolutionCategory solution = new SolutionCategory();
            solution.setCategory(category);
            solution.setCatimg(catimg);
            solution.setCatdescription(catdescription);
            solutionCategoryDao.save(solution);

            List<Solution> sol = solutionDao.findAll();
            ArrayList<Solution> new_sol = new ArrayList<>();
            System.out.println("outside");
            for(int i=0;i<sol.size();i++){
                System.out.println(sol.get(i).getSolcategory());
                System.out.println(category);
                if(sol.get(i).getSolcategory().equals(category)){
                    System.out.println("inside"+i);
                    new_sol.add(sol.get(i));
                }
            }
            solution.setSolutions(new_sol);

            // List<Solution> sol = solutionDao.findSolutionByCategory(category);
            // solution.setSolutions(sol);

            solutionCategoryDao.save(solution);

            responseMessage.setMessage("category added successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> deleteSolutionCategories(String authorization){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can delete solution categories");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }
            List<SolutionCategory> solutionCategories = solutionCategoryDao.findAll();
            if(solutionCategories!=null){
                solutionCategoryDao.deleteAll();
            }

            responseMessage.setMessage("solution categories deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> deleteSolutionCategoryById(String authorization,String category){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can delete solution categories");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }
            SolutionCategory solutionCategory = solutionCategoryDao.findSolutionCategoryByCategory(category);
            if(solutionCategory!=null){
                solutionCategoryDao.delete(solutionCategory);
            }

            responseMessage.setMessage("solution category deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getSolutionCategories(){
        try {
            List<SolutionCategory> solutionCategory = solutionCategoryDao.findAll();
            List<SolCategoryResponse> solCategoryResponses = new ArrayList<>();
            for(int i=0;i<solutionCategory.size();i++){
                SolCategoryResponse solCategoryResponse = new SolCategoryResponse();
                solCategoryResponse.setCategory(solutionCategory.get(i).getCategory());
                List<String> titles = new ArrayList<>();
                for(int j=0;j<solutionCategory.get(i).getSolutions().size();j++){
                titles.add(solutionCategory.get(i).getSolutions().get(j).getTitle());
                }
                solCategoryResponse.setSolutionName(titles);

                solCategoryResponses.add(solCategoryResponse);
        }

        return ResponseEntity.status(HttpStatus.OK).body(solCategoryResponses);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getSolutionCategoryDetails(){
        try {
            List<SolutionCategory> solutionCategories = solutionCategoryDao.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(solutionCategories);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            
        }
    }
}
