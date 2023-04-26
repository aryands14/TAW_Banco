package es.taw.grupo17.dao;

import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository  extends JpaRepository<CuentaEntity, Integer> {
    @Query("select c from CuentaEntity c where c.sospechosoBySospechoso.id = 1")
    public List<CuentaEntity> getSospechosos();
}
