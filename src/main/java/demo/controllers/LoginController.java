package demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public  class  LoginController {

    @GetMapping("/login")
    public String getLogin() {
        /*if (error != null && error) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity("login", HttpStatus.OK);*/
        return "index";
    }

}
