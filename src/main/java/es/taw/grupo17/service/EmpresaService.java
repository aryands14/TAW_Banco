package es.taw.grupo17.service;

import es.taw.grupo17.dao.*;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.EstadocuentaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
@author: Álvaro Bermúdez Gámez 50%
@author: Sadhwani Sadhwani Aryan Dilip 50%
 */

@Service
public class EmpresaService {

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected EstadoPersonaRepository estadoPersonaRepository;

    @Autowired
    protected PersonaRepository personaRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

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

    public List<Empresa> listarEmpresas() {
        List<EmpresaEntity> listaEmpresas = this.empresaRepository.findAll();
        List<Empresa> listaEmpresasDTO = listaEmpresasADTO(listaEmpresas);
        return listaEmpresasDTO;
    }

    protected List<Empresa> listaEmpresasADTO (List<EmpresaEntity> lista) {
        ArrayList dtos = new ArrayList<Empresa>();

        lista.forEach((final EmpresaEntity empresa) -> dtos.add(empresa.toDTO()));

        return dtos;
    }

    public List<Empresa> getEmpresasPendientes(int i) {
        EstadocuentaEntity e = this.estadoCuentaRepository.findById(i).orElse(null);
        List<EmpresaEntity> pendientes = this.empresaRepository.getPendientes(i);
        List<Empresa> personasPendientes = listaEmpresasADTO(pendientes);
        return  personasPendientes;
    }

    public List<Empresa> getEmpresasInactivos(String estado) {
        List<EmpresaEntity> inactivos = this.empresaRepository.getInactivos(estado);
        List<Empresa> empresasInactivos = listaEmpresasADTO(inactivos);
        return  empresasInactivos;
    }

    public List<Empresa> getEmpresasSospechosos(List<Integer> sospechosos) {
        List<EmpresaEntity> listaEmpresas = this.empresaRepository.getSospechosos(sospechosos);
        List<Empresa> listaEmpresasDTO = listaEmpresasADTO(listaEmpresas);
        return  listaEmpresasDTO;
    }


    public void guardarEmpresa(Empresa empresa) {
        EmpresaEntity empresaEntity;
        if (empresa.getId()==null){
            empresaEntity = new EmpresaEntity();
        }else {
            empresaEntity = this.empresaRepository.findById(empresa.getId()).orElse(null);
        }

        empresaEntity.setId(empresa.getId());
        empresaEntity.setCif(empresa.getCif());
        empresaEntity.setNombre(empresa.getNombre());
        empresaEntity.setCalle(empresa.getCalle());
        empresaEntity.setNumero(empresa.getNumero());
        empresaEntity.setPlantaPuertaOficina(empresa.getPlantaPuertaOficina());
        empresaEntity.setCiudad(empresa.getCiudad());
        empresaEntity.setPais(empresa.getPais());
        empresaEntity.setRegion(empresa.getRegion());
        empresaEntity.setCp(empresa.getCp());
        empresaEntity.setValida(empresa.getValida());
        empresaEntity.setContraseña(empresa.getContraseña());

        empresaEntity.setCuentaByCuenta(empresa.getCuentaByCuenta()==null? null :
                this.cuentaRepository.findById(empresa.getCuentaByCuenta()).orElse(null));

        empresaEntity.setEstadopersonaByEstado(empresa.getEstadopersonaByEstado()==null ? null :
                this.estadoPersonaRepository.findById(empresa.getEstadopersonaByEstado()).orElse(null));

        if (empresa.getPersonasById()!=null && !empresa.getPersonasById().isEmpty()){
            List<PersonaEntity> personas = new ArrayList<>();
            for (Integer id : empresa.getPersonasById()){
                personas.add(this.personaRepository.findById(id).orElse(null));
            }
            empresaEntity.setPersonasById(personas);
        }

        this.empresaRepository.save(empresaEntity);
        empresa.setId(empresaEntity.getId());
    }


    public Empresa autenticar(String user, String clave) {
        EmpresaEntity empresa = this.empresaRepository.autenticar(user, clave);
        return (empresa == null? null : empresa.toDTO());
    }

    public Empresa buscarEmpresa(Integer id) {
        EmpresaEntity empresa = this.empresaRepository.findById(id).orElse(null);
        return (empresa == null? null : empresa.toDTO());
    }

    public List<Persona> listarPlantilla(Empresa empresa) {
        List<Persona> lista = new ArrayList<>();

        for (Integer id : empresa.getPersonasById()){
            lista.add(this.personaRepository.findById(id).orElse(null).toDTO());
        }

        return lista;
    }


}

