package es.taw.grupo17.controller;

import es.taw.grupo17.dao.*;
import es.taw.grupo17.dto.*;
import es.taw.grupo17.entity.*;
import es.taw.grupo17.service.*;
import es.taw.grupo17.ui.FiltroClientes;
import es.taw.grupo17.ui.FiltroOperacion;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected PersonaRepository personaRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;
    @Autowired
    protected GestorService gestorService;
    @Autowired
    protected CuentaService cuentaService;
    @Autowired
    protected PersonaService personaService;
    @Autowired
    protected EmpresaService empresaService;
    @Autowired
    protected OperacionService operacionService;
    @Autowired
    protected TipoOperacionService tipoOperacionService;
    @Autowired
    protected EstadopersonaService estadopersonaService;
    @Autowired
    protected EstadoCuentaService estadoCuentaService;

    @GetMapping("/")
    public String doListarTodos(Model model, HttpSession session) {
        return doFiltrar(model, null);
    }

    @PostMapping("/filtrar")
    public String doFiltrar(Model model, @ModelAttribute("filtro") FiltroClientes filtro) {
        List<Persona> listaClientes;
        List<Empresa> listaEmpresas;
        if(filtro == null || (filtro.getTexto().isEmpty() && filtro.getEstados().isEmpty())) {
            listaClientes = this.personaService.listarClientes();
            listaEmpresas = this.empresaService.listarEmpresas();
            filtro = new FiltroClientes();
        } else {
            listaClientes = this.personaService.listarClientes(filtro.getTexto(), filtro.getEstados());
            listaEmpresas = this.empresaService.listarEmpresas(filtro.getTexto(), filtro.getEstados());
        }
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        model.addAttribute("filtro", filtro);
        List<Estadopersona> estadosPersona = this.estadopersonaService.doListarEstados();
        model.addAttribute("estadosPersona", estadosPersona);
        model.addAttribute("cuentaService", this.cuentaService);
        return "clientes";
    }


    @GetMapping("/solicitados")
    public String doListarSolicitados(Model model, HttpSession session) {
        String urlTo = "clientesAlta";
        List<Persona> listaClientes = this.personaService.getClientesPendientes(10);
        List<Empresa> listaEmpresas = this.empresaService.getEmpresasPendientes(10);
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        return urlTo;
    }

    @GetMapping("/visualizarcliente")
    public String doVisualizarCliente(@RequestParam("id") Integer id, Model model) {
        Persona persona = this.gestorService.buscarCliente(id);
        if (persona != null) {
            model.addAttribute("cliente", persona);
            List<Operacion> operaciones = null;
            if (persona.getCuentaByCuenta() != null) {
                operaciones = this.operacionService.getOperaciones(persona.getCuentaByCuenta());
            }
            List<Tipooperacion> tiposOperacion = this.tipoOperacionService.listarTiposOperaciones();
            model.addAttribute("tiposOperacion", tiposOperacion);
            model.addAttribute("filtro", new FiltroOperacion());
            model.addAttribute("operaciones", operaciones);
        }
        return "detallesCliente";
    }

    @GetMapping("/visualizarempresa")
    public String doVisualizarEmpresa(@RequestParam("id") Integer id, Model model) {
        Empresa empresa = this.gestorService.buscarEmpresa(id);
        if (empresa != null) {
            model.addAttribute("empresa", empresa);
            List<Operacion> operaciones = null;
            if (empresa.getCuentaByCuenta() != null) {
                operaciones = this.operacionService.getOperaciones(empresa.getCuentaByCuenta());
            }
            List<Tipooperacion> tiposOperacion = this.tipoOperacionService.listarTiposOperaciones();
            model.addAttribute("tiposOperacion", tiposOperacion);
            model.addAttribute("filtro", new FiltroOperacion());
            model.addAttribute("operaciones", operaciones);
        }
        return "detallesEmpresa";
    }

    @GetMapping("/sospechosos")
    public String doListarSospechosos(Model model) {
        String urlTo = "clientesSospechosos";
        List<Cuenta> cuentasSospechosas = this.cuentaService.getSospechosos();
        List<Persona> sospechosos = this.personaService.getClientesSospechosos(cuentasSospechosas);
        model.addAttribute("clientes", sospechosos);
        List<Empresa> sospechosos1 = this.empresaService.getEmpresasSospechosos(cuentasSospechosas);
        model.addAttribute("empresas", sospechosos1);
        return urlTo;
    }

    @GetMapping("/altaPersona")
    public String doAltaPersona(@RequestParam("id") Integer cid, Model model) {
        Persona p = this.personaService.buscarPersona(cid);
        Cuenta c = new Cuenta();
        Estadopersona estadoPersona = this.estadopersonaService.buscarEstado(6);
        Estadocuenta estadoCuenta = this.estadoCuentaService.buscarEstadoCuenta(5);
        c.setFechaApertura(java.sql.Date.valueOf(LocalDate.now()));
        c.setSaldo(0.0);
        c.setEstadocuentaByEstado(estadoCuenta.getId());
        this.cuentaService.guardarCuenta(c);
        p.setEstadopersonaByEstado(estadoPersona.getId());
        p.setCuentaByCuenta(c.getId());
        this.personaService.guardarPersona(p);
        return "redirect:/gestor/solicitados";
    }

    @GetMapping("/altaEmpresa")
    public String doAltaEmpresa(@RequestParam("id") Integer cid, Model model) {
        //EmpresaEntity p = this.empresaRepository.findById(cid).orElse(null);
        Empresa e = this.empresaService.buscarEmpresa(cid);
        Cuenta c = new Cuenta();
        Estadopersona estadoPersona = this.estadopersonaService.buscarEstado(6);
        Estadocuenta estadoCuenta = this.estadoCuentaService.buscarEstadoCuenta(5);
        e.setEstadopersonaByEstado(estadoPersona.getId());
        c.setEstadocuentaByEstado(estadoCuenta.getId());
        e.setCuentaByCuenta(c.getId());
        c.setFechaApertura(java.sql.Date.valueOf(LocalDate.now()));
        c.setSaldo(0.0);
        this.cuentaService.guardarCuenta(c);
        this.empresaService.guardarEmpresa(e);
        return "redirect:/gestor/solicitados";
    }

    @GetMapping("/inactivos")
    public String doListarInactivos(Model model, HttpSession session) {
        String urlTo = "clientesInactivos";
        Estadocuenta estadoCuenta = this.estadoCuentaService.buscarEstadoCuenta(5);
        List<Persona> listaClientes = this.personaService.getClientesInactivos(estadoCuenta.getDescripcion());
        List<Empresa> listaEmpresas = this.empresaService.getEmpresasInactivos(estadoCuenta.getDescripcion());
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        return urlTo;
    }

    @GetMapping ("/desactivarPersona")
    public String doDesactivarCuenta(@RequestParam("id") Integer id, Model model, HttpSession session) {
        Persona p = this.personaService.buscarPersona(id);
        Cuenta c = this.cuentaService.buscarCuenta(p.getCuentaByCuenta());
        Estadocuenta estado = this.estadoCuentaService.buscarEstadoCuenta(6);
        c.setEstadocuentaByEstado(estado.getId());
        this.cuentaService.guardarCuenta(c);
        return "redirect:/gestor/inactivos";
    }

    @GetMapping ("/desactivarEmpresa")
    public String doDesactivarCuentaEmpresa(@RequestParam("id") Integer id, Model model, HttpSession session) {
        Empresa e = this.empresaService.buscarEmpresa(id);
        Cuenta c = this.cuentaService.buscarCuenta(e.getCuentaByCuenta());
        Estadocuenta estado = this.estadoCuentaService.buscarEstadoCuenta(6);
        c.setEstadocuentaByEstado(estado.getId());
        this.cuentaService.guardarCuenta(c);
        return "redirect:/gestor/inactivos";
    }

    @GetMapping ("/bloquearCuentaPersona")
    public String doBloquearCuenta(@RequestParam("id") Integer id, Model model) {
        Persona p = this.personaService.buscarPersona(id);
        Cuenta c = this.cuentaService.buscarCuenta(p.getCuentaByCuenta());
        Estadocuenta estado = this.estadoCuentaService.buscarEstadoCuenta(7);
        c.setEstadocuentaByEstado(estado.getId());
        this.cuentaService.guardarCuenta(c);
        return "redirect:/gestor/sospechosos";
    }

}
