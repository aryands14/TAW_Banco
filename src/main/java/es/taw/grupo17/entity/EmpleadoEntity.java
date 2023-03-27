package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "grupo17", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "USUARIO", nullable = false, length = 45)
    private String usuario;
    @Basic
    @Column(name = "CONTRASEÑA", nullable = false, length = 45)
    private String contraseña;
    @OneToMany(mappedBy = "empleadoByAsistente")
    private Collection<ConversacionEntity> conversacionsById;
    @ManyToOne
    @JoinColumn(name = "TIPO", referencedColumnName = "ID", nullable = false)
    private TipoempleadoEntity tipoempleadoByTipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoEntity empleado = (EmpleadoEntity) o;
        return Objects.equals(id, empleado.id) && Objects.equals(usuario, empleado.usuario) && Objects.equals(contraseña, empleado.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, contraseña);
    }

    public Collection<ConversacionEntity> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<ConversacionEntity> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public TipoempleadoEntity getTipoempleadoByTipo() {
        return tipoempleadoByTipo;
    }

    public void setTipoempleadoByTipo(TipoempleadoEntity tipoempleadoByTipo) {
        this.tipoempleadoByTipo = tipoempleadoByTipo;
    }
}
