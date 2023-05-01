package es.taw.grupo17.dto;


import es.taw.grupo17.entity.EstadopersonaEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/*
@author: Álvaro Bermúdez Gámez
 */
public class Estadopersona implements Serializable {
    private Integer id;

    private String descripcion;

    private Collection<Integer> empresasById;

    private Collection<Integer> personasById;


    public Estadopersona(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estadopersona that = (Estadopersona) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

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

    public Collection<Integer> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Collection<Integer> empresasById) {
        this.empresasById = empresasById;
    }

    public Collection<Integer> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<Integer> personasById) {
        this.personasById = personasById;

    }
}
