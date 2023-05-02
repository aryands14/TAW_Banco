package es.taw.grupo17.controller;

import es.taw.grupo17.dto.Cuenta;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.dto.Tipopersona;
import es.taw.grupo17.service.*;
import es.taw.grupo17.ui.FiltroClientes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/*
@author: Álvaro Bermúdez Gámez
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    protected EmpresaService empresaService;

    @Autowired
    protected EstadopersonaService estadopersonaService;

    @Autowired
    protected TipopersonaService tipopersonaService;

    @Autowired
    protected PersonaService personaService;

    @Autowired
    protected CuentaService cuentaService;

    @Autowired
    protected EstadoCuentaService estadoCuentaService;


    @GetMapping("/")
    public String empresaHome(Model model, HttpSession session){
            return this.procesarFiltrado(null,model,session);
    }

    @PostMapping("/filtrarPersonas")
    public String doFiltrar(@ModelAttribute("filtro") FiltroClientes filtro, Model model,HttpSession session){
        return this.procesarFiltrado(filtro,model,session);
    }

    protected String procesarFiltrado(FiltroClientes filtroClientes, Model model, HttpSession session) {
        Empresa empresaSessionAttribute = (Empresa) session.getAttribute("empresa");
        Persona personaSessionAttribute = (Persona) session.getAttribute("personaEmpresa");
        if (empresaSessionAttribute == null && personaSessionAttribute == null) {
            return "login";
        } else {
            List<Persona> lista;
            Persona persona = personaSessionAttribute==null ? null :
                    this.personaService.buscarPersona(personaSessionAttribute.getId());
            Empresa empresa = empresaSessionAttribute==null ?
                    this.empresaService.buscarEmpresa(personaSessionAttribute.getEmpresaByEmpresa()) :
                    this.empresaService.buscarEmpresa(empresaSessionAttribute.getId());
            if (filtroClientes == null || filtroClientes.getTexto().isEmpty() && filtroClientes.getTipos().isEmpty()) {
                lista = this.empresaService.listarPlantilla(empresa);
                filtroClientes = new FiltroClientes();
            } else if (filtroClientes.getTexto().isEmpty()) {
                lista = this.personaService.buscarPersona(filtroClientes.getTipos(), empresa.getId());
            } else if (filtroClientes.getTipos().isEmpty()) {
                lista = this.personaService.buscarPersona(filtroClientes.getTexto(), empresa.getId());
            } else {
                lista = this.personaService.buscarPersona(filtroClientes.getTexto(), filtroClientes.getTipos(), empresa.getId());
            }

            List<Tipopersona> listaTipos = this.tipopersonaService.listarTiposPersonas();
            model.addAttribute("listaTipos", listaTipos);
            model.addAttribute("filtro", filtroClientes);

            model.addAttribute("empresa", empresa);
            model.addAttribute("listaPersonas", lista);

            model.addAttribute("persona",persona);

            model.addAttribute("estadoPersonaService",estadopersonaService);
            model.addAttribute("tipopersonaService",tipopersonaService);
            model.addAttribute("cuentaService",cuentaService);
            model.addAttribute("estadoCuentaService",estadoCuentaService);
            return "empresaHome";
        }
    }

    @GetMapping("/registrar")
    public String listarRegistrar(Model model) {
        Empresa empresa = new Empresa();
        model.addAttribute("empresa",empresa);
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);
        return "empresa";
    }


    @PostMapping("/cancelarRegistro")
    public String doCancelarRegistro(){
        return "redirect:/";
    }

    @PostMapping("/añadir")
    public String doAñadir(@ModelAttribute("empresa") Empresa empresa,
                           @RequestParam("repetirContraseña") String repetirContraseña,Model model){
        model.addAttribute("empresa",empresa);
        if(empresa.getId()!=null){
            this.empresaService.guardarEmpresa(empresa);
            return "redirect:/empresa/";
        }else if (empresa.getContraseña().equals(repetirContraseña)){
            empresa.setEstadopersonaByEstado(5);
            this.empresaService.guardarEmpresa(empresa);
            Persona persona = new Persona();
            return mostrarEditaroNuevoPersona(persona,model,empresa);
        }else {
            model.addAttribute("repContraseña",repetirContraseña);
            return "empresa";
        }

    }

    protected String mostrarEditaroNuevoPersona(Persona persona, Model model,Empresa empresa){
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);

        List<Tipopersona> listaTipos = this.tipopersonaService.listarTiposPersonas();
        model.addAttribute("listaTipos",listaTipos);

        model.addAttribute("persona",persona);
        model.addAttribute("empresa",empresa);
        return "personaEmpresa";
    }

    @GetMapping("/nuevo")
    public String doNuevo(@RequestParam("id") Integer idEmpresa,Model model){
        Empresa empresa = this.empresaService.buscarEmpresa(idEmpresa);
        Persona persona = new Persona();
        return mostrarEditaroNuevoPersona(persona,model,empresa);
    }

    @GetMapping("/editarEmpresa")
    public String doEditarEmpresa(Model model, @RequestParam("id") Integer idEmpresa){
        Empresa empresa = this.empresaService.buscarEmpresa(idEmpresa);
        model.addAttribute("empresa",empresa);
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);
        return "empresa";
    }

    @GetMapping("/editarPersonaEmpresa")
    public String doEditarPersona(Model model, @RequestParam("id") Integer idPersona){
        Persona persona = this.personaService.buscarPersona(idPersona);
        Empresa empresa = this.empresaService.buscarEmpresa(persona.getEmpresaByEmpresa());

        return mostrarEditaroNuevoPersona(persona,model,empresa);
    }

    @PostMapping("/anadirPersona")
    public String doAñadirPersona(@ModelAttribute("persona") Persona persona,
                                  @RequestParam("repetirContraseña") String repetirContraseña,
                                  @RequestParam("idEmpresa") Integer idEmpresa,Model model){
        Empresa empresa = this.empresaService.buscarEmpresa(idEmpresa);
        if (empresa.getPersonasById().contains(persona.getId())){
            this.personaService.guardarPersona(persona);
            return "redirect:/empresa/";
        }else if (persona.getContraseña().equals(repetirContraseña)){
            persona.setEstadopersonaByEstado(5);
            persona.setEmpresaByEmpresa(empresa.getId());
            this.personaService.guardarPersona(persona);

            List<Integer> listaPersonas = empresa.getPersonasById();
            if(listaPersonas==null){
                listaPersonas = new ArrayList<>();
                listaPersonas.add(persona.getId());
            }else{
                listaPersonas.add(persona.getId());
            }
            empresa.setPersonasById(listaPersonas);

            this.empresaService.guardarEmpresa(empresa);
            if(empresa.getEstadopersonaByEstado()==5){
                return "redirect:/";
            }else{
                return "redirect:/empresa/";
            }

        }else {
            List<Tipopersona> listaTipos = this.tipopersonaService.listarTiposPersonas();
            model.addAttribute("listaTipos",listaTipos);
            model.addAttribute("repContraseña",repetirContraseña);
            model.addAttribute("empresa",empresa);
            model.addAttribute("persona",persona);
            return "personaEmpresa";
        }
    }

    @GetMapping("/bloquearPersona")
    public String doBloquearPersona(@RequestParam("id") Integer idPersona){
        Persona persona = this.personaService.buscarPersona(idPersona);
        persona.setEstadopersonaByEstado(3);
        Cuenta cuenta = this.cuentaService.buscarCuenta(persona.getCuentaByCuenta());
        if(cuenta!=null){
            cuenta.setEstadocuentaByEstado(3);
        }
        this.cuentaService.guardarCuenta(cuenta);
        this.personaService.guardarPersona(persona);

        return "redirect:/empresa/";
    }

    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitarDesbloqueo(@RequestParam("id") Integer idPersona){
        Persona persona = this.personaService.buscarPersona(idPersona);
        persona.setEstadopersonaByEstado(5);
        Cuenta cuenta = this.cuentaService.buscarCuenta(persona.getCuentaByCuenta());
        if(cuenta!=null){
            cuenta.setEstadocuentaByEstado(4);
        }

        this.cuentaService.guardarCuenta(cuenta);
        this.personaService.guardarPersona(persona);
        return "redirect:/empresa/";
    }

    @GetMapping("/operaciones")
    public String doMostrarMenuOperaciones(@RequestParam("id") Integer idPersona){
        return "redirect:/cajero/?id=" + idPersona;
    }


}
