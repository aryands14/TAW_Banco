package es.taw.grupo17.entity;

import es.taw.grupo17.dto.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "operacion", schema = "grupo17", catalog = "")
public class OperacionEntity  implements DTO<Operacion> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "FECHA_INSTRUCCION")
    private Date fechaInstruccion;
    @Basic
    @Column(name = "FECHA_EJECUCION")
    private Date fechaEjecucion;
    @Basic
    @Column(name = "MONEDA")
    private String moneda;
    @Basic
    @Column(name = "MONEDA_CAMBIO")
    private String monedaCambio;
    @Basic
    @Column(name = "CANTIDAD_CAMBIO")
    private Double cantidadCambio;
    @Basic
    @Column(name = "CANTIDAD")
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name = "TIPO", referencedColumnName = "ID", nullable = false)
    private TipooperacionEntity tipooperacionByTipo;
    @ManyToOne
    @JoinColumn(name = "BENEFICIARIO", referencedColumnName = "ID")
    private PersonaEntity personaByBeneficiario;
    @ManyToOne
    @JoinColumn(name = "CUENTA", referencedColumnName = "ID", nullable = false)
    private CuentaEntity cuentaByCuenta;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionEntity that = (OperacionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fechaInstruccion, that.fechaInstruccion) && Objects.equals(fechaEjecucion, that.fechaEjecucion) && Objects.equals(moneda, that.moneda) && Objects.equals(monedaCambio, that.monedaCambio) && Objects.equals(cantidadCambio, that.cantidadCambio) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInstruccion, fechaEjecucion, moneda, monedaCambio, cantidadCambio, cantidad);
    }

    public TipooperacionEntity getTipooperacionByTipo() {
        return tipooperacionByTipo;
    }

    public void setTipooperacionByTipo(TipooperacionEntity tipooperacionByTipo) {
        this.tipooperacionByTipo = tipooperacionByTipo;
    }

    public PersonaEntity getPersonaByBeneficiario() {
        return personaByBeneficiario;
    }

    public void setPersonaByBeneficiario(PersonaEntity personaByBeneficiario) {
        this.personaByBeneficiario = personaByBeneficiario;
    }

    public CuentaEntity getCuentaByCuenta() {
        return cuentaByCuenta;
    }

    public void setCuentaByCuenta(CuentaEntity cuentaByCuenta) {
        this.cuentaByCuenta = cuentaByCuenta;
    }

    public Operacion toDTO() {
        Operacion dto = new Operacion();

        dto.setId(this.getId());
        dto.setFechaInstruccion(this.getFechaInstruccion());
        dto.setFechaEjecucion(this.getFechaEjecucion());
        dto.setMoneda(this.getMoneda());
        dto.setMonedaCambio(this.getMonedaCambio());
        dto.setCantidadCambio(this.getCantidadCambio());
        dto.setCantidad(this.getCantidad());
        dto.setTipooperacionByTipo(this.getTipooperacionByTipo().toDTO());
        dto.setPersonaByBeneficiario(this.getPersonaByBeneficiario().toDTO());
        dto.setCuentaByCuenta(this.getCuentaByCuenta().toDTO());

        return dto;
    }


}
