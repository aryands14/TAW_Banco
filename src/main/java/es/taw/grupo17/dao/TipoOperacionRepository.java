package es.taw.grupo17.dao;

import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.TipooperacionEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoOperacionRepository extends JpaRepository<TipooperacionEntity, Integer> {
}
