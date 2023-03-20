package es.taw.grupo17.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    @GetMapping("/")
    public String inicio(){
        return "empleado";
    }
}
