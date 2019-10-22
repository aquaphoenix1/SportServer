package demo.controllers;

import demo.dao.repository.LobbyEntityRepository;
import demo.entities.LobbyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@CrossOrigin
@RestController
public class LobbyController {
    private LobbyEntityRepository lobbyEntityRepository;

    @Autowired
    public LobbyController(LobbyEntityRepository lobbyEntityRepository) {
        this.lobbyEntityRepository = lobbyEntityRepository;
    }

    @GetMapping("/lobbies/all")
    public ResponseEntity<?> allProfiles() {
        return new ResponseEntity(lobbyEntityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/lobbies", params = {"id"})
    public ResponseEntity<?> profiles(@RequestParam(value = "id", required = true) Integer id) {
        return new ResponseEntity(lobbyEntityRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/lobbies", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProfiles(@RequestParam(value = "action") String action,
                                         @RequestBody LinkedHashMap entity) {
        LinkedHashMap data = ((LinkedHashMap) entity.get("data"));
        String description = data.get("description").toString();

        if (action.equals("update")) {
            int id = Integer.valueOf(data.get("lobbyId").toString());
            LobbyEntity lobbyEntity = lobbyEntityRepository.findById(id).get();
            lobbyEntity.setDescription(description);

            lobbyEntityRepository.save(lobbyEntity);

            return new ResponseEntity(HttpStatus.OK);
        } else {
            LobbyEntity lobbyEntity = new LobbyEntity();
            lobbyEntity.setDescription(description);

            lobbyEntityRepository.save(lobbyEntity);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/lobbies/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") Integer id) {
        try {
            lobbyEntityRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
