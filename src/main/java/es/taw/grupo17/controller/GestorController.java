package es.taw.grupo17.controller;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        String urlTo = "clientes";
        List<PersonaEntity> listaClientes = this.personaRepository.getPendientes(5);
        List<PersonaEntity> listaEmpresas = this.empresaRepository.getPendientes(5);
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("empresas", listaEmpresas);
        return urlTo;
    }


    @GetMapping("/alta")
    public String doAlta(Model model) {
        return "detallesCliente";
    }

    @GetMapping("/visualizar")
    public String doVisualizar(@RequestParam("id") Integer idCustomer, Model model) {
        PersonaEntity persona = this.personaRepository.findById(idCustomer).orElse(null);
        model.addAttribute("cliente", persona);
        List<OperacionEntity> operaciones = (List<OperacionEntity>) persona.getOperacionsById();
        model.addAttribute("operaciones", operaciones);
        return "detallesCliente";
    }
}
