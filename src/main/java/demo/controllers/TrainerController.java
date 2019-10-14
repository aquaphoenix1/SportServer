package demo.controllers;

import demo.dao.TrainerDAO;
import demo.entities.TrainerEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class TrainerController {
    @GetMapping("/trainers/all")
    public ResponseEntity<?> allTrainers() {
        List<TrainerEntity> list = TrainerDAO.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/trainers")
    public ResponseEntity<?> trainers(@RequestParam(value = "id") Integer id) {
        TrainerEntity entity = TrainerDAO.findById(id);
        return new ResponseEntity(entity, HttpStatus.OK);
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
            TrainerDAO.update(id, name, surname, age, profileId);

            return new ResponseEntity(HttpStatus.OK);
        } else {
            TrainerDAO.add(name, surname, age, profileId);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/trainers/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") Integer id) {
        try {
            TrainerDAO.remove(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
