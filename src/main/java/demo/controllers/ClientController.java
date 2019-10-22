package demo.controllers;

//import demo.dao.ClientDAO;
import demo.dao.repository.ClientEntityRepository;
import demo.dao.repository.TrainerEntityRepository;
import demo.entities.ClientEntity;
import demo.entities.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin
@RestController
public class ClientController {
    private ClientEntityRepository clientEntityRepository;
    private TrainerEntityRepository trainerEntityRepository;

    @Autowired
    public ClientController(ClientEntityRepository clientEntityRepository, TrainerEntityRepository trainerEntityRepository){
        this.clientEntityRepository = clientEntityRepository;
        this.trainerEntityRepository = trainerEntityRepository;
    }

    @GetMapping("/clients/all")
    public ResponseEntity<?> clients() {
        List<ClientEntity> list = (List<ClientEntity>) clientEntityRepository.findAll();
        list.forEach(x -> x.setPassword(""));
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/clients", params = {"id"})
    public ResponseEntity<?> profiles(@RequestParam(value = "id") String id) {
        ClientEntity client = clientEntityRepository.findById(id).get();
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

        ClientEntity clientEntity = clientEntityRepository.findById(email).get();
        clientEntity.setName(name);
        clientEntity.setSurname(surname);
        clientEntity.setAge(age);

        TrainerEntity trainerEntity = null;

        if(trainerId != null) {
            trainerEntity = trainerEntityRepository.findById(trainerId).get();
        }

        clientEntity.setTrainerByTrainerId(trainerEntity);

        clientEntityRepository.save(clientEntity);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/clients/remove")
    public ResponseEntity<?> removeProfiles(@RequestParam(value = "id") String id) {
        try {
            clientEntityRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}