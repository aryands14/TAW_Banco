package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empresa", schema = "grupo17", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "CIF", nullable = false, length = 45)
    private String cif;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "CALLE", nullable = false, length = 45)
    private String calle;
    @Basic
    @Column(name = "NUMERO", nullable = false)
    private int numero;
    @Basic
    @Column(name = "PLANTA_PUERTA_OFICINA", nullable = false, length = 45)
    private String plantaPuertaOficina;
    @Basic
    @Column(name = "CIUDAD", nullable = false, length = 45)
    private String ciudad;
    @Basic
    @Column(name = "REGION", nullable = true, length = 45)
    private String region;
    @Basic
    @Column(name = "PAIS", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "CP", nullable = false, length = 45)
    private String cp;
    @Basic
    @Column(name = "VALIDA", nullable = false)
    private byte valida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPlantaPuertaOficina() {
        return plantaPuertaOficina;
    }

    public void setPlantaPuertaOficina(String plantaPuertaOficina) {
        this.plantaPuertaOficina = plantaPuertaOficina;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public byte getValida() {
        return valida;
    }

    public void setValida(byte valida) {
        this.valida = valida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntity that = (EmpresaEntity) o;
        return id == that.id && numero == that.numero && valida == that.valida && Objects.equals(cif, that.cif) && Objects.equals(nombre, that.nombre) && Objects.equals(calle, that.calle) && Objects.equals(plantaPuertaOficina, that.plantaPuertaOficina) && Objects.equals(ciudad, that.ciudad) && Objects.equals(region, that.region) && Objects.equals(pais, that.pais) && Objects.equals(cp, that.cp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cif, nombre, calle, numero, plantaPuertaOficina, ciudad, region, pais, cp, valida);
    }
}
