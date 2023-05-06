package es.taw.grupo17.dao;

import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
@author: Álvaro Bermúdez Gámez 33%
@author: Aryan Dilip Sadhwani Sadhwani 33%
@author: Jaime Rodrigo Roldán Corcelles 33%
 */
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    @Query("select c from PersonaEntity c where c.estadopersonaByEstado.id = :id")
    public List<PersonaEntity> getPendientes(@Param("id") Integer id);

    @Query("SELECT p FROM PersonaEntity p LEFT JOIN " +
            "OperacionEntity o ON (o.cuentaByCuenta.id = p.cuentaByCuenta.id) " +
            "LEFT JOIN p.cuentaByCuenta.estadocuentaByEstado e " +
            "WHERE (p.cuentaByCuenta.id IS NOT NULL) " +
            "AND (e.descripcion LIKE :desc) " +
            "GROUP BY p.id, e.descripcion " +
            "HAVING MAX(o.fechaInstruccion) IS NULL OR DATEDIFF(CURDATE(), MAX(o.fechaInstruccion)) > 30")
    public List<PersonaEntity> getInactivos(@Param("desc") String desc);

    @Query("select p from PersonaEntity p where p.primerNombre like " +
            "CONCAT('%', :texto, '%' ) or p.primerApellido like " +
            "CONCAT('%', :texto, '%')")
    public List<PersonaEntity> buscarPorNombre(@Param("texto") String texto);


    @Query("select p from PersonaEntity p where p.nif = :username and p.contraseña = :password")
    public PersonaEntity autenticarPersonaEmpresa(@Param("username")String user, @Param("password") String password);

    @Query("select p from PersonaEntity p where p.nif = :username and p.contraseña = :password and p.empresaByEmpresa is null" )
    public PersonaEntity autenticarCliente(@Param("username")String user, @Param("password") String password);

    @Query("select p from PersonaEntity p where p.estadopersonaByEstado.descripcion in :estados")
    public List<PersonaEntity> buscarPorEstado(@Param("estados") List<String> estados);

    @Query("select p from PersonaEntity p where (p.primerNombre like " +
            "CONCAT('%', :texto, '%' ) or p.primerApellido like " +
            "CONCAT('%', :texto, '%')) and p.estadopersonaByEstado.descripcion in :estados")
    public List<PersonaEntity> buscarPorNombreYEstado(@Param("texto") String texto, @Param("estados") List<String> estados);

    @Query("select c from PersonaEntity c join OperacionEntity o on (o.cuentaByCuenta.id = c.cuentaByCuenta.id)" +
            "where o.cuentaByCuenta.estadocuentaByEstado.id = 1" +
            "and o.personaByBeneficiario.cuentaByCuenta.id in :sospechosos")
    public List<PersonaEntity> getSospechosos(@Param("sospechosos") List<Integer> sospechosos);

    @Query("select p from PersonaEntity p where (p.primerNombre like " +
            "CONCAT('%', :texto, '%' ) or p.primerApellido like " +
            "CONCAT('%', :texto, '%')) and p.empresaByEmpresa.id = :empresa")
    public List<PersonaEntity> buscarPorNombreYEmpresa(@Param("texto") String texto, @Param("empresa") Integer empresa);

    @Query("select p from PersonaEntity p where p.tipopersonaByTipo.id in :tipos and p.empresaByEmpresa.id = :empresa")
    public List<PersonaEntity> buscarPorTipoYEmpresa(@Param("tipos") List<Integer> tipos, @Param("empresa") Integer empresa);

    @Query("select p from PersonaEntity p where (p.primerNombre like " +
            "CONCAT('%', :texto, '%' ) or p.primerApellido like " +
            "CONCAT('%', :texto, '%')) and p.tipopersonaByTipo.id in :tipos and p.empresaByEmpresa.id = :empresa")
    public List<PersonaEntity> buscarPorNombreYTipoYEmpresa(@Param("texto") String texto, @Param("tipos") List<Integer> estados, @Param("empresa") Integer empresa);

}
