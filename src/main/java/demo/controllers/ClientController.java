package demo.controllers;

import demo.dao.ClientDAO;
import demo.entities.ClientEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class ClientController {
    @GetMapping("/clients/all")
    public ResponseEntity<?> clients() {
        List<ClientEntity> list = ClientDAO.getAll();
        list.forEach(x -> x.setPassword(""));
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/clients", params = {"id"})
    public ResponseEntity<?> profiles(@RequestParam(value = "id") String id) {
        ClientEntity client = ClientDAO.findById(id);
        client.setPassword("");
        return new ResponseEntity(client, HttpStatus.OK);
    }

    @PostMapping(value = "/clients", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateClients(@RequestBody LinkedHashMap entity) {
        LinkedHashMap data = ((LinkedHashMap) entity.get("data"));
        String email = data.get("email").toString();
        String name = data.get("name").toString();
        String surname = data.get("surname").toString();
        int age = Integer.valueOf(data.get("age").toString());
        Object trainer = data.get("trainerByTrainerId");
        Integer trainerId = null;
        if (trainer != null) {
            trainerId = Integer.valueOf(trainer.toString());
        }
        ClientDAO.update(email, name, surname, age, trainerId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/clients/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") String id) {
        try {
            ClientDAO.remove(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}