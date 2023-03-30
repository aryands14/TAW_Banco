package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepository  extends JpaRepository<EmpresaEntity, Integer> {
    @Query("select e from EmpresaEntity e where e.estadopersonaByEstado.id = :id")
    public List<PersonaEntity> getPendientes(@Param("id") Integer id);

    @Query("select c from PersonaEntity c join OperacionEntity o on (o.cuentaByCuenta.id = c.cuentaByCuenta.id) where o.fechaInstruccion - sysdate()  > 30")
    public List<PersonaEntity> getInactivos();

}
