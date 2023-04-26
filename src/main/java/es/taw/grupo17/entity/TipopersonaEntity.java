package es.taw.grupo17.entity;

import es.taw.grupo17.dto.DTO;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.dto.Tipopersona;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tipopersona", schema = "grupo17", catalog = "")
public class TipopersonaEntity implements DTO<Tipopersona> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipopersonaByTipo")
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
        TipopersonaEntity that = (TipopersonaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    public Collection<PersonaEntity> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<PersonaEntity> personasById) {
        this.personasById = personasById;
    }

    public Tipopersona toDTO() {
        Tipopersona dto = new Tipopersona();
        dto.setId(this.getId());
        dto.setDescripcion(this.getDescripcion());

        List<Integer> personas = new ArrayList<>();
        for (PersonaEntity personaEntity : this.getPersonasById()){
            personas.add(personaEntity.getId());
        }
        dto.setPersonasById(personas);

        return dto;
    }
}
