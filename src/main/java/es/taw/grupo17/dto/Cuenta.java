package es.taw.grupo17.dto;

import es.taw.grupo17.entity.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

public class Cuenta implements Serializable {
    private Integer id;

    private Integer numero;

    private Date fechaApertura;

    private Date fechaCierre;

    private Double saldo;

    private Estadocuenta estadocuentaByEstado;

    private Collection<Empresa> empresasById;

    private Collection<Operacion> operacionsById;

    private Collection<Persona> personasById;

    public Cuenta(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta that = (Cuenta) o;
        return Objects.equals(id, that.id) && Objects.equals(numero, that.numero) && Objects.equals(fechaApertura, that.fechaApertura) && Objects.equals(fechaCierre, that.fechaCierre) && Objects.equals(saldo, that.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, fechaApertura, fechaCierre, saldo);
    }

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

    public Estadocuenta getEstadocuentaByEstado() {
        return estadocuentaByEstado;
    }

    public void setEstadocuentaByEstado(Estadocuenta estadocuentaByEstado) {
        this.estadocuentaByEstado = estadocuentaByEstado;
    }

    public Collection<Empresa> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Collection<Empresa> empresasById) {
        this.empresasById = empresasById;
    }

    public Collection<Operacion> getOperacionsById() {
        return operacionsById;
    }

    public void setOperacionsById(Collection<Operacion> operacionsById) {
        this.operacionsById = operacionsById;
    }

    public Collection<Persona> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<Persona> personasById) {
        this.personasById = personasById;
    }
}
