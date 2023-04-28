package es.taw.grupo17.service;

import es.taw.grupo17.dao.*;
import es.taw.grupo17.dto.*;
import es.taw.grupo17.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    protected PersonaRepository personaRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected EstadoPersonaRepository estadoPersonaRepository;

    @Autowired
    protected ConversacionRepository conversacionRepository;

    @Autowired
    protected MensajeRepository mensajeRepository;

    @Autowired
    protected OperacionRepository operacionRepository;

    @Autowired
    protected TipoPersonaRepository tipoPersonaRepository;
    @Autowired
    protected EmpresaRepository empresaRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;


    public Persona autenticarPersonaEmpresa(String user, String clave) {
        PersonaEntity persona = this.personaRepository.autenticarPersonaEmpresa(user, clave);
        return (persona == null? null : persona.toDTO());
    }

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

    public List<Persona> listarClientes() {
        List<PersonaEntity> listaClientes = this.personaRepository.findAll();
        List<Persona> listaClientesDTO = listaClientesADTO(listaClientes);
        return listaClientesDTO;
    }

    protected List<Persona> listaClientesADTO (List<PersonaEntity> lista) {
        ArrayList dtos = new ArrayList<Persona>();

        lista.forEach((PersonaEntity cliente) -> dtos.add(cliente.toDTO()));

        return dtos;
    }

    public List<Persona> getClientesPendientes(int i) {
        EstadocuentaEntity e = this.estadoCuentaRepository.findById(i).orElse(null);
        List<PersonaEntity> pendientes = this.personaRepository.getPendientes(i);
        List<Persona> personasPendientes = listaClientesADTO(pendientes);
        return  personasPendientes;
    }

    public List<Persona> getClientesInactivos(String estado) {
        List<PersonaEntity> inactivos = this.personaRepository.getInactivos(estado);
        List<Persona> personasInactivos = listaClientesADTO(inactivos);
        return  personasInactivos;
    }

    public List<Persona> getClientesSospechosos(List<Cuenta> sospechosos) {
        List<PersonaEntity> listaPersonas = this.personaRepository.getSospechosos(sospechosos);
        List<Persona> listaPersonasDTO = listaClientesADTO(listaPersonas);
        return  listaPersonasDTO;
    }


    public Persona buscarPersona(Integer id) {
        PersonaEntity persona = this.personaRepository.findById(id).orElse(null);
        return (persona == null? null : persona.toDTO());
    }

    public List<Persona> buscarPersona(List<Integer> tipos, Integer idEmpresa) {
        List<PersonaEntity> lista = this.personaRepository.buscarPorTipoYEmpresa(tipos,idEmpresa);
        return this.listaEntidadesADTO(lista);
    }

    public List<Persona> buscarPersona(String texto, Integer idEmpresa) {
        List<PersonaEntity> lista = this.personaRepository.buscarPorNombreYEmpresa(texto,idEmpresa);
        return this.listaEntidadesADTO(lista);
    }

    public List<Persona> buscarPersona(String texto, List<Integer> tipos, Integer idEmpresa) {
        List<PersonaEntity> lista = this.personaRepository.buscarPorNombreYTipoYEmpresa(texto,tipos,idEmpresa);
        return this.listaEntidadesADTO(lista);
    }

    private List<Persona> listaEntidadesADTO(List<PersonaEntity> lista) {
        List dtos = new ArrayList<Persona>();

        lista.forEach((final PersonaEntity persona) -> dtos.add(persona.toDTO()));

        return dtos;
    }

    public void guardarPersona(Persona persona) {
        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setId(persona.getId());
        personaEntity.setNif(persona.getNif());
        personaEntity.setPrimerNombre(persona.getPrimerNombre());
        personaEntity.setSegundoNombre(persona.getSegundoNombre());
        personaEntity.setPrimerApellido(persona.getPrimerApellido());
        personaEntity.setSegundoApellido(persona.getSegundoApellido());
        personaEntity.setFechaNacimiento(persona.getFechaNacimiento());
        personaEntity.setCalle(persona.getCalle());
        personaEntity.setNumero(persona.getNumero());
        personaEntity.setPlantaPuertaOficina(persona.getPlantaPuertaOficina());
        personaEntity.setCiudad(persona.getCiudad());
        personaEntity.setPais(persona.getPais());
        personaEntity.setRegion(persona.getRegion());
        personaEntity.setCp(persona.getCp());
        personaEntity.setValida(persona.getValida());
        personaEntity.setContraseña(persona.getContraseña());

        personaEntity.setCuentaByCuenta(persona.getCuentaByCuenta()==null? null :
                this.cuentaRepository.findById(persona.getCuentaByCuenta()).orElse(null));

        personaEntity.setEstadopersonaByEstado(persona.getEstadopersonaByEstado()==null ? null :
                this.estadoPersonaRepository.findById(persona.getEstadopersonaByEstado()).orElse(null));

        if (persona.getConversacionsById()!=null){
            List<ConversacionEntity> conversaciones = new ArrayList<>();
            for (Integer id : persona.getConversacionsById()){
                conversaciones.add(this.conversacionRepository.findById(id).orElse(null));
            }
            personaEntity.setConversacionsById(conversaciones);
        }

        if (persona.getMensajesById()!=null){
            List<MensajeEntity> mensajes = new ArrayList<>();
            for (Integer id : persona.getMensajesById()){
                mensajes.add(this.mensajeRepository.findById(id).orElse(null));
            }
            personaEntity.setMensajesById(mensajes);
        }

        if (persona.getOperacionsById()!=null){
            List<OperacionEntity> operaciones = new ArrayList<>();
            for (Integer id : persona.getOperacionsById()){
                operaciones.add(this.operacionRepository.findById(id).orElse(null));
            }
            personaEntity.setOperacionsById(operaciones);
        }


        personaEntity.setTipopersonaByTipo(this.tipoPersonaRepository.findById(persona.getTipopersonaByTipo()).orElse(null));
        personaEntity.setEmpresaByEmpresa(persona.getEmpresaByEmpresa()==null ? null:
                this.empresaRepository.findById(persona.getEmpresaByEmpresa()).orElse(null));

        this.personaRepository.save(personaEntity);
        persona.setId(personaEntity.getId());
    }



}
