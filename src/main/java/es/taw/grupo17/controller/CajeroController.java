package es.taw.grupo17.controller;

import es.taw.grupo17.dto.Cuenta;
import es.taw.grupo17.dto.Estadocuenta;
import es.taw.grupo17.dto.Operacion;
import es.taw.grupo17.dto.Persona;
import es.taw.grupo17.service.CuentaService;
import es.taw.grupo17.service.EstadoCuentaService;
import es.taw.grupo17.service.OperacionService;
import es.taw.grupo17.service.PersonaService;
import es.taw.grupo17.ui.FiltroOperacion2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

// Hecho al 100% por Francisco Javier Tejada Martín
@Controller
@RequestMapping("/cajero")
public class CajeroController {

    @Autowired
    protected PersonaService personaService;
    @Autowired
    protected CuentaService cuentaService;

    @Autowired
    protected OperacionService operacionService;

    @Autowired
    protected EstadoCuentaService estadoCuentaService;

    @GetMapping("/")
    String menuVista(@RequestParam("id") Integer idcliente, Model model){
        Persona persona = personaService.buscarPersona(idcliente);
        Cuenta cuentaPersona = cuentaService.buscarCuenta(persona.getCuentaByCuenta());
        Estadocuenta estado = estadoCuentaService.buscarEstadoCuenta(cuentaPersona.getEstadocuentaByEstado());

        model.addAttribute("estado", estado);
        model.addAttribute("persona", persona);
        return ("cajero");
    }

    @PostMapping("/")
    String menuVista(@RequestParam("id") Integer idcliente, Model model, String operacion)
    {
        Persona persona = personaService.buscarPersona(idcliente);
        Cuenta cuentaPersona = cuentaService.buscarCuenta(persona.getCuentaByCuenta());
        Estadocuenta estado = estadoCuentaService.buscarEstadoCuenta(cuentaPersona.getEstadocuentaByEstado());

        model.addAttribute("estado", estado);
        model.addAttribute("persona", persona);
        model.addAttribute("operacion", operacion);
        return ("cajero");
    }

    @GetMapping("/modificarcliente")
    String modificarCliente(@RequestParam("id") Integer idcliente, Model model)
    {
        Persona persona = personaService.buscarPersona(idcliente);

        model.addAttribute("error", 0);
        model.addAttribute("cliente", persona);
        return("cajeromodificar");
    }

    @PostMapping("/guardarcliente")
    String guardarCliente(@ModelAttribute("cliente") Persona persona, @RequestParam("repetircontraseña") String contraseña, @RequestParam("ant") String anterior, @RequestParam("anterior2") String anterior2, Model model)
    {
        int error = 0;
        if (((anterior.equals(persona.getContraseña()) && contraseña.equals("")) && anterior2.equals("")) ||
                ((anterior.equals(anterior2) && persona.getContraseña().equals(contraseña))))
        {
            personaService.guardarPersona(persona);
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
        Persona persona = personaService.buscarPersona(idcliente);

        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("fecha", date);
        model.addAttribute("persona", persona);
        model.addAttribute("transferencia", new Operacion());
        return("cajerotransferencia");
    }

    @PostMapping("/guardartransferencia")
    String guardarTransferencia(@ModelAttribute("transferencia") Operacion operacion, @RequestParam("persona") Integer idpersona, Model model)
    {
        Persona persona = personaService.buscarPersona(idpersona);
        Cuenta cuentaPersona = cuentaService.buscarCuenta(persona.getCuentaByCuenta());
        Persona beneficiario = personaService.buscarPersona(operacion.getPersonaByBeneficiario());
        Cuenta cuentaBeneficiario = cuentaService.buscarCuenta(beneficiario.getCuentaByCuenta());

        model.addAttribute("persona", persona);
        if (operacion.getCantidad() > cuentaPersona.getSaldo())
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
            cuentaPersona.setSaldo(cuentaPersona.getSaldo() - operacion.getCantidad());
            cuentaBeneficiario.setSaldo(cuentaBeneficiario.getSaldo() + operacion.getCantidad());
            operacionService.guardarOperacion(operacion);
            cuentaService.guardarCuenta(cuentaPersona);
            cuentaService.guardarCuenta(cuentaBeneficiario);
        }
        String noticia = "Se ha realizado la transferencia a la cuenta " + cuentaBeneficiario.getId() + " con la cantidad de " + operacion.getCantidad() + " euros";
        return menuVista(idpersona, model, noticia);
    }
    @GetMapping("/sacardinero")
    String sacarDinero(@RequestParam("id") Integer idcliente, Model model)
    {
        Persona persona = personaService.buscarPersona(idcliente);

        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("fecha", date);
        model.addAttribute("persona", persona);
        model.addAttribute("sacardinero", new Operacion());
        return("cajerosacardinero");
    }

