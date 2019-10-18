package demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class GetControllers {
    @GetMapping("/lobbies")
    public String lobbies() {
        return "index";
    }
    @GetMapping("/trainers")
    public String trainers() {
        return "index";
    }
    @GetMapping("/clients")
    public String clients() {
        return "index";
    }
    @GetMapping("/profiles")
    public String profiles() {
        return "index";
    }
    @GetMapping("/sessions")
    public String sessions() {
        return "index";
    }
    @GetMapping("/client_sessions")
    public String client_sessions() {
        return "index";
    }
}
