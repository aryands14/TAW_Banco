package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "empresa", schema = "grupo17", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "CALLE", nullable = false, length = 45)
    private String calle;
    @Basic
    @Column(name = "NUMERO", nullable = false)
    private Integer numero;
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
    private Byte valida;
    @Basic
    @Column(name = "CONTRASEÑA", nullable = false, length = 45)
    private String contraseña;
    @ManyToOne
    @JoinColumn(name = "CUENTA", referencedColumnName = "ID")
    private CuentaEntity cuentaByCuenta;
    @OneToMany(mappedBy = "empresaByEmpresa")
    private Collection<PersonaEntity> personasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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

    public Byte getValida() {
        return valida;
    }

    public void setValida(Byte valida) {
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
        EmpresaEntity that = (EmpresaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(calle, that.calle) && Objects.equals(numero, that.numero) && Objects.equals(plantaPuertaOficina, that.plantaPuertaOficina) && Objects.equals(ciudad, that.ciudad) && Objects.equals(region, that.region) && Objects.equals(pais, that.pais) && Objects.equals(cp, that.cp) && Objects.equals(valida, that.valida) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, calle, numero, plantaPuertaOficina, ciudad, region, pais, cp, valida, contraseña);
    }

    public CuentaEntity getCuentaByCuenta() {
        return cuentaByCuenta;
    }

    public void setCuentaByCuenta(CuentaEntity cuentaByCuenta) {
        this.cuentaByCuenta = cuentaByCuenta;
    }

    public Collection<PersonaEntity> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<PersonaEntity> personasById) {
        this.personasById = personasById;
    }
}
