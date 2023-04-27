package es.taw.grupo17.service;

import es.taw.grupo17.dao.EstadoCuentaRepository;
import es.taw.grupo17.dto.Estadocuenta;
import es.taw.grupo17.entity.EstadocuentaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoCuentaService {

    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

    public Estadocuenta buscarEstadoCuenta(Integer id){
        EstadocuentaEntity estadocuenta = this.estadoCuentaRepository.findById(id).orElse(null);
        return estadocuenta==null? null : estadocuenta.toDTO();
    }
}
