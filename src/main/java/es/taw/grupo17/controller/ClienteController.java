package es.taw.grupo17.controller;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dto.Cuenta;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.dto.Tipopersona;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import es.taw.grupo17.service.*;
import es.taw.grupo17.ui.FiltroClientes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*
@author: Jaime Rodrigo Roldán Corcelles
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    protected EstadopersonaService estadopersonaService;

    @Autowired
    protected TipopersonaService tipopersonaService;

    @Autowired
    protected PersonaService clienteService;

    @Autowired
    protected CuentaService cuentaService;

    @Autowired
    protected EstadoCuentaService estadoCuentaService;

    @GetMapping("/")
    public String empresaHome(Model model, HttpSession session){
        return this.procesarFiltrado(model,session);
    }



    protected String procesarFiltrado(Model model, HttpSession session) {
        Persona personaSessionAttribute = (Persona) session.getAttribute("cliente");
        if (personaSessionAttribute == null) {
            return "login";
        } else {
            Persona cliente = personaSessionAttribute==null ? null :
                    this.clienteService.buscarPersona(personaSessionAttribute.getId());
            model.addAttribute("cliente",cliente);
            model.addAttribute("estadoPersonaService",estadopersonaService);
            model.addAttribute("tipopersonaService",tipopersonaService);
            model.addAttribute("cuentaService",cuentaService);
            model.addAttribute("estadoCuentaService",estadoCuentaService);
            return "clienteMenu";
        }
    }

    @GetMapping("/modificarcliente")
    String modificarCliente(@RequestParam("id") Integer idcliente, Model model)
    {
        Persona cliente = this.clienteService.buscarPersona(idcliente);

        model.addAttribute("error", 0);
        model.addAttribute("cliente", cliente);
        return("modificarcliente");
    }


    @GetMapping("/registrar")
    public String listarRegistrar(Model model) {
        Persona cliente = new Persona();
        model.addAttribute("cliente",cliente);
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);
        return "cliente";
    }


    @PostMapping("/cancelarRegistro")
    public String doCancelarRegistro() {
        return "redirect:/";
    }

    @PostMapping("/añadir")
    public String doAñadir(@ModelAttribute("cliente") Persona cliente,
                           @RequestParam("repetirContraseña") String repetirContraseña,Model model) {
        model.addAttribute("cliente", cliente);
        if (cliente.getId() != null) {
            this.clienteService.guardarPersona(cliente);
            return "redirect:/cliente/";
        } else if (cliente.getContraseña().equals(repetirContraseña)) {
            cliente.setEstadopersonaByEstado(5);
            cliente.setTipopersonaByTipo(3);
            this.clienteService.guardarPersona(cliente);
            return "redirect:/cliente/";
        } else {
            model.addAttribute("repContraseña", repetirContraseña);
            return "cliente";
        }
    }

    @GetMapping("/editarCliente")
    public String doEditarCliente(Model model, @RequestParam("id") Integer idCliente){
        Persona cliente = this.clienteService.buscarPersona(idCliente);
        model.addAttribute("cliente",cliente);
        String repContraseña = null;
        model.addAttribute("repContraseña" ,repContraseña);
        return "cliente";
    }
    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitarDesbloqueo(@RequestParam("id") Integer idPersona){
        Persona cliente = this.clienteService.buscarPersona(idPersona);
        cliente.setEstadopersonaByEstado(5);
        Cuenta cuenta = this.cuentaService.buscarCuenta(cliente.getCuentaByCuenta());
        if(cuenta!=null){
            cuenta.setEstadocuentaByEstado(4);
        }

        this.cuentaService.guardarCuenta(cuenta);
        this.clienteService.guardarPersona(cliente);
        return "redirect:/cliente/";
    }

    @GetMapping("/operaciones")
    public String doMostrarMenuOperaciones(@RequestParam("id") Integer idPersona){
        return "redirect:/cajero/?id=" + idPersona;
        }


    }