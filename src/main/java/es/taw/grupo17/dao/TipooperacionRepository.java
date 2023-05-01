package es.taw.grupo17.dao;

import es.taw.grupo17.dto.Tipooperacion;
import es.taw.grupo17.entity.TipooperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipooperacionRepository extends JpaRepository<TipooperacionEntity,Integer> {
}
