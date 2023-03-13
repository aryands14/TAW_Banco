package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "grupo17", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "USUARIO", nullable = false, length = 45)
    private String usuario;
    @Basic
    @Column(name = "CONTRASEÑA", nullable = false, length = 45)
    private String contraseña;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        EmpleadoEntity that = (EmpleadoEntity) o;
        return id == that.id && Objects.equals(usuario, that.usuario) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, contraseña);
    }
}
