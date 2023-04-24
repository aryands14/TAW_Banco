package es.taw.grupo17.dto;

import es.taw.grupo17.entity.TipopersonaEntity;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Tipopersona implements Serializable {
    private Integer id;

    private String descripcion;

    private Collection<Persona> personasById;

    public Tipopersona(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipopersona that = (Tipopersona) o;
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

    public Collection<Persona> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<Persona> personasById) {
        this.personasById = personasById;
    }
}
