package es.taw.grupo17.service;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.EmpresaRepository;
import es.taw.grupo17.dao.EstadoPersonaRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void guardarEmpresa(Empresa empresa) {
        EmpresaEntity empresaEntity = new EmpresaEntity();
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
        empresaEntity.setCuentaByCuenta(this.cuentaRepository.findById(empresa.getCuentaByCuenta().getId()).orElse(null));
        empresaEntity.setEstadopersonaByEstado(this.estadoPersonaRepository.findById(empresa.getEstadopersonaByEstado().getId()).orElse(null));

        List<PersonaEntity> personas = new ArrayList<>();
        for (Persona persona : empresa.getPersonasById()){
            personas.add(this.personaRepository.findById(persona.getId()).orElse(null));
        }
        empresaEntity.setPersonasById(personas);

        this.empresaRepository.save(empresaEntity);
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

        for (Persona persona : empresa.getPersonasById()){
            lista.add(this.personaRepository.findById(persona.getId()).orElse(null).toDTO());
        }

        return lista;
    }
}
