package es.taw.grupo17.service;

import es.taw.grupo17.dao.TipoPersonaRepository;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.dto.Tipopersona;
import es.taw.grupo17.entity.TipopersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
@author: Álvaro Bermúdez Gámez
 */

@Service
public class TipopersonaService {

    @Autowired
    protected TipoPersonaRepository tipoPersonaRepository;


    public Tipopersona buscarTipoPersona(Integer id){
        TipopersonaEntity tipopersona = this.tipoPersonaRepository.findById(id).orElse(null);
        return tipopersona==null? null : tipopersona.toDTO();
    }
    public List<Tipopersona> listarTiposPersonas() {
        List<TipopersonaEntity> lista = this.tipoPersonaRepository.findAll();
        return this.listaEntidadesADTO(lista);
    }

    private List<Tipopersona> listaEntidadesADTO(List<TipopersonaEntity> lista) {
        List dtos = new ArrayList<Tipopersona>();

        lista.forEach((final TipopersonaEntity tipopersona) -> dtos.add(tipopersona.toDTO()));

        return dtos;
    }
}
