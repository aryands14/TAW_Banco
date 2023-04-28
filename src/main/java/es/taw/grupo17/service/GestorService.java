package es.taw.grupo17.service;

import es.taw.grupo17.dao.*;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.EstadocuentaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestorService {

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
    @Autowired
    protected TipoOperacionRepository tipoOperacionRepository;


    public List<Persona> listarClientes (String texto, List<String> estados) {
        List<PersonaEntity> lista;
        if (texto.isEmpty() && estados.isEmpty()) {
            lista = this.personaRepository.findAll();
        } else if (texto.isEmpty()) {
            lista = this.personaRepository.buscarPorEstado(estados);
        } else if (estados.isEmpty()) {
            lista = this.personaRepository.buscarPorNombre(texto);
        } else {
            lista = this.personaRepository.buscarPorNombreYEstado(texto, estados);
        }
        return this.listaClientesADTO(lista);
    }

    public List<Empresa> listarEmpresas (String texto, List<String> estados) {
        List<EmpresaEntity> lista;
        if (texto.isEmpty() && estados.isEmpty()) {
            lista = this.empresaRepository.findAll();
        } else if (texto.isEmpty()) {
            lista = this.empresaRepository.buscarPorEstado(estados);
        } else if (estados.isEmpty()) {
            lista = this.empresaRepository.buscarPorNombre(texto);
        } else {
            lista = this.empresaRepository.buscarPorNombreYEstado(texto, estados);
        }
        return this.listaEmpresasADTO(lista);
    }

    public List<Persona> listarClientes() {
        List<PersonaEntity> listaClientes = this.personaRepository.findAll();
        List<Persona> listaClientesDTO = listaClientesADTO(listaClientes);
        return listaClientesDTO;
    }
    public List<Empresa> listarEmpresas() {
        List<EmpresaEntity> listaEmpresas = this.empresaRepository.findAll();
        List<Empresa> listaEmpresasDTO = listaEmpresasADTO(listaEmpresas);
        return listaEmpresasDTO;
    }

    protected List<Persona> listaClientesADTO (List<PersonaEntity> lista) {
        ArrayList dtos = new ArrayList<Persona>();

        lista.forEach((PersonaEntity cliente) -> dtos.add(cliente.toDTO()));

        return dtos;
    }

    protected List<Empresa> listaEmpresasADTO (List<EmpresaEntity> lista) {
        ArrayList dtos = new ArrayList<Empresa>();

        lista.forEach((final EmpresaEntity empresa) -> dtos.add(empresa.toDTO()));

        return dtos;
    }

    public List<Persona> getClientesPendientes(int i) {
        EstadocuentaEntity e = this.estadoCuentaRepository.findById(i).orElse(null);
        List<PersonaEntity> pendientes = this.personaRepository.getPendientes(i);
        List<Persona> personasPendientes = listaClientesADTO(pendientes);
        return  personasPendientes;
    }

    public List<Empresa> getEmpresasPendientes(int i) {
        EstadocuentaEntity e = this.estadoCuentaRepository.findById(i).orElse(null);
        List<EmpresaEntity> pendientes = this.empresaRepository.getPendientes(i);
        List<Empresa> personasPendientes = listaEmpresasADTO(pendientes);
        return  personasPendientes;
    }


    public List<Persona> getClientesInactivos(String estado) {
        List<PersonaEntity> inactivos = this.personaRepository.getInactivos(estado);
        List<Persona> personasInactivos = listaClientesADTO(inactivos);
        return  personasInactivos;
    }

    public List<Empresa> getEmpresasInactivos(String estado) {
        List<EmpresaEntity> inactivos = this.empresaRepository.getInactivos(estado);
        List<Empresa> empresasInactivos = listaEmpresasADTO(inactivos);
        return  empresasInactivos;
    }


    public List<Persona> getClientesSospechosos(List<CuentaEntity> sospechosos) {
        List<PersonaEntity> listaPersonas = this.personaRepository.getSospechosos(sospechosos);
        List<Persona> listaPersonasDTO = listaClientesADTO(listaPersonas);
        return  listaPersonasDTO;
    }

    public List<Empresa> getEmpresasSospechosos(List<CuentaEntity> sospechosos) {
        List<EmpresaEntity> listaEmpresas = this.empresaRepository.getSospechosos(sospechosos);
        List<Empresa> listaEmpresasDTO = listaEmpresasADTO(listaEmpresas);
        return  listaEmpresasDTO;
    }

    public Persona buscarCliente (Integer id) {
        PersonaEntity cliente = this.personaRepository.findById(id).orElse(null);
        if (cliente != null) {
            return cliente.toDTO();
        } else {
            return null;
        }
    }

    public Empresa buscarEmpresa (Integer id) {
        EmpresaEntity cliente = this.empresaRepository.findById(id).orElse(null);
        if (cliente != null) {
            return cliente.toDTO();
        } else {
            return null;
        }
    }

}
