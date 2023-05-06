package es.taw.grupo17.dao;

import es.taw.grupo17.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
/*
@author: Aryan Dilip Sadhwani Sadhwani 50%
@author: Francisco Javier Tejada Martín 50%
 */

public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("select o from OperacionEntity o where o.cuentaByCuenta.id = :id")
    public List<OperacionEntity> getOperaciones(@Param("id") Integer id);

    @Query("select o from OperacionEntity o where o.fechaInstruccion >= :date and o.cuentaByCuenta.id = :cuenta")
    public List<OperacionEntity> getOperacionesFiltro1(@Param("date")Date date, @Param("cuenta") Integer cuenta);

    @Query("select o from OperacionEntity o where o.tipooperacionByTipo.id = :id and o.cuentaByCuenta.id = :cuenta")
    public List<OperacionEntity> getOperacionesFiltro2(@Param("id") Integer id , @Param("cuenta") Integer cuenta);

    @Query("select o from OperacionEntity o where o.tipooperacionByTipo.id = :id and o.fechaInstruccion >= :date and o.cuentaByCuenta.id = :cuenta")
    public List<OperacionEntity> getOperacionesFiltro3(@Param("id") Integer id, @Param("date") Date date, @Param("cuenta") Integer cuenta);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id")
    public List<OperacionEntity> getOperacionesByPersona(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id")
    public List<OperacionEntity> buscarPorTipoOperacionYPersona(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.cantidad desc")
    public List<OperacionEntity> getOperacionesOrdenadoCantidad(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.fechaInstruccion desc ")
    public List<OperacionEntity> getOperacionesOrdenadoFecha(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.fechaInstruccion desc, o.cantidad desc")
    public List<OperacionEntity> getOperacionesOrdenadoFechaYCantidad(@Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.cantidad desc")
    public List<OperacionEntity> buscarPorTipoOperacionYPersonaOrdenadoCantidad(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.cantidad desc, o.fechaInstruccion desc")
    public List<OperacionEntity> buscarPorTipoOperacionYPersonaOrdenadoCantidadYFecha(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

    @Query("select o from OperacionEntity o join PersonaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.fechaInstruccion desc")
    public List<OperacionEntity> buscarPorTipoOperacionYPersonaOrdenadoFecha(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);


    @Query("select o from OperacionEntity o join EmpresaEntity e on e.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and e.id = :id")
    public List<OperacionEntity> buscarPorTipoOperacionYEmpresa(@Param("tipos") List<Integer> tipos, @Param("id") Integer id);

    @Query("select o from OperacionEntity o join EmpresaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.fechaInstruccion desc")
    List<OperacionEntity> buscarPorTipoOperacionYEmpresaOrdenadoFecha(List<Integer> tipos, Integer id);

    @Query("select o from OperacionEntity o join EmpresaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.cantidad desc")
    List<OperacionEntity> buscarPorTipoOperacionYEmpresaOrdenadoCantidad(List<Integer> tipos, Integer id);

    @Query("select o from OperacionEntity o join EmpresaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where o.tipooperacionByTipo.id in :tipos and p.id = :id order by o.cantidad desc, o.fechaInstruccion desc")
    List<OperacionEntity> buscarPorTipoOperacionYEmpresaOrdenadoCantidadYFecha(List<Integer> tipos, Integer id);

    @Query("select o from OperacionEntity o join EmpresaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.cantidad, o.fechaInstruccion desc")
    List<OperacionEntity> buscarPorEmpresaOrdenadoCantidadYFecha(Integer id);
    @Query("select o from OperacionEntity o join EmpresaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.fechaInstruccion desc")
    List<OperacionEntity> buscarPorEmpresaOrdenadoFecha(Integer id);
    @Query("select o from OperacionEntity o join EmpresaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id order by o.cantidad desc")
    List<OperacionEntity> buscarPorEmpresaOrdenadoCantidad(Integer id);

    @Query("select o from OperacionEntity o join EmpresaEntity p on p.cuentaByCuenta = o.cuentaByCuenta where p.id = :id")
    List<OperacionEntity> getOperacionesByEmpresa(Integer id);
}
