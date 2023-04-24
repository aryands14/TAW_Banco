package es.taw.grupo17.controller;

import es.taw.grupo17.dao.*;
import es.taw.grupo17.entity.*;
import es.taw.grupo17.ui.FiltroClientes;
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
    protected EmpresaRepository empresaRepository;
    @Autowired
    protected OperacionRepository operacionRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;
    @Autowired
    protected EstadoPersonaRepository estadoPersonaRepository;


    @GetMapping("/")
    public String doListarTodos(Model model, HttpSession session) {
        String urlTo = "clientes";
        List<PersonaEntity> listaClientes = this.personaRepository.findAll();
        List<EmpresaEntity> listaEmpresas = this.empresaRepository.findAll();
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        List<EstadopersonaEntity> estadosPersona = this.estadoPersonaRepository.findAll();
        model.addAttribute("estadosPersona", estadosPersona);
        model.addAttribute("filtro", new FiltroClientes());
        return urlTo;
    }

    @GetMapping("/solicitados")
    public String doListarSolicitados(Model model, HttpSession session) {
        String urlTo = "clientesAlta";
        List<PersonaEntity> listaClientes = this.personaRepository.getPendientes(5);
        List<EmpresaEntity> listaEmpresas = this.empresaRepository.getPendientes(5);
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        return urlTo;
    }

    @GetMapping("/altaPersona")
    public String doAltaPersona(@RequestParam("id") Integer cid, Model model) {
        PersonaEntity p = this.personaRepository.findById(cid).orElse(null);
        CuentaEntity c = new CuentaEntity();
        EstadopersonaEntity estadoPersona = this.estadoPersonaRepository.findById(1).orElse(null);
        EstadocuentaEntity estadoCuenta = this.estadoCuentaRepository.findById(1).orElse(null);
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
        EstadopersonaEntity estadoPersona = this.estadoPersonaRepository.findById(1).orElse(null);
        EstadocuentaEntity estadoCuenta = this.estadoCuentaRepository.findById(1).orElse(null);
        p.setEstadopersonaByEstado(estadoPersona);
        c.setEstadocuentaByEstado(estadoCuenta);
        p.setCuentaByCuenta(c);
        c.setFechaApertura(java.sql.Date.valueOf(LocalDate.now()));
        c.setSaldo(0.0);
        this.cuentaRepository.save(c);
        this.empresaRepository.save(p);
        return "redirect:/gestor/solicitados";
    }

    @GetMapping("/visualizarcliente")
    public String doVisualizarCliente(@RequestParam("id") Integer id, Model model) {
        PersonaEntity persona = this.personaRepository.findById(id).orElse(null);
        if (persona != null) {
            model.addAttribute("cliente", persona);
            List<OperacionEntity> operaciones = null;
            if (persona.getCuentaByCuenta() != null) {
                operaciones = this.operacionRepository.getOperaciones(persona.getCuentaByCuenta().getId());
            }
            model.addAttribute("operaciones", operaciones);
        }
        return "detallesCliente";
    }

    @GetMapping("/visualizarempresa")
    public String doVisualizarEmpresa(@RequestParam("id") Integer id, Model model) {
        EmpresaEntity empresa = this.empresaRepository.findById(id).orElse(null);
        if (empresa != null) {
            model.addAttribute("empresa", empresa);
            List<OperacionEntity> operaciones = null;
            if (empresa.getCuentaByCuenta() != null) {
                operaciones = this.operacionRepository.getOperaciones(empresa.getCuentaByCuenta().getId());
            }
            model.addAttribute("operaciones", operaciones);
        }
        return "detallesEmpresa";
    }


    @GetMapping("/inactivos")
    public String doListarInactivos(Model model, HttpSession session) {
        String urlTo = "clientesInactivos";
        EstadocuentaEntity estadoCuenta = this.estadoCuentaRepository.findById(1).orElse(null);
        List<PersonaEntity> inactivos = this.personaRepository.getInactivos(estadoCuenta.getDescripcion());
        model.addAttribute("clientes", inactivos);
        List<EmpresaEntity> inactivos2 = this.empresaRepository.getInactivos(estadoCuenta.getDescripcion());
        model.addAttribute("empresas", inactivos2);
        return urlTo;
    }

    @GetMapping ("/desactivarPersona")
    public String doDesactivarCuenta(@RequestParam("id") Integer id, Model model, HttpSession session) {
        PersonaEntity p = this.personaRepository.findById(id).orElse(null);
        CuentaEntity c = p.getCuentaByCuenta();
        EstadocuentaEntity estado = this.estadoCuentaRepository.findById(2).orElse(null);
        c.setEstadocuentaByEstado(estado);
        this.cuentaRepository.save(c);
        return "redirect:/gestor/inactivos";
    }

    @GetMapping ("/desactivarEmpresa")
    public String doDesactivarCuentaEmpresa(@RequestParam("id") Integer id, Model model, HttpSession session) {
        EmpresaEntity p = this.empresaRepository.findById(id).orElse(null);
        CuentaEntity c = p.getCuentaByCuenta();
        EstadocuentaEntity estado = this.estadoCuentaRepository.findById(2).orElse(null);
        c.setEstadocuentaByEstado(estado);
        this.cuentaRepository.save(c);
        return "redirect:/gestor/inactivos";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(Model model, @ModelAttribute("filtro") FiltroClientes filtro) {
        List<PersonaEntity> lista = new ArrayList<>();
        List<EmpresaEntity> lista2 = new ArrayList<>();
        if(!filtro.getTexto().isEmpty() && !filtro.getEstados().isEmpty()) {
            lista = this.personaRepository.buscarPorNombreYEstado(filtro.getTexto(),filtro.getEstados());
            lista2 = this.empresaRepository.buscarPorNombreYEstado(filtro.getTexto(), filtro.getEstados());
        } else if(filtro.getEstados().isEmpty()) {
            lista = this.personaRepository.buscarPorNombre(filtro.getTexto());
            lista2 = this.empresaRepository.buscarPorNombre(filtro.getTexto());
        } else {
            lista = this.personaRepository.buscarPorEstado(filtro.getEstados());
            lista2 = this.empresaRepository.buscarPorEstado(filtro.getEstados());
        }
        model.addAttribute("clientes", lista);
        model.addAttribute("filtro", filtro);
        List<EstadopersonaEntity> estadosPersona = this.estadoPersonaRepository.findAll();
        model.addAttribute("estadosPersona", estadosPersona);
        model.addAttribute("empresas", lista2);
        return "clientes";
    }

    @GetMapping("/sospechosos")
    public String doListarSospechosos(Model model) {
        String urlTo = "clientesSospechosos";
        List<CuentaEntity> cuentasSospechosas = null;
        EstadocuentaEntity estadoCuenta = this.estadoCuentaRepository.findById(1).orElse(null);
        List<PersonaEntity> sospechosos = this.personaRepository.getSospechosos(cuentasSospechosas);
        model.addAttribute("clientes", sospechosos);
        List<EmpresaEntity> sospechosos1 = this.empresaRepository.getSospechosos(cuentasSospechosas);
        model.addAttribute("empresas", sospechosos1);
        return urlTo;
    }

}
