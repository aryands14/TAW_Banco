package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sospechoso", schema = "grupo17", catalog = "")
public class SospechosoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "sospechosoBySospechoso")
    private Collection<CuentaEntity> cuentasById;

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
        SospechosoEntity that = (SospechosoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    public Collection<CuentaEntity> getCuentasById() {
        return cuentasById;
    }

    public void setCuentasById(Collection<CuentaEntity> cuentasById) {
        this.cuentasById = cuentasById;
    }
}
