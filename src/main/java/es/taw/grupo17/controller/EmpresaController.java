package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.EstadoPersonaRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dao.TipoPersonaRepository;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.EstadopersonaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import es.taw.grupo17.entity.TipopersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EstadoPersonaRepository estadoPersonaRepository;

    @Autowired
    TipoPersonaRepository tipoPersonaRepository;

    @Autowired
    PersonaRepository personaRepository;

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

            String repContraseña = null;
            model.addAttribute("repContraseña" ,repContraseña);

            List<TipopersonaEntity> listaTipos = this.tipoPersonaRepository.findAll();
            model.addAttribute("listaTipos",listaTipos);

            PersonaEntity persona = new PersonaEntity();
            model.addAttribute("persona",persona);
            model.addAttribute("empresa",empresa);
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

    @PostMapping("/anadirPersona")
    public String doAñadirPersona(@ModelAttribute("persona") PersonaEntity persona,
                                  @RequestParam("repetirContraseña") String repetirContraseña,
                                  @RequestParam("idEmpresa") Integer idEmpresa,Model model){
        EmpresaEntity empresa = this.empresaRepository.findById(idEmpresa).orElse(null);
        if (persona.getContraseña().equals(repetirContraseña)){
            EstadopersonaEntity estadopersona = this.estadoPersonaRepository.findById(5).orElse(null);
            persona.setEstadopersonaByEstado(estadopersona);
            persona.setEmpresaByEmpresa(empresa);
            List<PersonaEntity> listaPersonas = empresa.getPersonasById();
            if(listaPersonas==null){
                listaPersonas = new ArrayList<>();
                listaPersonas.add(persona);
            }else{
                listaPersonas.add(persona);
            }
            empresa.setPersonasById(listaPersonas);
            this.personaRepository.save(persona);
            this.empresaRepository.save(empresa);
            return "redirect:/";
        }else {
            List<TipopersonaEntity> listaTipos = this.tipoPersonaRepository.findAll();
            model.addAttribute("listaTipos",listaTipos);
            model.addAttribute("repContraseña",repetirContraseña);
            model.addAttribute("empresa",empresa);
            model.addAttribute("persona",persona);
            return "personaEmpresa";
        }
    }
}
