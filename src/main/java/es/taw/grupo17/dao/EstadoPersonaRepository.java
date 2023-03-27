package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EstadopersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoPersonaRepository extends JpaRepository<EstadopersonaEntity,Integer> {
}
