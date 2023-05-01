package es.taw.grupo17.dto;

import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.TipooperacionEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Tipooperacion implements Serializable {

    private Integer id;

    private String descripcion;

    private Collection<Integer> operacionsById;


    public Tipooperacion(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipooperacion that = (Tipooperacion) o;
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

    public Collection<Integer> getOperacionsById() {
        return operacionsById;
    }

    public void setOperacionsById(Collection<Integer> operacionsById) {
        this.operacionsById = operacionsById;
    }

}
