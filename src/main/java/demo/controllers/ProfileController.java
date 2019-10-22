package demo.controllers;

import demo.dao.repository.ProfileEntityRepository;
import demo.entities.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@CrossOrigin
@RestController
public class ProfileController {
    private ProfileEntityRepository profileEntityRepository;

    @Autowired
    public ProfileController(ProfileEntityRepository profileEntityRepository) {
        this.profileEntityRepository = profileEntityRepository;
    }

    @GetMapping("/profiles/all")
    public ResponseEntity<?> allProfiles() {
        return new ResponseEntity(profileEntityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/profiles", params = {"id"})
    public ResponseEntity<?> profiles(@RequestParam(value = "id") Integer id) {
        return new ResponseEntity(profileEntityRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(value = "/profiles", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProfiles(@RequestParam(value = "action") String action,
                                         @RequestBody LinkedHashMap entity) {
        LinkedHashMap data = ((LinkedHashMap) entity.get("data"));
        String description = data.get("description").toString();

        if (action.equals("update")) {
            Integer id = Integer.valueOf(data.get("profileId").toString());

            ProfileEntity profileEntity = profileEntityRepository.findById(id).get();
            profileEntity.setDescription(description);

            profileEntityRepository.save(profileEntity);

            return new ResponseEntity(HttpStatus.OK);
        } else {
            ProfileEntity profileEntity = new ProfileEntity();
            profileEntity.setDescription(description);

            profileEntityRepository.save(profileEntity);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/profiles/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") Integer id) {
        try {
            profileEntityRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
