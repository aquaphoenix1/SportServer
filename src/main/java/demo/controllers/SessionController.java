package demo.controllers;

import demo.dao.repository.LobbyEntityRepository;
import demo.dao.repository.SessionEntityRepository;
import demo.entities.LobbyEntity;
import demo.entities.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class SessionController {
    private SessionEntityRepository sessionEntityRepository;
    private LobbyEntityRepository lobbyEntityRepository;

    @Autowired
    public SessionController(SessionEntityRepository sessionEntityRepository, LobbyEntityRepository lobbyEntityRepository) {
        this.sessionEntityRepository = sessionEntityRepository;
        this.lobbyEntityRepository = lobbyEntityRepository;
    }

    @GetMapping("/sessions/all")
    public ResponseEntity<?> getAllSessions() {
        return new ResponseEntity(sessionEntityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/sessions", params = {"id"})
    public ResponseEntity<?> getSessionById(@RequestParam(value = "id") Integer id) {
        return new ResponseEntity(sessionEntityRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/sessions", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addSession(@RequestParam(value = "action") String action,
                                        @RequestBody LinkedHashMap entity) {
        LinkedHashMap data = ((LinkedHashMap) entity.get("data"));

        int price = Integer.valueOf(data.get("price").toString());
        String description = data.get("description").toString();
        Date startDate = Date.valueOf(data.get("startDate").toString());
        Date endDate = Date.valueOf(data.get("endDate").toString());
        int lobbyId = Integer.valueOf(data.get("lobbyByLobbyId").toString());
        if (action.equals("update")) {
            int id = Integer.valueOf(data.get("sessionId").toString());

            SessionEntity sessionEntity = sessionEntityRepository.findById(id).get();
            sessionEntity.setPrice(price);
            sessionEntity.setDescription(description);
            sessionEntity.setStartDate(startDate);
            sessionEntity.setEndDate(endDate);

            LobbyEntity lobbyEntity = lobbyEntityRepository.findById(lobbyId).get();

            sessionEntity.setLobbyByLobbyId(lobbyEntity);

            sessionEntityRepository.save(sessionEntity);

            return new ResponseEntity(HttpStatus.OK);
        } else {
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setPrice(price);
            sessionEntity.setDescription(description);
            sessionEntity.setStartDate(startDate);
            sessionEntity.setEndDate(endDate);

            LobbyEntity lobbyEntity = lobbyEntityRepository.findById(lobbyId).get();

            sessionEntity.setLobbyByLobbyId(lobbyEntity);

            sessionEntityRepository.save(sessionEntity);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/sessions/remove")
    public ResponseEntity<?> removeSession(@RequestParam(value = "id") Integer id) {
        try {
            sessionEntityRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
