package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "operacion", schema = "grupo17", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "FECHA_INSTRUCCION", nullable = false)
    private Date fechaInstruccion;
    @Basic
    @Column(name = "FECHA_EJECUCION", nullable = true)
    private Date fechaEjecucion;
    @Basic
    @Column(name = "MONEDA", nullable = false, length = 45)
    private String moneda;
    @Basic
    @Column(name = "MONEDA_CAMBIO", nullable = true, length = 45)
    private String monedaCambio;
    @Basic
    @Column(name = "CANTIDAD_CAMBIO", nullable = true, precision = 0)
    private Double cantidadCambio;
    @Basic
    @Column(name = "CANTIDAD", nullable = false, precision = 0)
    private double cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionEntity that = (OperacionEntity) o;
        return id == that.id && Double.compare(that.cantidad, cantidad) == 0 && Objects.equals(fechaInstruccion, that.fechaInstruccion) && Objects.equals(fechaEjecucion, that.fechaEjecucion) && Objects.equals(moneda, that.moneda) && Objects.equals(monedaCambio, that.monedaCambio) && Objects.equals(cantidadCambio, that.cantidadCambio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInstruccion, fechaEjecucion, moneda, monedaCambio, cantidadCambio, cantidad);
    }
}
