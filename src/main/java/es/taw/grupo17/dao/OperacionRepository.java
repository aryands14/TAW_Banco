package es.taw.grupo17.dao;

import es.taw.grupo17.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("select o from OperacionEntity o where o.cuentaByCuenta.id = :id")
    public List<OperacionEntity> getOperaciones(@Param("id") Integer id);

    @Query("select o from OperacionEntity o where o.fechaInstruccion >= :date and o.cuentaByCuenta.id = :cuenta")
    public Collection<OperacionEntity> getOperacionesFiltro1(@Param("date")Date date, @Param("cuenta") Integer cuenta);

    @Query("select o from OperacionEntity o where o.tipooperacionByTipo.id = :id and o.cuentaByCuenta.id = :cuenta")
    public Collection<OperacionEntity> getOperacionesFiltro2(@Param("id") Integer id , @Param("cuenta") Integer cuenta);

    @Query("select o from OperacionEntity o where o.tipooperacionByTipo.id = :id and o.fechaInstruccion >= :date and o.cuentaByCuenta.id = :cuenta")
    public Collection<OperacionEntity> getOperacionesFiltro3(@Param("id") Integer id, @Param("date") Date date, @Param("cuenta") Integer cuenta);




    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id")
    public List<OperacionEntity> getOperacionesByPersona(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id")
    public List<OperacionEntity> buscarPorTipoOperacionYPersona(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.cantidad")
    public List<OperacionEntity> getOperacionesOrdenadoCantidad(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.fechaInstruccion desc ")
    public List<OperacionEntity> getOperacionesOrdenadoFecha(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.fechaInstruccion desc, o.cantidad")
    public List<OperacionEntity> getOperacionesOrdenadoFechaYCantidad(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.cantidad")
    public List<OperacionEntity> buscarPorTipoOperacionYPersonaOrdenadoCantidad(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.fechaInstruccion desc")
    public List<OperacionEntity> buscarPorTipoOperacionYPersonaOrdenadoFecha(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

    @Query("select o from OperacionEntity o join EmpresaEntity e on e.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and e.id = :id")
    public List<OperacionEntity> buscarPorTipoOperacionYEmpresa(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

}
