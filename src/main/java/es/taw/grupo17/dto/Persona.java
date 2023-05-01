package es.taw.grupo17.dto;

import es.taw.grupo17.entity.PersonaEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

public class Persona implements Serializable {

    private Integer id;

    private String nif;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private Date fechaNacimiento;

    private String calle;

    private Integer numero;

    private String plantaPuertaOficina;

    private String ciudad;

    private String region;

    private String pais;

    private String cp;

    private Byte valida;

    private String contraseña;

    private Collection<Integer> conversacionsById;

    private Collection<Integer> mensajesById;

    private Collection<Integer> operacionsById;

    private Integer tipopersonaByTipo;

    private Integer cuentaByCuenta;

    private Integer empresaByEmpresa;

    private Integer estadopersonaByEstado;

    public Persona(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona that = (Persona) o;
        return Objects.equals(id, that.id) && Objects.equals(nif, that.nif) && Objects.equals(primerNombre, that.primerNombre) && Objects.equals(segundoNombre, that.segundoNombre) && Objects.equals(primerApellido, that.primerApellido) && Objects.equals(segundoApellido, that.segundoApellido) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(calle, that.calle) && Objects.equals(numero, that.numero) && Objects.equals(plantaPuertaOficina, that.plantaPuertaOficina) && Objects.equals(ciudad, that.ciudad) && Objects.equals(region, that.region) && Objects.equals(pais, that.pais) && Objects.equals(cp, that.cp) && Objects.equals(valida, that.valida) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, calle, numero, plantaPuertaOficina, ciudad, region, pais, cp, valida, contraseña);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Collection<Integer> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<Integer> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Collection<Integer> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Integer> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Collection<Integer> getOperacionsById() {
        return operacionsById;
    }

    public void setOperacionsById(Collection<Integer> operacionsById) {
        this.operacionsById = operacionsById;
    }

    public Integer getTipopersonaByTipo() {
        return tipopersonaByTipo;
    }

    public void setTipopersonaByTipo(Integer tipopersonaByTipo) {
        this.tipopersonaByTipo = tipopersonaByTipo;
    }

    public Integer getCuentaByCuenta() {
        return cuentaByCuenta;
    }

    public void setCuentaByCuenta(Integer cuentaByCuenta) {
        this.cuentaByCuenta = cuentaByCuenta;
    }

    public Integer getEmpresaByEmpresa() {
        return empresaByEmpresa;
    }

    public void setEmpresaByEmpresa(Integer empresaByEmpresa) {
        this.empresaByEmpresa = empresaByEmpresa;
    }

    public Integer getEstadopersonaByEstado() {
        return estadopersonaByEstado;
    }

    public void setEstadopersonaByEstado(Integer estadopersonaByEstado) {
        this.estadopersonaByEstado = estadopersonaByEstado;
    }
}
