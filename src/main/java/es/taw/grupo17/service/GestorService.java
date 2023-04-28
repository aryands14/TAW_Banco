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
