package demo.controllers;

import demo.dao.SessionDAO;
import demo.entities.SessionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class SessionController {
    @GetMapping("/sessions/all")
    public ResponseEntity<?> getAllSessions() {
        List<SessionEntity> list = SessionDAO.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/sessions")
    public ResponseEntity<?> getSessionById(@RequestParam(value = "id") Integer id) {
        SessionEntity entity = SessionDAO.findById(id);
        return new ResponseEntity(entity, HttpStatus.OK);
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
            SessionDAO.update(id, price, description, startDate, endDate, lobbyId);

            return new ResponseEntity(HttpStatus.OK);
        } else {
            SessionDAO.add(price, description, startDate, endDate, lobbyId);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/sessions/remove")
    public ResponseEntity<?> removeSession(@RequestParam(value = "id") Integer id) {
        try {
            SessionDAO.remove(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
