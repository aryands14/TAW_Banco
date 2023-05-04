package es.taw.grupo17.service;


import es.taw.grupo17.dao.EstadoPersonaRepository;
import es.taw.grupo17.dto.Empresa;
import es.taw.grupo17.dto.Estadopersona;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.dto.Tipopersona;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.EstadopersonaEntity;
import es.taw.grupo17.entity.PersonaEntity;
import es.taw.grupo17.entity.TipopersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
@author: Álvaro Bermúdez Gámez 50%
@author: Aryan Dilip Sadhwani Sadhwani 50%
 */

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

    public List<Estadopersona> doListarEstados() {
        List<EstadopersonaEntity> listaEstados = this.estadoPersonaRepository.findAll();
        List<Estadopersona> listaEstadosDTO = listarEstadosDTO(listaEstados);
        return listaEstadosDTO;
    }

    protected List<Estadopersona> listarEstadosDTO (List<EstadopersonaEntity> lista) {
        ArrayList dtos = new ArrayList<Persona>();

        lista.forEach((final EstadopersonaEntity estado) -> dtos.add(estado.toDTO()));

        return dtos;
    }

}
