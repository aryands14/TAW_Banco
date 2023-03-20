package es.taw.grupo17.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/")
    public String doLogin() {
        return "login";
    }



}
