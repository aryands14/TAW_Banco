package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.EstadoPersonaRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dao.TipoPersonaRepository;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.EstadopersonaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import es.taw.grupo17.entity.TipopersonaEntity;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/")
    public String empresaHome(Model model, HttpSession session){
        EmpresaEntity sessionAttribute = (EmpresaEntity) session.getAttribute("empresa");
        if (sessionAttribute==null){
            return "login";
        }else {
            EmpresaEntity empresa = this.empresaRepository.findById(sessionAttribute.getId()).orElse(null);
            model.addAttribute("empresa",empresa);
            List<PersonaEntity> listaPersonas = empresa.getPersonasById();
            model.addAttribute("listaPersonas",listaPersonas);
            return "empresaHome";
        }

    }

    @GetMapping("/registrar")
    public String listarRegistrar(Model model) {
        EmpresaEntity empresa = new EmpresaEntity();
        model.addAttribute("empresa",empresa);
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);
        return "empresa";
    }

    protected String mostrarEditaroNuevoPersona(PersonaEntity persona, Model model,EmpresaEntity empresa){
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);

        List<TipopersonaEntity> listaTipos = this.tipoPersonaRepository.findAll();
        model.addAttribute("listaTipos",listaTipos);

        model.addAttribute("persona",persona);
        model.addAttribute("empresa",empresa);
        return "personaEmpresa";
    }

    @GetMapping("/nuevo")
    public String doNuevo(@RequestParam("id") Integer idEmpresa,Model model){
        EmpresaEntity empresa = this.empresaRepository.findById(idEmpresa).orElse(null);
        PersonaEntity persona = new PersonaEntity();
        return mostrarEditaroNuevoPersona(persona,model,empresa);
    }

    @PostMapping("/añadir")
    public String doAñadir(@ModelAttribute("empresa") EmpresaEntity empresa,
                           @RequestParam("repetirContraseña") String repetirContraseña,Model model){
        model.addAttribute("empresa",empresa);
        if (empresa.getContraseña().equals(repetirContraseña)){
            EstadopersonaEntity estado = this.estadoPersonaRepository.findById(5).orElse(null);
            empresa.setEstadopersonaByEstado(estado);
            this.empresaRepository.save(empresa);
            PersonaEntity persona = new PersonaEntity();
            return mostrarEditaroNuevoPersona(persona,model,empresa);
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
            if(empresa.getEstadopersonaByEstado().getId()==5){
                return "redirect:/";
            }else{
                return "redirect:/empresa/";
            }

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
