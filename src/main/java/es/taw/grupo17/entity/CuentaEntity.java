package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cuenta", schema = "grupo17", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "NUMERO", nullable = true)
    private Integer numero;
    @Basic
    @Column(name = "FECHA_APERTURA", nullable = false)
    private Date fechaApertura;
    @Basic
    @Column(name = "FECHA_CIERRE", nullable = true)
    private Date fechaCierre;
    @Basic
    @Column(name = "SALDO", nullable = false, precision = 0)
    private double saldo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaEntity that = (CuentaEntity) o;
        return id == that.id && Double.compare(that.saldo, saldo) == 0 && Objects.equals(numero, that.numero) && Objects.equals(fechaApertura, that.fechaApertura) && Objects.equals(fechaCierre, that.fechaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, fechaApertura, fechaCierre, saldo);
    }
}
