package es.taw.grupo17.controller;

import es.taw.grupo17.dao.CuentaRepository;
import es.taw.grupo17.dao.OperacionRepository;
import es.taw.grupo17.dao.PersonaRepository;
import es.taw.grupo17.dao.TipooperacionRepository;
import es.taw.grupo17.entity.CuentaEntity;
import es.taw.grupo17.entity.OperacionEntity;
import es.taw.grupo17.entity.PersonaEntity;
import es.taw.grupo17.entity.TipooperacionEntity;
import es.taw.grupo17.ui.FiltroOperacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;


@Controller
@RequestMapping("/cajero")
public class CajeroController {

    @Autowired
    protected PersonaRepository personaRepository;

    @Autowired
    protected OperacionRepository operacionRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected TipooperacionRepository tipooperacionRepository;

    @GetMapping("/")
    String menuVista(@RequestParam("id") Integer idcliente, Model model){
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        model.addAttribute("persona", persona);
        return ("cajero");
    }

    @PostMapping("/")
    String menuVista(@RequestParam("id") Integer idcliente, Model model, String operacion)
    {
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        model.addAttribute("persona", persona);
        model.addAttribute("operacion", operacion);
        return ("cajero");
    }

    @GetMapping("/modificarcliente")
    String modificarCliente(@RequestParam("id") Integer idcliente, Model model)
    {
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        model.addAttribute("error", 0);
        model.addAttribute("cliente", persona);
        return("cajeromodificar");
    }

    @PostMapping("/guardarcliente")
    String guardarCliente(@ModelAttribute("cliente") PersonaEntity persona, @RequestParam("repetircontraseña") String contraseña, @RequestParam("ant") String anterior, @RequestParam("anterior2") String anterior2, Model model)
    {
        int error = 0;
        if (((anterior.equals(persona.getContraseña()) && contraseña.equals("")) && anterior2.equals("")) ||
                ((anterior.equals(anterior2) && persona.getContraseña().equals(contraseña))))
        {
            this.personaRepository.save(persona);
            Boolean guardar = true;

            model.addAttribute("guardar", guardar);
        }
        else if (!(contraseña.equals(persona.getContraseña())))
        {
            persona.setContraseña(anterior);
            error = 2;
        }
        else if (!(anterior.equals(anterior2)))
        {
            persona.setContraseña(anterior);
            error = 1;
        }

        model.addAttribute("error", error);
        model.addAttribute("cliente", persona);
        return ("cajeromodificar");
    }

    @GetMapping("/transferencia")
    String realizarTransferencia(@RequestParam("id") Integer idcliente, Model model)
    {
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("fecha", date);
        model.addAttribute("persona", persona);
        model.addAttribute("transferencia", new OperacionEntity());
        return("cajerotransferencia");
    }

    @PostMapping("/guardartransferencia")
    String guardarTransferencia(@ModelAttribute("transferencia") OperacionEntity operacion, @RequestParam("persona") Integer idpersona, Model model)
    {
        PersonaEntity persona = this.personaRepository.findById(idpersona).orElse(null);

        model.addAttribute("persona", persona);
        if (operacion.getCantidad() > persona.getCuentaByCuenta().getSaldo())
        {
           model.addAttribute("transferencia", operacion);
           model.addAttribute("fecha", new Date(System.currentTimeMillis()));
           model.addAttribute("error", "No tienes disponible esta cantidad de dinero en la cuenta");
           return ("cajerotransferencia");
        }
        else if (operacion.getCantidad() < 5)
        {
            model.addAttribute("transferencia", operacion);
            model.addAttribute("fecha", new Date(System.currentTimeMillis()));
            model.addAttribute("error", "La transferencia minima debe ser de 5 euros");
            return ("cajerotransferencia");
        }
        else
        {
            persona.getCuentaByCuenta().setSaldo(persona.getCuentaByCuenta().getSaldo() - operacion.getCantidad());
            operacion.getPersonaByBeneficiario().getCuentaByCuenta().setSaldo(operacion.getCuentaByCuenta().getSaldo() + operacion.getCantidad());
            Collection<OperacionEntity> aux = persona.getOperacionsById();
            aux.add(operacion);
            persona.setOperacionsById(aux);
            this.personaRepository.save(persona);
            this.operacionRepository.save(operacion);
            this.cuentaRepository.save(persona.getCuentaByCuenta());
            this.cuentaRepository.save(operacion.getPersonaByBeneficiario().getCuentaByCuenta());
        }
        String noticia = "Se ha realizado la transferencia a la cuenta " + operacion.getPersonaByBeneficiario().getCuentaByCuenta().getId() + " con la cantidad de " + operacion.getCantidad() + " euros";
        return menuVista(idpersona, model, noticia);
    }
    @GetMapping("/sacardinero")
    String sacarDinero(@RequestParam("id") Integer idcliente, Model model)
    {
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("fecha", date);
        model.addAttribute("persona", persona);
        model.addAttribute("sacardinero", new OperacionEntity());
        return("cajerosacardinero");
    }

