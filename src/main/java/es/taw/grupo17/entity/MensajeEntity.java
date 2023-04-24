package es.taw.grupo17.entity;

import es.taw.grupo17.dto.Conversacion;
import es.taw.grupo17.dto.DTO;
import es.taw.grupo17.dto.Mensaje;
import es.taw.grupo17.dto.Persona;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "mensaje", schema = "grupo17", catalog = "")
public class MensajeEntity implements DTO<Mensaje> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "CONTENIDO")
    private String contenido;
    @Basic
    @Column(name = "FECHA")
    private Date fecha;
    @Basic
    @Column(name = "HORA")
    private Time hora;
    @ManyToOne
    @JoinColumn(name = "CONVERSACION", referencedColumnName = "ID", nullable = false)
    private ConversacionEntity conversacionByConversacion;
    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    private PersonaEntity personaByUsuario;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntity that = (MensajeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(contenido, that.contenido) && Objects.equals(fecha, that.fecha) && Objects.equals(hora, that.hora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contenido, fecha, hora);
    }

    public ConversacionEntity getConversacionByConversacion() {
        return conversacionByConversacion;
    }

    public void setConversacionByConversacion(ConversacionEntity conversacionByConversacion) {
        this.conversacionByConversacion = conversacionByConversacion;
    }

    public PersonaEntity getPersonaByUsuario() {
        return personaByUsuario;
    }

    public void setPersonaByUsuario(PersonaEntity personaByUsuario) {
        this.personaByUsuario = personaByUsuario;
    }

    public Mensaje toDTO() {
        Mensaje dto = new Mensaje();

        dto.setId(this.getId());
        dto.setContenido(this.getContenido());
        dto.setFecha(this.getFecha());
        dto.setHora(this.getHora());
        dto.setConversacionByConversacion(this.getConversacionByConversacion().toDTO());
        dto.setPersonaByUsuario(this.getPersonaByUsuario().toDTO());

        return dto;
    }
}
