package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tipooperacion", schema = "grupo17", catalog = "")
public class TipooperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "DESCRIPCION", nullable = false, length = 45)
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
}
