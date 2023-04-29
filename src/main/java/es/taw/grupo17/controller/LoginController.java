package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpleadoRepository;
import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dto.Empleado;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.service.EmpresaService;
import es.taw.grupo17.service.PersonaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
        Empleado empleadoDTO = empleado.toDTO();
        Empresa empresa = this.empresaService.autenticar(user,clave);
        Persona personaEmpresa = this.personaService.autenticarPersonaEmpresa(user,clave);
        if(empleado == null && empresa==null && personaEmpresa==null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "login";
        } else if(empleadoDTO!=null){
            session.setAttribute("gestor", empleadoDTO);
            urlTo = "redirect:/gestor/";
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
