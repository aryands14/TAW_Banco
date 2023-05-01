package es.taw.grupo17.entity;

import es.taw.grupo17.dto.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
/*
@author: Álvaro Bermúdez Gámez 50%
@author: Sadhwani Sadhwani Aryan Dilip 50%
 */
@Entity
@Table(name = "conversacion", schema = "grupo17", catalog = "")
public class ConversacionEntity implements DTO<Conversacion> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "CERRADA")
    private Byte cerrada;
    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    private PersonaEntity personaByUsuario;
    @ManyToOne
    @JoinColumn(name = "ASISTENTE", referencedColumnName = "ID", nullable = false)
    private EmpleadoEntity empleadoByAsistente;
    @OneToMany(mappedBy = "conversacionByConversacion")
    private Collection<MensajeEntity> mensajesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversacionEntity that = (ConversacionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cerrada, that.cerrada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cerrada);
    }

    public PersonaEntity getPersonaByUsuario() {
        return personaByUsuario;
    }

    public void setPersonaByUsuario(PersonaEntity personaByUsuario) {
        this.personaByUsuario = personaByUsuario;
    }

    public EmpleadoEntity getEmpleadoByAsistente() {
        return empleadoByAsistente;
    }

    public void setEmpleadoByAsistente(EmpleadoEntity empleadoByAsistente) {
        this.empleadoByAsistente = empleadoByAsistente;
    }

    public Collection<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Conversacion toDTO() {
        Conversacion dto = new Conversacion();

        dto.setId(this.getId());
        dto.setCerrada(this.getCerrada());
        dto.setPersonaByUsuario(this.getPersonaByUsuario().getId());
        dto.setEmpleadoByAsistente(this.getEmpleadoByAsistente().getId());

        List<Integer> mensajes = new ArrayList<>();
        for(MensajeEntity mensajeEntity : this.getMensajesById()){
            mensajes.add(mensajeEntity.getId());
        }
        dto.setMensajesById(mensajes);
        return dto;
    }
}
