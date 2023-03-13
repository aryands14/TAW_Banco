package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "persona", schema = "grupo17", catalog = "")
public class PersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "NIF", nullable = false, length = 45)
    private String nif;
    @Basic
    @Column(name = "PRIMER_NOMBRE", nullable = false, length = 45)
    private String primerNombre;
    @Basic
    @Column(name = "SEGUNDO_NOMBRE", nullable = true, length = 45)
    private String segundoNombre;
    @Basic
    @Column(name = "PRIMER_APELLIDO", nullable = false, length = 45)
    private String primerApellido;
    @Basic
    @Column(name = "SEGUNDO_APELLIDO", nullable = true, length = 45)
    private String segundoApellido;
    @Basic
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;
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
    @Basic
    @Column(name = "CONTRASEÑA", nullable = false, length = 45)
    private String contraseña;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return id == that.id && numero == that.numero && valida == that.valida && Objects.equals(nif, that.nif) && Objects.equals(primerNombre, that.primerNombre) && Objects.equals(segundoNombre, that.segundoNombre) && Objects.equals(primerApellido, that.primerApellido) && Objects.equals(segundoApellido, that.segundoApellido) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(calle, that.calle) && Objects.equals(plantaPuertaOficina, that.plantaPuertaOficina) && Objects.equals(ciudad, that.ciudad) && Objects.equals(region, that.region) && Objects.equals(pais, that.pais) && Objects.equals(cp, that.cp) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, calle, numero, plantaPuertaOficina, ciudad, region, pais, cp, valida, contraseña);
    }
}
