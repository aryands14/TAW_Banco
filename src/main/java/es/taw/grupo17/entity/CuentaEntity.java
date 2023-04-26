package es.taw.grupo17.entity;

import es.taw.grupo17.dto.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cuenta", schema = "grupo17", catalog = "")
public class CuentaEntity implements DTO<Cuenta> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic
    @Column(name = "FECHA_APERTURA")
    private Date fechaApertura;
    @Basic
    @Column(name = "FECHA_CIERRE")
    private Date fechaCierre;
    @Basic
    @Column(name = "SALDO")
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID", nullable = false)
    private EstadocuentaEntity estadocuentaByEstado;
    @OneToMany(mappedBy = "cuentaByCuenta")
    private Collection<EmpresaEntity> empresasById;
    @OneToMany(mappedBy = "cuentaByCuenta")
    private Collection<OperacionEntity> operacionsById;
    @OneToMany(mappedBy = "cuentaByCuenta")
    private Collection<PersonaEntity> personasById;
    @ManyToOne
    @JoinColumn(name = "SOSPECHOSO", referencedColumnName = "id")
    private SospechosoEntity sospechosoBySospechoso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaEntity that = (CuentaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(numero, that.numero) && Objects.equals(fechaApertura, that.fechaApertura) && Objects.equals(fechaCierre, that.fechaCierre) && Objects.equals(saldo, that.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, fechaApertura, fechaCierre, saldo);
    }

    public EstadocuentaEntity getEstadocuentaByEstado() {
        return estadocuentaByEstado;
    }

    public void setEstadocuentaByEstado(EstadocuentaEntity estadocuentaByEstado) {
        this.estadocuentaByEstado = estadocuentaByEstado;
    }

    public Collection<EmpresaEntity> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Collection<EmpresaEntity> empresasById) {
        this.empresasById = empresasById;
    }

    public Collection<OperacionEntity> getOperacionsById() {
        return operacionsById;
    }

    public void setOperacionsById(Collection<OperacionEntity> operacionsById) {
        this.operacionsById = operacionsById;
    }

    public Collection<PersonaEntity> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<PersonaEntity> personasById) {
        this.personasById = personasById;
    }

    public SospechosoEntity getSospechosoBySospechoso() {
        return sospechosoBySospechoso;
    }

    public void setSospechosoBySospechoso(SospechosoEntity sospechosoBySospechoso) {
        this.sospechosoBySospechoso = sospechosoBySospechoso;
    }

    public Cuenta toDTO() {
        Cuenta dto = new Cuenta();
        dto.setId(this.getId());
        dto.setNumero(this.getNumero());
        dto.setFechaApertura(this.getFechaApertura());
        dto.setFechaCierre(this.getFechaCierre());
        dto.setSaldo(this.getSaldo());
        dto.setEstadocuentaByEstado(this.getEstadocuentaByEstado().getId());

        List<Integer> empresas = new ArrayList<>();
        for (EmpresaEntity empresaEntity : this.getEmpresasById()){
            empresas.add(empresaEntity.getId());
        }
        dto.setEmpresasById(empresas);

        List<Integer> operaciones = new ArrayList<>();
        for (OperacionEntity operacionEntity : this.getOperacionsById()){
            operaciones.add(operacionEntity.getId());
        }
        dto.setOperacionsById(operaciones);

        List<Integer> personas = new ArrayList<>();
        for (PersonaEntity personaEntity : this.getPersonasById()){
            personas.add(personaEntity.getId());
        }
        dto.setPersonasById(personas);

        dto.setSospechosoBySospechoso(this.getSospechosoBySospechoso().getId());

        return dto;
    }
}
