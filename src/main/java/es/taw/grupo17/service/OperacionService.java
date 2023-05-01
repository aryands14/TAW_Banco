package es.taw.grupo17.service;

import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dto.Operacion;
import es.taw.grupo17.entity.OperacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionService {

    @Autowired
    OperacionRepository operacionRepository;


    public List<Operacion> filtrar() {
        List<OperacionEntity> operaciones = null;
        return listaOperacionesADTO(operaciones);
    }

    public List<Operacion> getOperaciones(Integer idCuenta) {
        List<OperacionEntity> operacionList = this.operacionRepository.getOperaciones(idCuenta);
        List<Operacion> operacionesDTO = listaOperacionesADTO(operacionList);
        return operacionesDTO;
    }

    protected List<Operacion> listaOperacionesADTO (List<OperacionEntity> lista) {
        ArrayList dtos = new ArrayList<Operacion>();

        lista.forEach((OperacionEntity operacion) -> dtos.add(operacion.toDTO()));

        return dtos;
    }
}
