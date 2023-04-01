package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepository  extends JpaRepository<EmpresaEntity, Integer> {
    @Query("select e from EmpresaEntity e where e.estadopersonaByEstado.id = :id")
    public List<EmpresaEntity> getPendientes(@Param("id") Integer id);

    @Query("select p from EmpresaEntity p left join OperacionEntity o on (o.cuentaByCuenta.id = p.cuentaByCuenta.id) group by p.id  HAVING MAX(o.fechaInstruccion) IS NULL OR DATEDIFF(CURDATE(), MAX(o.fechaInstruccion)) > 30")
    public List<EmpresaEntity> getInactivos();

}
