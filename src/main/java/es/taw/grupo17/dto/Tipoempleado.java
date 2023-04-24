package es.taw.grupo17.dto;

import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.entity.TipoempleadoEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Tipoempleado implements Serializable {

    private Integer id;
    private String descripcion;
    private Collection<Empleado> empleadosById;

    public Tipoempleado(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipoempleado that = (Tipoempleado) o;
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

    public Collection<Empleado> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Collection<Empleado> empleadosById) {
        this.empleadosById = empleadosById;
    }
}
