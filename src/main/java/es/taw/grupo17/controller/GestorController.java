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
    protected GestorService gestorService;
    @Autowired
    protected EmpresaRepository empresaRepository;
    @Autowired
    protected OperacionRepository operacionRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;
    @Autowired
    protected EstadoPersonaRepository estadoPersonaRepository;
    @Autowired
    protected EstadopersonaService estadopersonaService;

    @Autowired
    protected TipoOperacionRepository tipoOperacionRepository;
    @Autowired
    protected CuentaService cuentaService;
    @Autowired
    protected OperacionService operacionService;
    @Autowired
    protected TipoOperacionService tipoOperacionService;

    @GetMapping("/")
    public String doListarTodos(Model model, HttpSession session) {
        return doFiltrar(model, null);
    }

    @PostMapping("/filtrar")
    public String doFiltrar(Model model, @ModelAttribute("filtro") FiltroClientes filtro) {
        List<Persona> listaClientes;
        List<Empresa> listaEmpresas;
        if(filtro == null || (filtro.getTexto().isEmpty() && filtro.getEstados().isEmpty())) {
            listaClientes = this.gestorService.listarClientes();
            listaEmpresas = this.gestorService.listarEmpresas();
            filtro = new FiltroClientes();
        } else {
            listaClientes = this.gestorService.listarClientes(filtro.getTexto(), filtro.getEstados());
            listaEmpresas = this.gestorService.listarEmpresas(filtro.getTexto(), filtro.getEstados());
        }
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        model.addAttribute("filtro", filtro);
        List<EstadopersonaEntity> estadosPersona = this.estadoPersonaRepository.findAll();
        model.addAttribute("estadosPersona", estadosPersona);
        model.addAttribute("cuentaService", this.cuentaService);
        return "clientes";
    }


    @GetMapping("/solicitados")
    public String doListarSolicitados(Model model, HttpSession session) {
        String urlTo = "clientesAlta";
        List<Persona> listaClientes = this.gestorService.getClientesPendientes(10);
        List<Empresa> listaEmpresas = this.gestorService.getEmpresasPendientes(10);
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
        List<CuentaEntity> cuentasSospechosas = this.cuentaRepository.getSospechosos();
        List<Persona> sospechosos = this.gestorService.getClientesSospechosos(cuentasSospechosas);
        model.addAttribute("clientes", sospechosos);
        List<Empresa> sospechosos1 = this.gestorService.getEmpresasSospechosos(cuentasSospechosas);
        model.addAttribute("empresas", sospechosos1);
        return urlTo;
    }

    @GetMapping("/altaPersona")
    public String doAltaPersona(@RequestParam("id") Integer cid, Model model) {
        PersonaEntity p = this.personaRepository.findById(cid).orElse(null);
        CuentaEntity c = new CuentaEntity();
        EstadopersonaEntity estadoPersona = this.estadoPersonaRepository.findById(6).orElse(null);
        EstadocuentaEntity estadoCuenta = this.estadoCuentaRepository.findById(5).orElse(null);
        p.setEstadopersonaByEstado(estadoPersona);
        c.setEstadocuentaByEstado(estadoCuenta);
        p.setCuentaByCuenta(c);
        c.setFechaApertura(java.sql.Date.valueOf(LocalDate.now()));
        c.setSaldo(0.0);
        this.cuentaRepository.save(c);
        this.personaRepository.save(p);
        return "redirect:/gestor/solicitados";
    }

    @GetMapping("/altaEmpresa")
    public String doAltaEmpresa(@RequestParam("id") Integer cid, Model model) {
        EmpresaEntity p = this.empresaRepository.findById(cid).orElse(null);
        CuentaEntity c = new CuentaEntity();
        EstadopersonaEntity estadoPersona = this.estadoPersonaRepository.findById(6).orElse(null);
        EstadocuentaEntity estadoCuenta = this.estadoCuentaRepository.findById(5).orElse(null);
        p.setEstadopersonaByEstado(estadoPersona);
        c.setEstadocuentaByEstado(estadoCuenta);
        p.setCuentaByCuenta(c);
        c.setFechaApertura(java.sql.Date.valueOf(LocalDate.now()));
        c.setSaldo(0.0);
        this.cuentaRepository.save(c);
        this.empresaRepository.save(p);
        return "redirect:/gestor/solicitados";
    }

    @GetMapping("/inactivos")
    public String doListarInactivos(Model model, HttpSession session) {
        String urlTo = "clientesInactivos";
        EstadocuentaEntity estadoCuenta = this.estadoCuentaRepository.findById(5).orElse(null);
        List<Persona> listaClientes = this.gestorService.getClientesInactivos(estadoCuenta.getDescripcion());
        List<Empresa> listaEmpresas = this.gestorService.getEmpresasInactivos(estadoCuenta.getDescripcion());
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        return urlTo;
    }

    @GetMapping ("/desactivarPersona")
    public String doDesactivarCuenta(@RequestParam("id") Integer id, Model model, HttpSession session) {
        PersonaEntity p = this.personaRepository.findById(id).orElse(null);
        CuentaEntity c = p.getCuentaByCuenta();
        EstadocuentaEntity estado = this.estadoCuentaRepository.findById(6).orElse(null);
        c.setEstadocuentaByEstado(estado);
        this.cuentaRepository.save(c);
        return "redirect:/gestor/inactivos";
    }

    @GetMapping ("/desactivarEmpresa")
    public String doDesactivarCuentaEmpresa(@RequestParam("id") Integer id, Model model, HttpSession session) {
        EmpresaEntity p = this.empresaRepository.findById(id).orElse(null);
        CuentaEntity c = p.getCuentaByCuenta();
        EstadocuentaEntity estado = this.estadoCuentaRepository.findById(6).orElse(null);
        c.setEstadocuentaByEstado(estado);
        this.cuentaRepository.save(c);
        return "redirect:/gestor/inactivos";
    }

    @GetMapping ("/bloquearCuentaPersona")
    public String doBloquearCuenta(@RequestParam("id") Integer id, Model model) {
        PersonaEntity p = this.personaRepository.findById(id).orElse(null);
        CuentaEntity c = p.getCuentaByCuenta();
        EstadocuentaEntity estado = this.estadoCuentaRepository.findById(7).orElse(null);
        c.setEstadocuentaByEstado(estado);
        this.cuentaRepository.save(c);
        return "redirect:/gestor/sospechosos";
    }

}
