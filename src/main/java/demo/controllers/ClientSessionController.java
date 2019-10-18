package demo.controllers;

import demo.dao.ClientSessionDAO;
import demo.entities.ClientSessionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class ClientSessionController {
    @GetMapping("/client_sessions/all")
    public ResponseEntity<?> getAllSessions() {
        List<ClientSessionEntity> list = ClientSessionDAO.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/client_sessions", params = {"email", "sessionId"})
    public ResponseEntity<?> getSessionById(@RequestParam(value = "email") String email, @RequestParam(value = "sessionId") int sessionId) {
        ClientSessionEntity entity = ClientSessionDAO.findById(email, sessionId);
        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @PostMapping(value = "/client_sessions", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addSession(@RequestParam(value = "action") String action,
                                        @RequestBody LinkedHashMap entity) {
        LinkedHashMap data = ((LinkedHashMap) entity.get("data"));

        String email = data.get("clientByMail").toString();
        int sessionId = Integer.valueOf(data.get("sessionBySessionId").toString());
        boolean isPaid = Boolean.valueOf(data.get("isPaid").toString());
        if (action.equals("update")) {
            try {
                ClientSessionDAO.update(email, sessionId, isPaid);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(HttpStatus.OK);
        } else {
            try {
                ClientSessionDAO.add(email, sessionId, isPaid);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/client_sessions/remove")
    public ResponseEntity<?> removeSession(@RequestParam(value = "email") String email, @RequestParam(value = "id") int sessionId) {
        try {
            ClientSessionDAO.remove(email, sessionId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
