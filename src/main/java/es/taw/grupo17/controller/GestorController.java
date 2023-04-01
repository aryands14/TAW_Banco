package es.taw.grupo17.controller;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @GetMapping("/")
    public String doListarTodos(Model model, HttpSession session) {
        String urlTo = "clientes";
        List<PersonaEntity> listaClientes = this.personaRepository.findAll();
        List<EmpresaEntity> listaEmpresas = this.empresaRepository.findAll();
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
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


    @GetMapping("/alta")
    public String doAlta(Model model) {
        return "";
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
        String urlTo = "clientes";
        List<PersonaEntity> inactivos = this.personaRepository.getInactivos();
        model.addAttribute("clientes", inactivos);
        List<EmpresaEntity> inactivos2 = this.empresaRepository.getInactivos();
        model.addAttribute("empresas", inactivos2);
        return urlTo;
    }

}
