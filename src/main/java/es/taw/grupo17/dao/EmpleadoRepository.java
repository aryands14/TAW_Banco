package es.taw.grupo17.dao;

import es.taw.grupo17.entity.EmpleadoEntity;
import es.taw.grupo17.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/*
@author: Aryan Dilip Sadhwani Sadhwani
 */
public interface EmpleadoRepository  extends JpaRepository<EmpleadoEntity, Integer> {
    @Query("select e from EmpleadoEntity e where e.usuario = :username and e.contraseña = :password")
    public EmpleadoEntity autenticar(@Param("username")String user, @Param("password") String password);

}
