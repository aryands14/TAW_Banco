package es.taw.grupo17.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "empresa", schema = "grupo17", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "CIF")
    private String cif;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "CALLE")
    private String calle;
    @Basic
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic
    @Column(name = "PLANTA_PUERTA_OFICINA")
    private String plantaPuertaOficina;
    @Basic
    @Column(name = "CIUDAD")
    private String ciudad;
    @Basic
    @Column(name = "REGION")
    private String region;
    @Basic
    @Column(name = "PAIS")
    private String pais;
    @Basic
    @Column(name = "CP")
    private String cp;
    @Basic
    @Column(name = "VALIDA")
    private Byte valida;
    @Basic
    @Column(name = "CONTRASEÑA")
    private String contraseña;
    @ManyToOne
    @JoinColumn(name = "CUENTA", referencedColumnName = "ID")
    private CuentaEntity cuentaByCuenta;
    @ManyToOne
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID", nullable = false)
    private EstadopersonaEntity estadopersonaByEstado;
    @OneToMany(mappedBy = "empresaByEmpresa")
    private Collection<PersonaEntity> personasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        EmpresaEntity empresa = (EmpresaEntity) o;
        return Objects.equals(id, empresa.id) && Objects.equals(cif, empresa.cif) && Objects.equals(nombre, empresa.nombre) && Objects.equals(calle, empresa.calle) && Objects.equals(numero, empresa.numero) && Objects.equals(plantaPuertaOficina, empresa.plantaPuertaOficina) && Objects.equals(ciudad, empresa.ciudad) && Objects.equals(region, empresa.region) && Objects.equals(pais, empresa.pais) && Objects.equals(cp, empresa.cp) && Objects.equals(valida, empresa.valida) && Objects.equals(contraseña, empresa.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cif, nombre, calle, numero, plantaPuertaOficina, ciudad, region, pais, cp, valida, contraseña);
    }

    public CuentaEntity getCuentaByCuenta() {
        return cuentaByCuenta;
    }

    public void setCuentaByCuenta(CuentaEntity cuentaByCuenta) {
        this.cuentaByCuenta = cuentaByCuenta;
    }

    public EstadopersonaEntity getEstadopersonaByEstado() {
        return estadopersonaByEstado;
    }

    public void setEstadopersonaByEstado(EstadopersonaEntity estadopersonaByEstado) {
        this.estadopersonaByEstado = estadopersonaByEstado;
    }

    public Collection<PersonaEntity> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<PersonaEntity> personasById) {
        this.personasById = personasById;
    }
}
