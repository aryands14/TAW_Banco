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

    private Integer personaByUsuario;

    private Integer empleadoByAsistente;
    private Collection<Integer> mensajesById;

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

    public Integer getPersonaByUsuario() {
        return personaByUsuario;
    }

    public void setPersonaByUsuario(Integer personaByUsuario) {
        this.personaByUsuario = personaByUsuario;
    }

    public Integer getEmpleadoByAsistente() {
        return empleadoByAsistente;
    }

    public void setEmpleadoByAsistente(Integer empleadoByAsistente) {
        this.empleadoByAsistente = empleadoByAsistente;
    }

    public Collection<Integer> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Integer> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
