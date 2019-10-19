//package demo.controllers;
//
//import demo.dao.ProfileDAO;
//import demo.entities.ProfileEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//@CrossOrigin
//@RestController
//public class ProfileController {
//    @GetMapping("/profiles/all")
//    public ResponseEntity<?> allProfiles() {
//        List<ProfileEntity> list = ProfileDAO.getAll();
//        return new ResponseEntity(list, HttpStatus.OK);
//    }
//
//    @GetMapping("/profiles")
//    public ResponseEntity<?> profiles(@RequestParam(value = "id") Integer id) {
//        ProfileEntity entity = ProfileDAO.findById(id);
//        return new ResponseEntity(entity, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/profiles", produces = "application/json", consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity<?> addProfiles(@RequestParam(value = "action") String action,
//                                         @RequestBody LinkedHashMap entity) {
//        if (action.equals("update")) {
//            ProfileDAO.update(Integer.valueOf(((LinkedHashMap) entity.get("data")).get("profileId").toString()),
//                    ((LinkedHashMap) entity.get("data")).get("description").toString());
//
//            return new ResponseEntity(HttpStatus.OK);
//        } else {
//            ProfileDAO.add(((LinkedHashMap) entity.get("data")).get("description").toString());
//
//            return new ResponseEntity(HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/profiles/remove")
//    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") Integer id) {
//        try {
//            ProfileDAO.remove(id);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}

package demo.controllers;

import demo.dao.ProfileDAO;
import demo.dao.repository.IProfileRepository;
import demo.entities.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@CrossOrigin
@RestController
public class ProfileController {
    private IProfileRepository profileRepository;

    @Autowired
    public ProfileController(IProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    @GetMapping("/profiles/all")
    public ResponseEntity<?> allProfiles() {
        try {
            return new ResponseEntity(/*ProfileDAO.getAll()*/profileRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/profiles", params = {"id"})
    public ResponseEntity<?> profiles(@RequestParam(value = "id") Integer id) {
        ProfileEntity entity = ProfileDAO.findById(id);
        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @PostMapping(value = "/profiles", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProfiles(@RequestParam(value = "action") String action,
                                         @RequestBody LinkedHashMap entity) {
        if (action.equals("update")) {
            ProfileDAO.update(Integer.valueOf(((LinkedHashMap) entity.get("data")).get("profileId").toString()),
                    ((LinkedHashMap) entity.get("data")).get("description").toString());

            return new ResponseEntity(HttpStatus.OK);
        } else {
            ProfileDAO.add(((LinkedHashMap) entity.get("data")).get("description").toString());

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/profiles/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") Integer id) {
        try {
            ProfileDAO.remove(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
