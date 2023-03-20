package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository  extends JpaRepository<EmpleadoEntity, Integer> {
}