    @PostMapping("/guardardinero")
    String guardarDinero(@ModelAttribute("sacardinero") OperacionEntity operacion,@RequestParam("persona") Integer idcliente, Model model)
    {
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        model.addAttribute("persona", persona);
        if (operacion.getCantidad() > persona.getCuentaByCuenta().getSaldo())
        {
            model.addAttribute("transferencia", operacion);
            model.addAttribute("fecha", new Date(System.currentTimeMillis()));
            model.addAttribute("error", "No tienes disponible esta cantidad de dinero en la cuenta");
            return ("cajerosacardinero");
        }
        else if (operacion.getCantidad() < 5)
        {
            model.addAttribute("transferencia", operacion);
            model.addAttribute("fecha", new Date(System.currentTimeMillis()));
            model.addAttribute("error", "No se puede sacar menos de 5 euros");
            return ("cajerosacardinero");
        }
        else
        {
            Collection<OperacionEntity> aux = persona.getOperacionsById();
            aux.add(operacion);
            persona.setOperacionsById(aux);
            persona.getCuentaByCuenta().setSaldo(persona.getCuentaByCuenta().getSaldo() - operacion.getCantidad());
            this.cuentaRepository.save(persona.getCuentaByCuenta());
            this.operacionRepository.save(operacion);
            this.personaRepository.save(persona);
        }
        String noticia = "Has sacado " + operacion.getCantidad() + " euros";
        return menuVista(idcliente, model, noticia);
    }

    @GetMapping("/cambiodivisas")
    String cajeroCambio(@RequestParam("id") Integer idcliente, Model model)
    {
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("fecha", date);
        model.addAttribute("persona", persona);
        model.addAttribute("cambiardinero", new OperacionEntity());
        return("cajerocambio");
    }

    @PostMapping("/guardarcambio")
    String guardarCambio(@ModelAttribute("cambiardinero") OperacionEntity operacion,@RequestParam("persona") Integer idcliente, Model model)
    {

        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);

        model.addAttribute("persona", persona);
        String noticia = "";
        if (operacion.getCantidad() > persona.getCuentaByCuenta().getSaldo())
        {
            model.addAttribute("transferencia", operacion);
            model.addAttribute("fecha", new Date(System.currentTimeMillis()));
            model.addAttribute("error", "No tienes disponible esta cantidad de dinero en la cuenta");
            return ("cajerocambio");
        }
        else if (operacion.getCantidad() < 5)
        {
            model.addAttribute("transferencia", operacion);
            model.addAttribute("fecha", new Date(System.currentTimeMillis()));
            model.addAttribute("error", "No se puede cambiar menos de 5 euros");
            return ("cajerocambio");
        }
        else
        {
            if (operacion.getMonedaCambio().equals("libras"))
            {
                operacion.setCantidadCambio(operacion.getCantidad() * 0.88);
                noticia = "Has sacado " + operacion.getCantidadCambio() + " libras";
            }
            else if(operacion.getMonedaCambio().equals("dolares"))
            {
                operacion.setCantidadCambio(operacion.getCantidad() * 1.1);
                noticia = "Has sacado " + operacion.getCantidadCambio() + " dolares";
            }
            Collection<OperacionEntity> aux = persona.getOperacionsById();
            aux.add(operacion);
            persona.setOperacionsById(aux);
            persona.getCuentaByCuenta().setSaldo(persona.getCuentaByCuenta().getSaldo() - operacion.getCantidad());
            this.cuentaRepository.save(persona.getCuentaByCuenta());
            this.operacionRepository.save(operacion);
            this.personaRepository.save(persona);
        }
        return menuVista(idcliente, model, noticia);
    }

    @GetMapping("/operaciones")
    String verOperaciones(@RequestParam("id") Integer idcliente, @ModelAttribute("filtro") FiltroOperacion filtro, Model model)
    {
        PersonaEntity persona = this.personaRepository.findById(idcliente).orElse(null);
        model.addAttribute("persona", persona);
        if (filtro.getFecha() == null && filtro.getTipo() == null) {
           model.addAttribute("operaciones", persona.getOperacionsById());
           model.addAttribute("filtro", filtro);
       }
        else if (filtro.getFecha().equals(new Date(1970,1,1)) && filtro.getTipo().equals(0))
        {
            model.addAttribute("operaciones", persona.getOperacionsById());
            model.addAttribute("filtro", filtro);
        }
        else if (filtro.getTipo().equals(0))
        {
            model.addAttribute("operaciones", this.operacionRepository.getOperacionesFiltro1(filtro.getFecha(), persona.getCuentaByCuenta().getId()));
            model.addAttribute("filtro", filtro);
        }
        else if (filtro.getFecha().equals(new Date(1970,1,1)))
        {
            model.addAttribute("operaciones", this.operacionRepository.getOperacionesFiltro2(filtro.getTipo(), persona.getCuentaByCuenta().getId()));
            model.addAttribute("filtro", filtro);
        }
        else
        {
            model.addAttribute("operaciones", this.operacionRepository.getOperacionesFiltro3(filtro.getTipo(), filtro.getFecha(), persona.getCuentaByCuenta().getId()));
            model.addAttribute("filtro", filtro);
        }
        return("cajerooperaciones");
    }

}
