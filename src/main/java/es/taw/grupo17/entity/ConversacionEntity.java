package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "conversacion", schema = "grupo17", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "CERRADA", nullable = false)
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
}
