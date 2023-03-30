package es.taw.grupo17.dao;

import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.entity.PersonaEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    @Query("select c from PersonaEntity c where c.estadopersonaByEstado.id = :id")
    public List<PersonaEntity> getPendientes(@Param("id") Integer id);

    @Query("select c from PersonaEntity c join OperacionEntity o on (o.cuentaByCuenta.id = c.cuentaByCuenta.id) where o.fechaInstruccion - sysdate()  > 30")
    public List<PersonaEntity> getInactivos();

 //   @Query("select c from PersonaEntity c where c.cuentaByCuenta.id in sospechosos")
 //   public List<PersonaEntity> getSospechosos(@Param("sospechosos") List<CuentaEntity> sospechosos);

}
