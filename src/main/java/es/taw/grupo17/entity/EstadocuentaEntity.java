package es.taw.grupo17.entity;

import es.taw.grupo17.dto.Cuenta;
import es.taw.grupo17.dto.DTO;
import es.taw.grupo17.dto.Estadocuenta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estadocuenta", schema = "grupo17", catalog = "")
public class EstadocuentaEntity implements DTO<Estadocuenta> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "estadocuentaByEstado")
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
        EstadocuentaEntity that = (EstadocuentaEntity) o;
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

    public Estadocuenta toDTO() {
        Estadocuenta dto = new Estadocuenta();
        dto.setId(this.getId());
        dto.setDescripcion(this.getDescripcion());

        List<Cuenta> cuentas = new ArrayList<>();
        for (CuentaEntity cuentaEntity : this.getCuentasById()){
            cuentas.add(cuentaEntity.toDTO());
        }
        dto.setCuentasById(cuentas);

        return dto;
    }
}
