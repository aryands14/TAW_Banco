package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpleadoRepository;
import es.taw.grupo17.dto.Empleado;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.service.EmpleadoService;
import es.taw.grupo17.service.EmpresaService;
import es.taw.grupo17.service.PersonaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
@author: Álvaro Bermúdez Gámez 33%
@author: Aryan Dilip Sadhwani Sadhwani 33%
@author: Jaime Rodrigo Roldán Corcelles 33%
 */

@Controller
public class LoginController {


    @Autowired
    protected EmpresaService empresaService;
    @Autowired
    protected EmpleadoService empleadoService;
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
        Empleado empleado = this.empleadoService.autenticar(user, clave);
        Empresa empresa = this.empresaService.autenticar(user, clave);
        Persona personaEmpresa = this.personaService.autenticarPersonaEmpresa(user, clave);
        Persona cliente = this.personaService.autenticarCliente(user, clave);
        if (empleado == null && empresa == null && personaEmpresa == null && cliente == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "login";
        } else if (empleado != null) {
            session.setAttribute("gestor", empleado);
            urlTo = "redirect:/gestor/";
        }  else if (cliente != null) {
            if (cliente.getEstadopersonaByEstado() == 5) {
                model.addAttribute("error", "Esta pendiente de aprovación");
                urlTo = "login";
            } else {
                session.setAttribute("cliente", cliente);
                urlTo = "redirect:/cliente/";
            }
        }else if (empresa != null) {
            if (empresa.getEstadopersonaByEstado() == 5) {
                model.addAttribute("error", "Esta pendiente de aprovación");
                urlTo = "login";
            } else {
                session.setAttribute("empresa", empresa);
                session.setAttribute("personaEmpresa", null);
                urlTo = "redirect:/empresa/";
            }
        } else if (personaEmpresa != null) {
            if (personaEmpresa.getEstadopersonaByEstado() == 5) {
                model.addAttribute("error", "Esta pendiente de aprovación");
                urlTo = "login";
            } else {
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

    @PostMapping("/registrarCliente")
    public String doRegistrarEm(){
        return "redirect:/cliente/registrar";
    }

}
