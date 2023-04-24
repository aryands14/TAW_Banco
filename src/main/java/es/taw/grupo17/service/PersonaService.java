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

    public Persona autenticarPersonaEmpresa(String user, String clave) {
        PersonaEntity persona = this.personaRepository.autenticarPersonaEmpresa(user, clave);
        return (persona == null? null : persona.toDTO());
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

        CuentaEntity cuentaEntity = this.cuentaRepository.findById(persona.getCuentaByCuenta().getId()).orElse(null);
        personaEntity.setCuentaByCuenta(cuentaEntity);

        EstadopersonaEntity estadopersonaEntity = this.estadoPersonaRepository.findById(persona.getEstadopersonaByEstado().getId()).orElse(null);
        personaEntity.setEstadopersonaByEstado(estadopersonaEntity);

        List<ConversacionEntity> conversaciones = new ArrayList<>();
        for (Conversacion conversacion : persona.getConversacionsById()){
            conversaciones.add(this.conversacionRepository.findById(conversacion.getId()).orElse(null));
        }
        personaEntity.setConversacionsById(conversaciones);

        List<MensajeEntity> mensajes = new ArrayList<>();
        for (Mensaje mensaje : persona.getMensajesById()){
            mensajes.add(this.mensajeRepository.findById(mensaje.getId()).orElse(null));
        }
        personaEntity.setMensajesById(mensajes);

        List<OperacionEntity> operaciones = new ArrayList<>();
        for (Operacion operacion : persona.getOperacionsById()){
            operaciones.add(this.operacionRepository.findById(operacion.getId()).orElse(null));
        }
        personaEntity.setOperacionsById(operaciones);

        personaEntity.setTipopersonaByTipo(this.tipoPersonaRepository.findById(persona.getTipopersonaByTipo().getId()).orElse(null));
        personaEntity.setEmpresaByEmpresa(this.empresaRepository.findById(persona.getEmpresaByEmpresa().getId()).orElse(null));

        this.personaRepository.save(personaEntity);
    }



}
