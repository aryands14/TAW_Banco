package es.taw.grupo17.entity;

import es.taw.grupo17.dto.DTO;
import es.taw.grupo17.dto.Sospechoso;
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
@Table(name = "sospechoso", schema = "grupo17", catalog = "")
public class SospechosoEntity implements DTO<Sospechoso> {
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

    @Override
    public Sospechoso toDTO() {
        Sospechoso dto = new Sospechoso();

        dto.setId(this.getId());
        dto.setDescripcion(this.getDescripcion());

        List<Integer> cuentas = new ArrayList<>();
        for (CuentaEntity cuentaEntity : this.getCuentasById()){
            cuentas.add(cuentaEntity.getId());
        }
        dto.setCuentasById(cuentas);
        return dto;
    }
}
