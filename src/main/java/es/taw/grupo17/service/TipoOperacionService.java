package es.taw.grupo17.service;

import es.taw.grupo17.dao.TipooperacionRepository;
import es.taw.grupo17.dto.Tipooperacion;
import es.taw.grupo17.entity.TipooperacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
@author: Aryan Dilip Sadhwani Sadhwani
 */

@Service
public class TipoOperacionService {


    @Autowired
    protected TipooperacionRepository tipoOperacionRepository;

    public List<Tipooperacion> listarTiposOperaciones() {
        List<TipooperacionEntity> lista = this.tipoOperacionRepository.findAll();
        return this.listaEntidadesADTO(lista);
    }

    private List<Tipooperacion> listaEntidadesADTO(List<TipooperacionEntity> lista) {
        List dtos = new ArrayList<Tipooperacion>();

        lista.forEach((final TipooperacionEntity tipooperacion) -> dtos.add(tipooperacion.toDTO()));

        return dtos;
    }

}
