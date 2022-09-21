package com.mavs.backend.services.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mavs.backend.daos.admin.AdminDao;
import com.mavs.backend.daos.product.ProductDao;
import com.mavs.backend.daos.solution.SolutionDao;
import com.mavs.backend.entities.admin.Admin;
import com.mavs.backend.entities.product.Product;
import com.mavs.backend.entities.solution.Solution;
import com.mavs.backend.entities.solution.SolutionBenefits;
import com.mavs.backend.entities.solution.SolutionFeatures;
import com.mavs.backend.helper.JwtUtil;
import com.mavs.backend.helper.ResponseMessage;

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
    
    public ResponseEntity<?> addSolution(String title,String description,String coverimg,String solimg1,String solimg2,String solimg3,List<String> productsused,String authorization){
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
                responseMessage.setMessage("Only admins can add solution features");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            Solution solution = solutionDao.findSolutionByTitle(title);
            if(solution==null){
                responseMessage.setMessage("Solution Not found for which you are adding features");
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
}
