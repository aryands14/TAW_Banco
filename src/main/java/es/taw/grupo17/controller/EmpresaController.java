package es.taw.grupo17.controller;

import es.taw.grupo17.entity.EmpresaEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @GetMapping("/registrar")
    public String listarRegistrar(Model model) {
        EmpresaEntity empresa = new EmpresaEntity();
        model.addAttribute("empresa",empresa);
        return "empresa";
    }

    @PostMapping("/añadir")
    public String doAñadir(){
        return "";
    }
}
