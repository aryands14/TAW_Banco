package es.taw.grupo17.entity;

import es.taw.grupo17.dto.DTO;
import es.taw.grupo17.dto.Empleado;
import es.taw.grupo17.dto.Tipoempleado;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
/*
@author: Álvaro Bermúdez Gámez 50%
@author: Sadhwani Sadhwani Aryan Dilip 50%
 */
@Entity
@Table(name = "tipoempleado", schema = "grupo17", catalog = "")
public class TipoempleadoEntity implements DTO<Tipoempleado> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipoempleadoByTipo")
    private Collection<EmpleadoEntity> empleadosById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoempleadoEntity that = (TipoempleadoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    public Collection<EmpleadoEntity> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Collection<EmpleadoEntity> empleadosById) {
        this.empleadosById = empleadosById;
    }

    @Override
    public Tipoempleado toDTO() {
        Tipoempleado dto = new Tipoempleado();
        dto.setId(this.getId());
        dto.setDescripcion(this.getDescripcion());

        List<Integer> empleados = new ArrayList<>();
        for (EmpleadoEntity empleadoEntity : this.getEmpleadosById()){
            empleados.add(empleadoEntity.getId());
        }
        dto.setEmpleadosById(empleados);

        return dto;
    }
}
