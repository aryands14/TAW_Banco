package es.taw.grupo17.dto;

import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.EstadopersonaEntity;
import es.taw.grupo17.entity.PersonaEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/*
@author: Álvaro Bermúdez Gámez
 */
public class Empresa implements Serializable {
    private Integer id;

    private String cif;

    private String nombre;

    private String calle;

    private Integer numero;

    private String plantaPuertaOficina;

    private String ciudad;

    private String region;

    private String pais;

    private String cp;

    private Byte valida;

    private String contraseña;

    private Integer cuentaByCuenta;

    private Integer estadopersonaByEstado;

    private List<Integer> personasById;

    public Empresa(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id) && Objects.equals(cif, empresa.cif) && Objects.equals(nombre, empresa.nombre) && Objects.equals(calle, empresa.calle) && Objects.equals(numero, empresa.numero) && Objects.equals(plantaPuertaOficina, empresa.plantaPuertaOficina) && Objects.equals(ciudad, empresa.ciudad) && Objects.equals(region, empresa.region) && Objects.equals(pais, empresa.pais) && Objects.equals(cp, empresa.cp) && Objects.equals(valida, empresa.valida) && Objects.equals(contraseña, empresa.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cif, nombre, calle, numero, plantaPuertaOficina, ciudad, region, pais, cp, valida, contraseña);
    }

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

    public Integer getCuentaByCuenta() {
        return cuentaByCuenta;
    }

    public void setCuentaByCuenta(Integer cuentaByCuenta) {
        this.cuentaByCuenta = cuentaByCuenta;
    }

    public Integer getEstadopersonaByEstado() {
        return estadopersonaByEstado;
    }

    public void setEstadopersonaByEstado(Integer estadopersonaByEstado) {
        this.estadopersonaByEstado = estadopersonaByEstado;
    }

    public List<Integer> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(List<Integer> personasById) {
        this.personasById = personasById;
    }
}
