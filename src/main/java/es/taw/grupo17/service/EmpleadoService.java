package es.taw.grupo17.service;

import es.taw.grupo17.dao.*;
import es.taw.grupo17.dto.Empleado;
import es.taw.grupo17.entity.EmpleadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    @Autowired
    protected EmpleadoRepository empleadoRepository;

    public Empleado autenticar(String user, String clave) {
        EmpleadoEntity empleado = this.empleadoRepository.autenticar(user, clave);
        return (empleado == null ? null : empleado.toDTO());
    }



}
