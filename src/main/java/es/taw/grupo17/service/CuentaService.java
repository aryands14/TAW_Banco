package es.taw.grupo17.service;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.EstadoCuentaRepository;
import es.taw.grupo17.dto.Estadocuenta;
import es.taw.grupo17.entity.CuentaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    EstadoCuentaRepository estadoCuentaRepository;
    @Autowired
    CuentaRepository cuentaRepository;

    public Estadocuenta getEstadoByCuenta(Integer id) {
        CuentaEntity c = this.cuentaRepository.findById(id).orElse(null);
        return c==null?null:c.getEstadocuentaByEstado().toDTO();
    }
}
