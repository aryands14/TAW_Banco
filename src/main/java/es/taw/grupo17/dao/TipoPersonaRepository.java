package es.taw.grupo17.dao;

import es.taw.grupo17.entity.TipopersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
@author: Álvaro Bermúdez Gámez
 */
public interface TipoPersonaRepository extends JpaRepository<TipopersonaEntity,Integer> {
}
