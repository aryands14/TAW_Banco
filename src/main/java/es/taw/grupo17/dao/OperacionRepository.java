package es.taw.grupo17.dao;

import es.taw.grupo17.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("select o from OperacionEntity o where o.cuentaByCuenta.id = :id")
    public List<OperacionEntity> getOperaciones(@Param("id") Integer id);
}
