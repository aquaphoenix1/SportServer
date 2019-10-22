//package demo.controllers;
//
//import demo.dao.ClientDAO;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.LinkedHashMap;
//
//@CrossOrigin
//@RestController
//public class LoginController {
//    private static String encode(String password) {
//        return new BCryptPasswordEncoder().encode(password);
//    }
//
//    @GetMapping("/login")
//    public ResponseEntity<?> getLogin(@RequestParam(value = "error", required = false) Boolean error) {
//        if (error != null && error) {
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//
//        return new ResponseEntity("login", HttpStatus.OK);
//    }
//
//    @GetMapping("/logout")
//    public ResponseEntity<?> getLogout(@RequestParam(value = "logout", required = false) Boolean logout) {
//        return new ResponseEntity((logout != null && logout) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
//    }
//
//    @PostMapping("/registration")
//    public ResponseEntity<?> postRegistration(@RequestBody LinkedHashMap entity) {
//        String email = entity.get("email").toString();
//        String password = encode(entity.get("password").toString());
//        String name = entity.get("name").toString();
//        String surname = entity.get("surname").toString();
//        int age = Integer.valueOf(entity.get("age").toString());
//
//        try {
//            ClientDAO.save(email, password, name, surname, age);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(null, HttpStatus.OK);
//    }
//
//    @PostMapping("/reset")
//    public ResponseEntity<?> reset(@RequestBody LinkedHashMap entity) {
//        String email = entity.get("email").toString();
//        String password = encode(entity.get("password").toString());
//
//        try {
//            ClientDAO.updatePassword(email, password);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(null, HttpStatus.OK);
//    }
//}

package demo.controllers;

//import demo.dao.ClientDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@CrossOrigin
@RestController
public class LoginRestController {
    private static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @GetMapping(value = "/login", params = {"error"})
    public ResponseEntity<?> getLogin(@RequestParam(value = "error", required = false) Boolean error) {
        if (error != null && error) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity("login", HttpStatus.OK);
    }

    @GetMapping(value = "/logout", params = {"logout"})
    public ResponseEntity<?> getLogout(@RequestParam(value = "logout", required = false) Boolean logout) {
        return new ResponseEntity((logout != null && logout) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> postRegistration(@RequestBody LinkedHashMap entity) {
        String email = entity.get("email").toString();
        String password = encode(entity.get("password").toString());
        String name = entity.get("name").toString();
        String surname = entity.get("surname").toString();
        int age = Integer.valueOf(entity.get("age").toString());

        try {
            //ClientDAO.save(email, password, name, surname, age);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@RequestBody LinkedHashMap entity) {
        String email = entity.get("email").toString();
        String password = encode(entity.get("password").toString());

        try {
            //ClientDAO.updatePassword(email, password);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }
}
