package es.taw.grupo17.service;

import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dto.Operacion;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionService {

    @Autowired
    OperacionRepository operacionRepository;


    public List<Operacion> listarOperaciones (boolean cantidad, boolean fecha, List<Integer> tipos, Integer id) {
        List<OperacionEntity> lista;
        if(!tipos.isEmpty() && !cantidad && !fecha) {
            lista = this.operacionRepository.buscarPorTipoOperacionYPersona(tipos, id);
        } else if (!tipos.isEmpty() && !cantidad && fecha){
            lista = this.operacionRepository.buscarPorTipoOperacionYPersonaOrdenadoFecha(tipos, id);
        } else if (!tipos.isEmpty() && cantidad && !fecha){
            lista = this.operacionRepository.buscarPorTipoOperacionYPersonaOrdenadoCantidad(tipos, id);
        } else if (!tipos.isEmpty() && cantidad && fecha){
            lista = this.operacionRepository.buscarPorTipoOperacionYPersonaOrdenadoCantidadYFecha(tipos, id);
        } else if (tipos.isEmpty() && cantidad && fecha){
            lista = this.operacionRepository.getOperacionesOrdenadoFechaYCantidad(id);
        } else if (tipos.isEmpty() && cantidad && !fecha){
            lista = this.operacionRepository.getOperacionesOrdenadoCantidad(id);
        } else if (tipos.isEmpty() && !cantidad && fecha){
            lista = this.operacionRepository.getOperacionesOrdenadoFecha(id);
        } else {
            lista = this.operacionRepository.getOperacionesByPersona(id);
        }
        return this.listaOperacionesADTO(lista);
    }

    public List<Operacion> listarOperacionesEmpresa (boolean cantidad, boolean fecha, List<Integer> tipos, Integer id) {
        List<OperacionEntity> lista;
        if(!tipos.isEmpty() && !cantidad && !fecha) {
            lista = this.operacionRepository.buscarPorTipoOperacionYEmpresa(tipos, id);
        } else if (!tipos.isEmpty() && !cantidad && fecha){
            lista = this.operacionRepository.buscarPorTipoOperacionYEmpresaOrdenadoFecha(tipos, id);
        } else if (!tipos.isEmpty() && cantidad && !fecha){
            lista = this.operacionRepository.buscarPorTipoOperacionYEmpresaOrdenadoCantidad(tipos, id);
        } else if (!tipos.isEmpty() && cantidad && fecha){
            lista = this.operacionRepository.buscarPorTipoOperacionYPersonaOrdenadoCantidad(tipos, id);
        } else if (tipos.isEmpty() && cantidad && fecha){
            lista = this.operacionRepository.buscarPorEmpresaOrdenadoCantidadYFecha(id);
        } else if (tipos.isEmpty() && cantidad && !fecha){
            lista = this.operacionRepository.getOperacionesOrdenadoCantidad(id);
        } else if (tipos.isEmpty() && !cantidad && fecha){
            lista = this.operacionRepository.buscarPorEmpresaOrdenadoFecha(id);
        } else {
            lista = this.operacionRepository.getOperacionesByPersona(id);
        }
        return this.listaOperacionesADTO(lista);
    }

    public List<Operacion> getOperaciones(Integer idCuenta) {
        List<OperacionEntity> operacionList = this.operacionRepository.getOperaciones(idCuenta);
        List<Operacion> operacionesDTO = listaOperacionesADTO(operacionList);
        return operacionesDTO;
    }

    protected List<Operacion> listaOperacionesADTO (List<OperacionEntity> lista) {
        ArrayList dtos = new ArrayList<Operacion>();

        lista.forEach((OperacionEntity operacion) -> dtos.add(operacion.toDTO()));

        return dtos;
    }
}
