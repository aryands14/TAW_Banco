package es.taw.grupo17.dao;

import es.taw.grupo17.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
}
