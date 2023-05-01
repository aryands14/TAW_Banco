package es.taw.grupo17.dto;

import es.taw.grupo17.entity.ConversacionEntity;
import es.taw.grupo17.entity.MensajeEntity;
import es.taw.grupo17.entity.PersonaEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/*
@author: Álvaro Bermúdez Gámez
 */
public class Mensaje implements Serializable {
    private Integer id;

    private String contenido;

    private Date fecha;

    private Time hora;

    private Integer conversacionByConversacion;

    private Integer personaByUsuario;

    public Mensaje(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje that = (Mensaje) o;
        return Objects.equals(id, that.id) && Objects.equals(contenido, that.contenido) && Objects.equals(fecha, that.fecha) && Objects.equals(hora, that.hora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contenido, fecha, hora);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Integer getConversacionByConversacion() {
        return conversacionByConversacion;
    }

    public void setConversacionByConversacion(Integer conversacionByConversacion) {
        this.conversacionByConversacion = conversacionByConversacion;
    }

    public Integer getPersonaByUsuario() {
        return personaByUsuario;
    }

    public void setPersonaByUsuario(Integer personaByUsuario) {
        this.personaByUsuario = personaByUsuario;
    }
}
