package es.taw.grupo17.dto;

import es.taw.grupo17.entity.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

/*
@author: Álvaro Bermúdez Gámez
 */
public class Cuenta implements Serializable {
    private Integer id;

    private Integer numero;

    private Date fechaApertura;

    private Date fechaCierre;

    private Double saldo;

    private Integer estadocuentaByEstado;

    private Collection<Integer> empresasById;

    private Collection<Integer> operacionsById;

    private Collection<Integer> personasById;

    private Integer sospechosoBySospechoso;

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

    public Integer getEstadocuentaByEstado() {
        return estadocuentaByEstado;
    }

    public void setEstadocuentaByEstado(Integer estadocuentaByEstado) {
        this.estadocuentaByEstado = estadocuentaByEstado;
    }

    public Collection<Integer> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Collection<Integer> empresasById) {
        this.empresasById = empresasById;
    }

    public Collection<Integer> getOperacionsById() {
        return operacionsById;
    }

    public void setOperacionsById(Collection<Integer> operacionsById) {
        this.operacionsById = operacionsById;
    }

    public Collection<Integer> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<Integer> personasById) {
        this.personasById = personasById;
    }

    public Integer getSospechosoBySospechoso() {
        return sospechosoBySospechoso;
    }

    public void setSospechosoBySospechoso(Integer sospechosoBySospechoso) {
        this.sospechosoBySospechoso = sospechosoBySospechoso;
    }
}
