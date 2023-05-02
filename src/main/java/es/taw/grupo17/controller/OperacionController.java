package es.taw.grupo17.controller;

import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dao.TipooperacionRepository;
import es.taw.grupo17.entity.*;
import es.taw.grupo17.service.OperacionService;
import es.taw.grupo17.ui.FiltroOperacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/operaciones")
public class OperacionController {

    @Autowired
    protected OperacionRepository operacionRepository;
    @Autowired
    protected OperacionService operacionService;
    @Autowired
    protected TipooperacionRepository tipooperacionRepository;
    @Autowired
    protected PersonaRepository personaRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @PostMapping("/filtrarCliente")
    public String doFiltrarCliente(@ModelAttribute("filtro") FiltroOperacion filtro, @RequestParam("id") Integer id, Model model) {
        PersonaEntity persona = this.personaRepository.findById(id).orElse(null);
        List<OperacionEntity> lista;
        model.addAttribute("cliente", persona);
        if(!filtro.getTipos().isEmpty() && !filtro.isCantidad() && !filtro.isFecha()) {
            lista = this.operacionRepository.buscarPorTipoOperacionYPersona(filtro.getTipos(), id);
        } else if (!filtro.getTipos().isEmpty() && !filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.buscarPorTipoOperacionYPersonaOrdenadoFecha(filtro.getTipos(), id);
        } else if (!filtro.getTipos().isEmpty() && filtro.isCantidad() && !filtro.isFecha()){
            lista = this.operacionRepository.buscarPorTipoOperacionYPersonaOrdenadoCantidad(filtro.getTipos(), id);
        } else if (!filtro.getTipos().isEmpty() && filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.buscarPorTipoOperacionYPersonaOrdenadoCantidad(filtro.getTipos(), id);
        } else if (filtro.getTipos().isEmpty() && filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.getOperacionesOrdenadoFechaYCantidad(id);
        } else if (filtro.getTipos().isEmpty() && filtro.isCantidad() && !filtro.isFecha()){
            lista = this.operacionRepository.getOperacionesOrdenadoCantidad(id);
        } else if (filtro.getTipos().isEmpty() && !filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.getOperacionesOrdenadoFecha(id);
        } else {
            lista = this.operacionRepository.getOperacionesByPersona(id);
        }
        model.addAttribute("filtro", filtro);
        List<TipooperacionEntity> tipos = this.tipooperacionRepository.findAll();
        model.addAttribute("tiposOperacion", tipos);
        model.addAttribute("operaciones", lista);

        return "detallesCliente";
    }

    @PostMapping("/filtraEmpresa")
    public String doFiltrarEmpresa(@ModelAttribute("filtro") FiltroOperacion filtro, @RequestParam("id") Integer id, Model model){
        EmpresaEntity empresa = this.empresaRepository.findById(id).orElse(null);
        List<OperacionEntity> lista;
        model.addAttribute("empresa", empresa);
        if(!filtro.getTipos().isEmpty() && !filtro.isCantidad() && !filtro.isFecha()) {
            lista = this.operacionRepository.buscarPorTipoOperacionYEmpresa(filtro.getTipos(), id);
        } else if (!filtro.getTipos().isEmpty() && !filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.buscarPorTipoOperacionYEmpresaOrdenadoFecha(filtro.getTipos(), id);
        } else if (!filtro.getTipos().isEmpty() && filtro.isCantidad() && !filtro.isFecha()){
            lista = this.operacionRepository.buscarPorTipoOperacionYEmpresaOrdenadoCantidad(filtro.getTipos(), id);
        } else if (!filtro.getTipos().isEmpty() && filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.buscarPorTipoOperacionYEmpresaOrdenadoCantidad(filtro.getTipos(), id);
        } else if (filtro.getTipos().isEmpty() && filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.getOperacionesOrdenadoFechaYCantidad(id);
        } else if (filtro.getTipos().isEmpty() && filtro.isCantidad() && !filtro.isFecha()){
            lista = this.operacionRepository.getOperacionesOrdenadoCantidad(id);
        } else if (filtro.getTipos().isEmpty() && !filtro.isCantidad() && filtro.isFecha()){
            lista = this.operacionRepository.getOperacionesOrdenadoFecha(id);
        } else {
            lista = this.operacionRepository.getOperacionesByEmpresa(id);
        }
        model.addAttribute("filtro", filtro);
        List<TipooperacionEntity> tipos = this.tipooperacionRepository.findAll();
        model.addAttribute("tiposOperacion", tipos);
        model.addAttribute("operaciones", lista);
        return "detallesEmpresa";
    }


}
