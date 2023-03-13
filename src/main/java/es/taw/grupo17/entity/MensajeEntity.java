package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "mensaje", schema = "grupo17", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "CONTENIDO", nullable = false, length = 45)
    private String contenido;
    @Basic
    @Column(name = "FECHA", nullable = false)
    private Date fecha;
    @Basic
    @Column(name = "HORA", nullable = false)
    private Time hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntity that = (MensajeEntity) o;
        return id == that.id && Objects.equals(contenido, that.contenido) && Objects.equals(fecha, that.fecha) && Objects.equals(hora, that.hora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contenido, fecha, hora);
    }
}
