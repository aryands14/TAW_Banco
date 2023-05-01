package es.taw.grupo17.dto;

import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import es.taw.grupo17.entity.TipooperacionEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Operacion implements Serializable {
    private Integer id;

    private Date fechaInstruccion;

    private Date fechaEjecucion;

    private String moneda;

    private String monedaCambio;

    private Double cantidadCambio;

    private Double cantidad;

    private Integer tipooperacionByTipo;

    private Integer personaByBeneficiario;

    private Integer cuentaByCuenta;

    public Operacion(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operacion that = (Operacion) o;
        return Objects.equals(id, that.id) && Objects.equals(fechaInstruccion, that.fechaInstruccion) && Objects.equals(fechaEjecucion, that.fechaEjecucion) && Objects.equals(moneda, that.moneda) && Objects.equals(monedaCambio, that.monedaCambio) && Objects.equals(cantidadCambio, that.cantidadCambio) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInstruccion, fechaEjecucion, moneda, monedaCambio, cantidadCambio, cantidad);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Date fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMonedaCambio() {
        return monedaCambio;
    }

    public void setMonedaCambio(String monedaCambio) {
        this.monedaCambio = monedaCambio;
    }

    public Double getCantidadCambio() {
        return cantidadCambio;
    }

    public void setCantidadCambio(Double cantidadCambio) {
        this.cantidadCambio = cantidadCambio;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTipooperacionByTipo() {
        return tipooperacionByTipo;
    }

    public void setTipooperacionByTipo(Integer tipooperacionByTipo) {
        this.tipooperacionByTipo = tipooperacionByTipo;
    }

    public Integer getPersonaByBeneficiario() {
        return personaByBeneficiario;
    }

    public void setPersonaByBeneficiario(Integer personaByBeneficiario) {
        this.personaByBeneficiario = personaByBeneficiario;
    }

    public Integer getCuentaByCuenta() {
        return cuentaByCuenta;
    }

    public void setCuentaByCuenta(Integer cuentaByCuenta) {
        this.cuentaByCuenta = cuentaByCuenta;
    }
}
