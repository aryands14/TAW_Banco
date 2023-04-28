package es.taw.grupo17.service;

import es.taw.grupo17.dao.*;
import es.taw.grupo17.dto.Cuenta;
import es.taw.grupo17.dto.Estadocuenta;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.EmpresaEntity;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

    @Autowired
    protected OperacionRepository operacionRepository;

    @Autowired
    protected PersonaRepository personaRepository;

    @Autowired
    protected SospechosoRepository sospechosoRepository;

    public void guardarCuenta(Cuenta cuenta) {
        CuentaEntity cuentaEntity;
        if (cuenta.getId()==null){
            cuentaEntity = new CuentaEntity();
        }else {
            cuentaEntity = this.cuentaRepository.findById(cuenta.getId()).orElse(null);
        }

        cuentaEntity.setId(cuenta.getId());
        cuentaEntity.setNumero(cuenta.getNumero());
        cuentaEntity.setFechaApertura(cuenta.getFechaApertura());
        cuentaEntity.setFechaCierre(cuenta.getFechaCierre());
        cuentaEntity.setSaldo(cuenta.getSaldo());
        cuentaEntity.setEstadocuentaByEstado(cuenta.getEstadocuentaByEstado()==null? null:
                this.estadoCuentaRepository.findById(cuenta.getEstadocuentaByEstado()).orElse(null));

        if (cuenta.getEmpresasById()!=null){
            List<EmpresaEntity> empresas = new ArrayList<>();
            for (Integer id : cuenta.getEmpresasById()){
                empresas.add(this.empresaRepository.findById(id).orElse(null));
            }
            cuentaEntity.setEmpresasById(empresas);
        }

        if (cuenta.getOperacionsById()!=null) {
            List<OperacionEntity> operaciones = new ArrayList<>();
            for (Integer id : cuenta.getOperacionsById()) {
                operaciones.add(this.operacionRepository.findById(id).orElse(null));
            }
            cuentaEntity.setOperacionsById(operaciones);
        }

        if (cuenta.getPersonasById()!=null) {
            List<PersonaEntity> personas = new ArrayList<>();
            for (Integer id : cuenta.getPersonasById()) {
                personas.add(this.personaRepository.findById(id).orElse(null));
            }
            cuentaEntity.setPersonasById(personas);
        }

        cuentaEntity.setSospechosoBySospechoso(cuenta.getSospechosoBySospechoso()==null? null :
                this.sospechosoRepository.findById(cuenta.getSospechosoBySospechoso()).orElse(null));

    }

    public Cuenta buscarCuenta(Integer id){
        CuentaEntity cuenta= this.cuentaRepository.findById(id).orElse(null);
        return cuenta==null? null : cuenta.toDTO();
    }

    public Estadocuenta getEstadoByCuenta(Integer id) {
        CuentaEntity c = this.cuentaRepository.findById(id).orElse(null);
        return c==null?null:c.getEstadocuentaByEstado().toDTO();
    }

    public List<Cuenta> getSospechosos() {
        List<CuentaEntity> sospechosos = this.cuentaRepository.getSospechosos();
        return listaCuentasADTO(sospechosos);
    }

    protected List<Cuenta> listaCuentasADTO (List<CuentaEntity> lista) {
        ArrayList dtos = new ArrayList<Cuenta>();

        lista.forEach((CuentaEntity cuenta) -> dtos.add(cuenta.toDTO()));

        return dtos;
    }
}
