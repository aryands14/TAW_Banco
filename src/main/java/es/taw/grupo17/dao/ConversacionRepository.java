package es.taw.grupo17.dao;

import es.taw.grupo17.entity.ConversacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<ConversacionEntity,Integer> {
}
