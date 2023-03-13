package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "conversacion", schema = "grupo17", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "CERRADA", nullable = false)
    private byte cerrada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getCerrada() {
        return cerrada;
    }

    public void setCerrada(byte cerrada) {
        this.cerrada = cerrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversacionEntity that = (ConversacionEntity) o;
        return id == that.id && cerrada == that.cerrada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cerrada);
    }
}
