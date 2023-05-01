package es.taw.grupo17.dto;

import es.taw.grupo17.entity.CuentaEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;

/*
@author: Álvaro Bermúdez Gámez
 */
public class Sospechoso implements Serializable {
    private Integer id;
    private String descripcion;
    private Collection<Integer> cuentasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Integer> getCuentasById() {
        return cuentasById;
    }

    public void setCuentasById(Collection<Integer> cuentasById) {
        this.cuentasById = cuentasById;
    }
}
