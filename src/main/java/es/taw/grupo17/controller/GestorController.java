package es.taw.grupo17.controller;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.entity.PersonaEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected PersonaRepository personaRepository;

    @GetMapping("/")
    public String doListar(Model model, HttpSession session) {
        String urlTo = "clientes";
        List<PersonaEntity> lista = this.personaRepository.getPendientes(5);
        //List<PersonaEntity> lista = this.personaRepository.findAll();
        model.addAttribute("clientes", lista);
        return urlTo;
    }


}
