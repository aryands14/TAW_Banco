package es.taw.grupo17.dto;

import es.taw.grupo17.entity.ConversacionEntity;
import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.entity.MensajeEntity;
import es.taw.grupo17.entity.PersonaEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Conversacion implements Serializable {
    private Integer id;
    private Byte cerrada;

    private Persona personaByUsuario;

    private Empleado empleadoByAsistente;
    private Collection<Mensaje> mensajesById;

    public Conversacion(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversacion that = (Conversacion) o;
        return Objects.equals(id, that.id) && Objects.equals(cerrada, that.cerrada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cerrada);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getCerrada() {
        return cerrada;
    }

    public void setCerrada(Byte cerrada) {
        this.cerrada = cerrada;
    }

    public Persona getPersonaByUsuario() {
        return personaByUsuario;
    }

    public void setPersonaByUsuario(Persona personaByUsuario) {
        this.personaByUsuario = personaByUsuario;
    }

    public Empleado getEmpleadoByAsistente() {
        return empleadoByAsistente;
    }

    public void setEmpleadoByAsistente(Empleado empleadoByAsistente) {
        this.empleadoByAsistente = empleadoByAsistente;
    }

    public Collection<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
