package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.EstadoPersonaRepository;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.EstadopersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EstadoPersonaRepository estadoPersonaRepository;

    @GetMapping("/registrar")
    public String listarRegistrar(Model model) {
        EmpresaEntity empresa = new EmpresaEntity();
        model.addAttribute("empresa",empresa);
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);
        return "empresa";
    }

    @PostMapping("/añadir")
    public String doAñadir(@ModelAttribute("empresa") EmpresaEntity empresa,
                           @RequestParam("repetirContraseña") String repetirContraseña,Model model){
        model.addAttribute("empresa",empresa);
        if (empresa.getContraseña().equals(repetirContraseña)){
            EstadopersonaEntity estado = this.estadoPersonaRepository.findById(5).orElse(null);
            empresa.setEstadopersonaByEstado(estado);
            this.empresaRepository.save(empresa);
            return "personaEmpresa";
        }else {
            model.addAttribute("repContraseña",repetirContraseña);
            return "empresa";
        }

    }

    @PostMapping("/cancelarRegistro")
    public String doCancelarRegistro(){
        return "redirect:/";
    }
}
