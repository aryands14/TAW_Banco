package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EmpleadoEntity;
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

    @Query("select e from EmpresaEntity e where e.cif = :username and e.contraseña = :password")
    public EmpresaEntity autenticar(@Param("username")String user, @Param("password") String password);


    @Query("select e from EmpresaEntity e where (e.nombre like " +
            "CONCAT('%', :texto, '%' )) and e.estadopersonaByEstado.descripcion in :estados")
    public List<EmpresaEntity> buscarPorNombreYEstado(@Param("texto") String texto, @Param("estados") List<String> estados);

}
