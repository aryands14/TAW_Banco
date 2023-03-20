package es.taw.grupo17.dao;

import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository  extends JpaRepository<CuentaEntity, Integer> {
}
