package com.mavs.backend.services.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mavs.backend.daos.admin.AdminDao;
import com.mavs.backend.daos.home.AchievementsDao;
import com.mavs.backend.daos.home.HomeCoverDao;
import com.mavs.backend.daos.home.HomeDao;
import com.mavs.backend.daos.solution.SolutionCategoryDao;
import com.mavs.backend.entities.admin.Admin;
import com.mavs.backend.entities.home.Achievements;
import com.mavs.backend.entities.home.Home;
import com.mavs.backend.entities.home.HomeCover;
import com.mavs.backend.entities.home.SubLink;
import com.mavs.backend.entities.solution.SolutionCategory;
import com.mavs.backend.helper.JwtUtil;
import com.mavs.backend.helper.ResponseMessage;

@Repository
@Component
public class HomeService {
    
    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public Admin admin;

    @Autowired
    public AdminDao adminDao;

    @Autowired SolutionCategoryDao solutionCategoryDao;

    @Autowired
    public HomeDao homedao;

    @Autowired
    public HomeCoverDao homeCoverDao;

    @Autowired
    public AchievementsDao achievementsDao;

    public ResponseEntity<?> addNavbarDetails(String authorization, String name, String mainlink, String submenu){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can add details");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            Home home = new Home();
            home.setName(name);
            home.setMainlink(mainlink);
            home.setSubmenu(submenu);
            // if(submenu == "true"){
                if(submenu.equals("true")){
                System.out.println("Inside sublink");
                List<SubLink> sublinks = new ArrayList<>();
            List<SolutionCategory> solutionCategory = solutionCategoryDao.findAll();
            for(int i=0;i<solutionCategory.size();i++)
            {
                SubLink subLink = new SubLink();
                subLink.setHead(solutionCategory.get(i).getCategory());
                List<String> titles = new ArrayList<>();
                for(int j=0;j<solutionCategory.get(i).getSolutions().size();j++){
                titles.add(solutionCategory.get(i).getSolutions().get(j).getTitle());
                }
                subLink.setSublink(titles);

                sublinks.add(subLink);

            }
            
            home.setSublinks(sublinks);
            // }
        }
            
            homedao.save(home);
            responseMessage.setMessage("Saved successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getNavbarDetails(){
        try {
            List<Home> home  = homedao.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(home);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> addHomeCovers(String authorization,String video,String coverdescription){
        try {
            String token = authorization.substring(7);
            String email = jwtUtil.extractUsername(token);
            admin = adminDao.findAdminByEmail(email);
            if(admin==null){
                responseMessage.setMessage("Only admins can add details");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
            }

            HomeCover homecover = new HomeCover();
            homecover.setVideo(video);
            homecover.setDescription(coverdescription);
            homeCoverDao.save(homecover);
            responseMessage.setMessage("home cover details saved successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> getHomeCovers(){
        try {
            List<HomeCover> homeCovers = homeCoverDao.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(homeCovers);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    public ResponseEntity<?> addAchievements(String authorization, String img){
        try {
                String token = authorization.substring(7);
                String email = jwtUtil.extractUsername(token);
                admin = adminDao.findAdminByEmail(email);
                if(admin==null){
                    responseMessage.setMessage("Only admins can add details");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
                }
                
            Achievements achievements = new Achievements();
            achievements.setAchievementImg(img);
            achievementsDao.save(achievements);

            responseMessage.setMessage("achievements uploaded successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    public ResponseEntity<?> getAchievements(){
        try {
            List<Achievements> achievements = achievementsDao.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(achievements);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }
}
