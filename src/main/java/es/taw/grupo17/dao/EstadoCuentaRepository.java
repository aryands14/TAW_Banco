package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EstadocuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCuentaRepository extends JpaRepository<EstadocuentaEntity, Integer> {
}
