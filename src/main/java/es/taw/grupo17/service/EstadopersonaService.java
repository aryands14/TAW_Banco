package es.taw.grupo17.service;


import es.taw.grupo17.dao.EstadoPersonaRepository;
import es.taw.grupo17.dto.Estadopersona;
import es.taw.grupo17.entity.EstadopersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadopersonaService {

    @Autowired
    protected EstadoPersonaRepository estadoPersonaRepository;

    public Estadopersona buscarEstado(Integer id){
        EstadopersonaEntity estadopersona = this.estadoPersonaRepository.findById(id).orElse(null);
        if (estadopersona != null){
            return estadopersona.toDTO();
        }else {
            return null;
        }
    }
}
