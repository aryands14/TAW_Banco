package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepository  extends JpaRepository<EmpresaEntity, Integer> {
    @Query("select e from EmpresaEntity e where e.estadopersonaByEstado.id = :id")
    public List<EmpresaEntity> getPendientes(@Param("id") Integer id);

    @Query("SELECT p FROM EmpresaEntity p LEFT JOIN " +
            "OperacionEntity o ON (o.cuentaByCuenta.id = p.cuentaByCuenta.id) " +
            "LEFT JOIN p.cuentaByCuenta.estadocuentaByEstado e " +
            "WHERE (p.cuentaByCuenta.id IS NOT NULL) " +
            "AND (e.descripcion LIKE :desc) " +
            "GROUP BY p.id, e.descripcion " +
            "HAVING MAX(o.fechaInstruccion) IS NULL OR DATEDIFF(CURDATE(), MAX(o.fechaInstruccion)) > 30")
    public List<EmpresaEntity> getInactivos(@Param("desc") String desc);

    @Query("select e from EmpresaEntity e where e.cif = :username and e.contraseña = :password")
    public EmpresaEntity autenticar(@Param("username")String user, @Param("password") String password);

    @Query("select e from EmpresaEntity e where e.nombre like CONCAT('%', :texto, '%' )")
    public List<EmpresaEntity> buscarPorNombre(@Param("texto") String texto);


    @Query("select e from EmpresaEntity e where e.estadopersonaByEstado.descripcion in :estados")
    public List<EmpresaEntity> buscarPorEstado(@Param("estados") List<String> estados);

    @Query("select e from EmpresaEntity e where (e.nombre like " +
            "CONCAT('%', :texto, '%' )) and e.estadopersonaByEstado.descripcion in :estados")
    public List<EmpresaEntity> buscarPorNombreYEstado(@Param("texto") String texto, @Param("estados") List<String> estados);

    @Query("select c from EmpresaEntity c join OperacionEntity o on (o.cuentaByCuenta.id = c.cuentaByCuenta.id)" +
            "where o.cuentaByCuenta.estadocuentaByEstado.id = 5" +
            "and o.personaByBeneficiario.cuentaByCuenta.id in :sospechosos")
    public List<EmpresaEntity> getSospechosos(@Param("sospechosos") List<Integer> sospechosos);

}
