package es.taw.grupo17.entity;

import es.taw.grupo17.dto.DTO;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Estadopersona;
import es.taw.grupo17.dto.Persona;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estadopersona", schema = "grupo17", catalog = "")
public class EstadopersonaEntity implements DTO<Estadopersona> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "estadopersonaByEstado")
    private Collection<EmpresaEntity> empresasById;
    @OneToMany(mappedBy = "estadopersonaByEstado")
    private Collection<PersonaEntity> personasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadopersonaEntity that = (EstadopersonaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    public Collection<EmpresaEntity> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Collection<EmpresaEntity> empresasById) {
        this.empresasById = empresasById;
    }

    public Collection<PersonaEntity> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<PersonaEntity> personasById) {
        this.personasById = personasById;
    }

    public Estadopersona toDTO() {
        Estadopersona dto = new Estadopersona();
        dto.setId(this.getId());
        dto.setDescripcion(this.getDescripcion());

        List<Empresa> empresas = new ArrayList<>();
        for (EmpresaEntity empresa : this.getEmpresasById()){
            empresas.add(empresa.toDTO());
        }
        dto.setEmpresasById(empresas);

        List<Persona> personas = new ArrayList<>();
        for (PersonaEntity persona : this.getPersonasById()){
            personas.add(persona.toDTO());
        }

        dto.setPersonasById(personas);
        return dto;
    }
}
