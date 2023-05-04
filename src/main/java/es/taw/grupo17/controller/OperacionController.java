package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dao.TipooperacionRepository;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Operacion;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.dto.Tipooperacion;
import es.taw.grupo17.entity.*;
import es.taw.grupo17.service.EmpresaService;
import es.taw.grupo17.service.OperacionService;
import es.taw.grupo17.service.PersonaService;
import es.taw.grupo17.service.TipoOperacionService;
import es.taw.grupo17.ui.FiltroOperacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
@author: Álvaro Bermúdez Gámez 50%
@author: Aryan Dilip Sadhwani Sadhwani 50%
 */

@Controller
@RequestMapping("/operaciones")
public class OperacionController {

    @Autowired
    protected OperacionService operacionService;
    @Autowired
    protected PersonaService personaService;
    @Autowired
    protected EmpresaService empresaService;
    @Autowired
    protected TipoOperacionService tipoOperacionService;


    @PostMapping("/filtrarCliente")
    public String doFiltrarCliente(@ModelAttribute("filtro") FiltroOperacion filtro, @RequestParam("id") Integer id, Model model) {
        Persona persona = this.personaService.buscarPersona(id);
        List<Operacion> lista = this.operacionService.listarOperaciones(filtro.isCantidad(), filtro.isFecha(), filtro.getTipos(), id);
        model.addAttribute("cliente", persona);
        model.addAttribute("filtro", filtro);
        List<Tipooperacion> tipos = this.tipoOperacionService.listarTiposOperaciones();
        model.addAttribute("tiposOperacion", tipos);
        model.addAttribute("operaciones", lista);
        return "detallesCliente";
    }

    @PostMapping("/filtrarEmpresa")
    public String doFiltrarEmpresa(@ModelAttribute("filtro") FiltroOperacion filtro, @RequestParam("id") Integer id, Model model){
        Empresa empresa = this.empresaService.buscarEmpresa(id);
        List<Operacion> lista = this.operacionService.listarOperacionesEmpresa(filtro.isCantidad(), filtro.isFecha(), filtro.getTipos(), id);
        model.addAttribute("empresa", empresa);
        model.addAttribute("filtro", filtro);
        List<Tipooperacion> tipos = this.tipoOperacionService.listarTiposOperaciones();
        model.addAttribute("tiposOperacion", tipos);
        model.addAttribute("operaciones", lista);
        return "detallesEmpresa";
    }


}
