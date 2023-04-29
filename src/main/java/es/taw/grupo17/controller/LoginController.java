package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpleadoRepository;
import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import es.taw.grupo17.service.EmpresaService;
import es.taw.grupo17.service.PersonaService;
import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected PersonaRepository personaRepository;

    @Autowired
    protected EmpresaService empresaService;

    @Autowired
    protected PersonaService personaService;

    @GetMapping("/")
    public String doLogin() {
        return "login";
    }

    @PostMapping("/")
    public String doAutenticar(@RequestParam("usuario") String user,
                               @RequestParam("clave") String clave, Model model, HttpSession session) {
        String urlTo = "redirect:/clientes/";
        EmpleadoEntity empleado = this.empleadoRepository.autenticar(user, clave);
        Empresa empresa = this.empresaService.autenticar(user,clave);
        Persona personaEmpresa = this.personaService.autenticarPersonaEmpresa(user,clave);
        if(empleado == null && empresa==null && personaEmpresa==null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "login";
        } else if(empleado!=null){
            session.setAttribute("empleado", empleado);
        } else if (empresa!=null) {
            if(empresa.getEstadopersonaByEstado()==5){
                model.addAttribute("error", "Esta pendiente de aprovación");
                urlTo = "login";
            }else{
                session.setAttribute("empresa", empresa);
                session.setAttribute("personaEmpresa",null);
                urlTo = "redirect:/empresa/";
            }
        } else if (personaEmpresa!=null){
            if(personaEmpresa.getEstadopersonaByEstado()==5){
                model.addAttribute("error", "Esta pendiente de aprovación");
                urlTo = "login";
            }else{
                session.setAttribute("personaEmpresa", personaEmpresa);
                session.setAttribute("empresa", null);
                urlTo = "redirect:/empresa/";
            }
        }
        return urlTo;
    }

    @PostMapping("/registrarEmpresa")
    public String doRegistrarEmpresa(){
        return "redirect:/empresa/registrar";
    }

}
