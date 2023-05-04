package es.taw.grupo17.service;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dao.TipooperacionRepository;
import es.taw.grupo17.dto.Operacion;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/*
@author: Francisco Javier Tejada Martín 50%
@author: Aryan Dilip Sadhwani Sadhwani 50%
 */

@Service
public class OperacionService {

    @Autowired
    OperacionRepository operacionRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    TipooperacionRepository tipooperacionRepository;


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

    public List<Operacion> getOperacionesFiltro1(Date fecha, Integer idCuenta)
    {
        List<OperacionEntity> operacionList = this.operacionRepository.getOperacionesFiltro1(fecha, idCuenta);
        List<Operacion> operacionesDTO = listaOperacionesADTO(operacionList);
        return operacionesDTO;
    }

    public List<Operacion> getOperacionesFiltro2(Integer tipo, Integer idCuenta)
    {
        List<OperacionEntity> operacionList = this.operacionRepository.getOperacionesFiltro2(tipo, idCuenta);
        List<Operacion> operacionesDTO = listaOperacionesADTO(operacionList);
        return operacionesDTO;
    }

    public List<Operacion> getOperacionesFiltro3(Integer tipo, Date fecha, Integer idCuenta)
    {
        List<OperacionEntity> operacionList = this.operacionRepository.getOperacionesFiltro3(tipo, fecha, idCuenta);
        List<Operacion> operacionesDTO = listaOperacionesADTO(operacionList);
        return operacionesDTO;
    }

    public void guardarOperacion(Operacion operacion)
    {
        OperacionEntity operacionEntity;
        if (operacion.getId() == null)
        {
            operacionEntity = new OperacionEntity();
        }
        else
        {
            operacionEntity = this.operacionRepository.findById(operacion.getId()).orElse(null);
        }

        operacionEntity.setId(operacion.getId());
        operacionEntity.setCantidadCambio(operacion.getCantidadCambio());
        operacionEntity.setCantidad(operacion.getCantidad());
        operacionEntity.setMoneda(operacion.getMoneda());
        operacionEntity.setFechaEjecucion(operacion.getFechaEjecucion());
        operacionEntity.setFechaInstruccion(operacion.getFechaInstruccion());
        operacionEntity.setMonedaCambio(operacion.getMonedaCambio());
        operacionEntity.setCuentaByCuenta(operacion.getCuentaByCuenta() == null ? null : this.cuentaRepository.findById(operacion.getCuentaByCuenta()).orElse(null));
        operacionEntity.setPersonaByBeneficiario(operacion.getPersonaByBeneficiario() == null ? null : this.personaRepository.findById(operacion.getPersonaByBeneficiario()).orElse(null));
        operacionEntity.setTipooperacionByTipo(operacion.getTipooperacionByTipo() == null ? null : this.tipooperacionRepository.findById(operacion.getTipooperacionByTipo()).orElse(null));

        this.operacionRepository.save(operacionEntity);
        operacion.setId(operacionEntity.getId());
    }
}
