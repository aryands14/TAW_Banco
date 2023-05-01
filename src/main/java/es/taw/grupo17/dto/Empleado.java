package es.taw.grupo17.dto;

import es.taw.grupo17.entity.ConversacionEntity;
import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.entity.TipoempleadoEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Empleado implements Serializable {

    private Integer id;

    private String usuario;

    private String contraseña;
    private Collection<Integer> conversacionsById;
    private Integer tipoempleadoByTipo;

    public Empleado(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado that = (Empleado) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, contraseña);
    }

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

    public Collection<Integer> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<Integer> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Integer getTipoempleadoByTipo() {
        return tipoempleadoByTipo;
    }

    public void setTipoempleadoByTipo(Integer tipoempleadoByTipo) {
        this.tipoempleadoByTipo = tipoempleadoByTipo;
    }
}
