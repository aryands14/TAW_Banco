package es.taw.grupo17.service;

import es.taw.grupo17.dao.TipoOperacionRepository;
import es.taw.grupo17.dao.TipoPersonaRepository;
import es.taw.grupo17.dto.Tipooperacion;
import es.taw.grupo17.dto.Tipopersona;
import es.taw.grupo17.entity.TipooperacionEntity;
import es.taw.grupo17.entity.TipopersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoOperacionService {


    @Autowired
    protected TipoOperacionRepository tipoOperacionRepository;

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
