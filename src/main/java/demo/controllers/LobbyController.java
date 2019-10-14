package demo.controllers;

import demo.dao.LobbyDAO;
import demo.entities.LobbyEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class LobbyController {
    @GetMapping("/lobbies/all")
    public ResponseEntity<?> allProfiles() {
        List<LobbyEntity> list = LobbyDAO.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lobbies")
    public ResponseEntity<?> profiles(@RequestParam(value = "id") Integer id) {
        LobbyEntity lobby = LobbyDAO.findById(id);
        return new ResponseEntity(lobby, HttpStatus.OK);
    }

    @PostMapping(value = "/lobbies", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProfiles(@RequestParam(value = "action") String action,
                                         @RequestBody LinkedHashMap entity) {
        if (action.equals("update")) {
            LobbyDAO.update(Integer.valueOf(((LinkedHashMap) entity.get("data")).get("lobbyId").toString()),
                    ((LinkedHashMap) entity.get("data")).get("description").toString());

            return new ResponseEntity(HttpStatus.OK);
        } else {
            LobbyDAO.add(((LinkedHashMap) entity.get("data")).get("description").toString());

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/lobbies/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") Integer id) {
        try {
            LobbyDAO.remove(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