    @PostMapping("/guardardinero")
    String guardarDinero(@ModelAttribute("sacardinero") Operacion operacion,@RequestParam("persona") Integer idcliente, Model model)
    {
        Persona persona = personaService.buscarPersona(idcliente);
        Cuenta cuentaPersona = cuentaService.buscarCuenta(persona.getCuentaByCuenta());

        model.addAttribute("persona", persona);
        if (operacion.getCantidad() > cuentaPersona.getSaldo())
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
            cuentaPersona.setSaldo(cuentaPersona.getSaldo() - operacion.getCantidad());
            operacionService.guardarOperacion(operacion);
            cuentaService.guardarCuenta(cuentaPersona);
            personaService.guardarPersona(persona);
        }
        String noticia = "Has sacado " + operacion.getCantidad() + " euros";
        return menuVista(idcliente, model, noticia);
    }

    @GetMapping("/cambiodivisas")
    String cajeroCambio(@RequestParam("id") Integer idcliente, Model model)
    {
        Persona persona = personaService.buscarPersona(idcliente);

        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("fecha", date);
        model.addAttribute("persona", persona);
        model.addAttribute("cambiardinero", new Operacion());
        return("cajerocambio");
    }

    @PostMapping("/guardarcambio")
    String guardarCambio(@ModelAttribute("cambiardinero") Operacion operacion,@RequestParam("persona") Integer idcliente, Model model)
    {

        Persona persona = personaService.buscarPersona(idcliente);
        Cuenta cuentaPersona = cuentaService.buscarCuenta(persona.getCuentaByCuenta());

        model.addAttribute("persona", persona);
        String noticia = "";
        if (operacion.getCantidad() > cuentaPersona.getSaldo())
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

            cuentaPersona.setSaldo(cuentaPersona.getSaldo() - operacion.getCantidad());
            operacionService.guardarOperacion(operacion);
            cuentaService.guardarCuenta(cuentaPersona);
            personaService.guardarPersona(persona);
        }
        return menuVista(idcliente, model, noticia);
    }

    @GetMapping("/operaciones")
    String verOperaciones(@RequestParam("id") Integer idcliente, @ModelAttribute("filtro") FiltroOperacion2 filtro, Model model)
    {
        Persona persona = personaService.buscarPersona(idcliente);
        Cuenta cuentaPersona = cuentaService.buscarCuenta(persona.getCuentaByCuenta());

        model.addAttribute("persona", persona);
        if (filtro.getFecha() == null && filtro.getTipo() == null) {
           model.addAttribute("operaciones", operacionService.getOperaciones(cuentaPersona.getId()));
           model.addAttribute("filtro", filtro);
       }
        else if (filtro.getFecha().equals(new Date(1970,1,1)) && filtro.getTipo().equals(0))
        {
            model.addAttribute("operaciones", operacionService.getOperaciones(cuentaPersona.getId()));
            model.addAttribute("filtro", filtro);
        }
        else if (filtro.getTipo().equals(0))
        {
            model.addAttribute("operaciones", operacionService.getOperacionesFiltro1(filtro.getFecha(), cuentaPersona.getId()));
            model.addAttribute("filtro", filtro);
        }
        else if (filtro.getFecha().equals(new Date(1970,1,1)))
        {
            model.addAttribute("operaciones", operacionService.getOperacionesFiltro2(filtro.getTipo(), cuentaPersona.getId()));
            model.addAttribute("filtro", filtro);
        }
        else
        {
            model.addAttribute("operaciones", operacionService.getOperacionesFiltro3(filtro.getTipo(), filtro.getFecha(), cuentaPersona.getId()));
            model.addAttribute("filtro", filtro);
        }
        return("cajerooperaciones");
    }

    @GetMapping("/desbloqueo")
    String solicitarDesbloqueo(@RequestParam("id") Integer id, Model model)
    {
        Persona persona = personaService.buscarPersona(id);
        Cuenta cuentaPersona = cuentaService.buscarCuenta(persona.getCuentaByCuenta());
        Estadocuenta estado = estadoCuentaService.buscarEstadoCuenta(4);
        cuentaPersona.setEstadocuentaByEstado(estado.getId()); //La pasamos a pendiente para que la aprueben
        cuentaService.guardarCuenta(cuentaPersona);
        String noticia = "Se ha solicitado el desbloqueo de la cuenta";
        return menuVista(persona.getId(), model, noticia);
    }

}
