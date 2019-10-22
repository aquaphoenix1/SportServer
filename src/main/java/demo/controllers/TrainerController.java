package demo.controllers;

import demo.dao.repository.ProfileEntityRepository;
import demo.dao.repository.TrainerEntityRepository;
import demo.entities.ProfileEntity;
import demo.entities.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class TrainerController {
    private TrainerEntityRepository trainerEntityRepository;
    private ProfileEntityRepository profileEntityRepository;

    @Autowired
    public TrainerController(TrainerEntityRepository trainerEntityRepository, ProfileEntityRepository profileEntityRepository){
        this.trainerEntityRepository = trainerEntityRepository;
        this.profileEntityRepository = profileEntityRepository;
    }

    @GetMapping("/trainers/all")
    public ResponseEntity<?> allTrainers() {
        return new ResponseEntity(trainerEntityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/trainers", params = {"id"})
    public ResponseEntity<?> trainers(@RequestParam(value = "id") Integer id) {
        return new ResponseEntity(trainerEntityRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(value = "/trainers", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addTrainers(@RequestParam(value = "action") String action,
                                         @RequestBody LinkedHashMap entity) {
        LinkedHashMap data = ((LinkedHashMap) entity.get("data"));
        String name = data.get("name").toString();
        String surname = data.get("surname").toString();
        int age = Integer.valueOf(data.get("age").toString());
        int profileId = Integer.valueOf(data.get("profileByProfileId").toString());
        if (action.equals("update")) {
            int id = Integer.valueOf(data.get("trainerId").toString());

            TrainerEntity trainerEntity = trainerEntityRepository.findById(id).get();
            trainerEntity.setName(name);
            trainerEntity.setSurname(surname);
            trainerEntity.setAge(age);

            ProfileEntity profile = profileEntityRepository.findById(profileId).get();

            trainerEntity.setProfileByProfileId(profile);

            trainerEntityRepository.save(trainerEntity);

            return new ResponseEntity(HttpStatus.OK);
        } else {
            TrainerEntity trainerEntity = new TrainerEntity();
            trainerEntity.setName(name);
            trainerEntity.setSurname(surname);
            trainerEntity.setAge(age);

            ProfileEntity profile = profileEntityRepository.findById(profileId).get();

            trainerEntity.setProfileByProfileId(profile);

            trainerEntityRepository.save(trainerEntity);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/trainers/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") Integer id) {
        try {
            trainerEntityRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
