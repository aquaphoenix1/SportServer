package demo.controllers;

import demo.dao.repository.ClientEntityRepository;
import demo.dao.repository.ClientSessionEntityRepository;
import demo.dao.repository.SessionEntityRepository;
import demo.entities.ClientEntity;
import demo.entities.ClientSessionEntity;
import demo.entities.ClientSessionEntityPK;
import demo.entities.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class ClientSessionController {
    private ClientSessionEntityRepository clientSessionEntityRepository;
    private SessionEntityRepository sessionEntityRepository;
    private ClientEntityRepository clientEntityRepository;

    @Autowired
    public ClientSessionController(ClientSessionEntityRepository clientSessionEntityRepository, SessionEntityRepository sessionEntityRepository, ClientEntityRepository clientEntityRepository) {
        this.clientSessionEntityRepository = clientSessionEntityRepository;
        this.sessionEntityRepository = sessionEntityRepository;
        this.clientEntityRepository = clientEntityRepository;
    }

    @GetMapping("/client_sessions/all")
    public ResponseEntity<?> getAllSessions() {
        return new ResponseEntity(clientSessionEntityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/client_sessions", params = {"email", "sessionId"})
    public ResponseEntity<?> getSessionById(@RequestParam(value = "email") String email, @RequestParam(value = "sessionId") int sessionId) {
        return new ResponseEntity(clientSessionEntityRepository.findById(new ClientSessionEntityPK(email, sessionId)), HttpStatus.OK);
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
                ClientSessionEntity clientSessionEntity = clientSessionEntityRepository.findById(new ClientSessionEntityPK(email, sessionId)).get();

                clientSessionEntity.setPaid(isPaid);

                clientSessionEntityRepository.save(clientSessionEntity);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(HttpStatus.OK);
        } else {
            try {
                ClientSessionEntity clientSessionEntity = new ClientSessionEntity();

                SessionEntity sessionEntity = sessionEntityRepository.findById(sessionId).get();

                clientSessionEntity.setSessionId(sessionId);
                clientSessionEntity.setSessionBySessionId(sessionEntity);

                ClientEntity clientEntity = clientEntityRepository.findById(email).get();

                clientSessionEntity.setClientByMail(clientEntity);
                clientSessionEntity.setMail(email);

                clientSessionEntity.setPaid(isPaid);

                clientSessionEntityRepository.save(clientSessionEntity);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/client_sessions/remove")
    public ResponseEntity<?> removeSession(@RequestParam(value = "email") String email, @RequestParam(value = "id") int sessionId) {
        try {
            clientSessionEntityRepository.deleteById(new ClientSessionEntityPK(email, sessionId));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
