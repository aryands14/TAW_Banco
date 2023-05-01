package es.taw.grupo17.entity;

import es.taw.grupo17.dto.DTO;
import es.taw.grupo17.dto.Operacion;
import es.taw.grupo17.dto.Tipooperacion;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tipooperacion", schema = "grupo17", catalog = "")
public class TipooperacionEntity implements DTO<Tipooperacion> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipooperacionByTipo")
    private Collection<OperacionEntity> operacionsById;

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
        TipooperacionEntity that = (TipooperacionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    public Collection<OperacionEntity> getOperacionsById() {
        return operacionsById;
    }

    public void setOperacionsById(Collection<OperacionEntity> operacionsById) {
        this.operacionsById = operacionsById;
    }

    @Override
    public Tipooperacion toDTO() {
        Tipooperacion dto = new Tipooperacion();
        dto.setId(this.getId());
        dto.setDescripcion(this.getDescripcion());

        List<Integer> operaciones = new ArrayList<>();
        for (OperacionEntity operacionEntity : this.getOperacionsById()){
            operaciones.add(operacionEntity.getId());
        }
        dto.setOperacionsById(operaciones);

        return dto;
    }
}
