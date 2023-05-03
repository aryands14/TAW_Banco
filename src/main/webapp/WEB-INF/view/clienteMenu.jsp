<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.service.TipopersonaService" %>
<%@ page import="es.taw.grupo17.service.EstadopersonaService" %>
<%@ page import="es.taw.grupo17.dto.*" %>
<%@ page import="es.taw.grupo17.service.CuentaService" %>
<%@ page import="es.taw.grupo17.service.EstadoCuentaService" %><%--
  Created by IntelliJ IDEA.
  User: Jaime Rodrigo Roldán Corcelles
  Date: 21/04/2023
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*
@author: Jaime Rodrigo Roldán Corcelles
 */
    EstadopersonaService estadopersonaService = (EstadopersonaService) request.getAttribute("estadoPersonaService");
    TipopersonaService tipopersonaService = (TipopersonaService) request.getAttribute("tipopersonaService");
    CuentaService cuentaService = (CuentaService) request.getAttribute("cuentaService");
    EstadoCuentaService estadoCuentaService = (EstadoCuentaService) request.getAttribute("estadoCuentaService");
    Persona cliente = (Persona) request.getAttribute("cliente");

%>
<html>
<head>
    <title>Cliente</title>
</head>
<body>

<h1>Menú del Cliente</h1>
<table border="2">
    <tr>
        <th>NIF</th>
        <th>PRIMER NOMBRE</th>
        <th>PRIMER APELLIDO</th>
        <th>FECHA NACIMIENTO</th>
        <th>TIPO USUARIO</th>
        <th>ESTADO PERSONA</th>
        <th>ESTADO CUENTA</th>

    </tr>
    <tr>
        <td><%=cliente.getNif()%></td>
        <td><%=cliente.getPrimerNombre()%></td>
        <td><%=cliente.getPrimerApellido()%></td>
        <td><%=cliente.getFechaNacimiento()%></td>
        <%
            Tipopersona tipopersona = tipopersonaService.buscarTipoPersona(cliente.getTipopersonaByTipo());
        %>
        <td><%=tipopersona.getDescripcion()%></td>
        <%
            Estadopersona estadopersona = estadopersonaService.buscarEstado(cliente.getEstadopersonaByEstado());
        %>
        <td><%=estadopersona.getDescripcion()%></td>
        <%
            Cuenta cuenta = cliente.getCuentaByCuenta()==null? null :cuentaService.buscarCuenta(cliente.getCuentaByCuenta());
            Estadocuenta estadocuenta = cuenta==null ? null : estadoCuentaService.buscarEstadoCuenta(cuenta.getEstadocuentaByEstado());
        %>
        <td><%=estadocuenta==null? "No asignada": estadocuenta.getDescripcion()%></td>
    </tr>
</table>

        <br/><a  href="/cliente/editarCliente?id=<%=cliente.getId()%>" >Editar Datos</a><br/>

        <%
            if (cuenta!=null){
                if (cuenta.getEstadocuentaByEstado()==3){
        %>

        <a href="/cliente/solicitarDesbloqueo?id=<%=cliente.getId()%>" >Solicitar desbloqueo</a><br/>

        <%
                    }
        %>
        <a href="/gestor/visualizarcliente?id=<%=cliente.getId()%>" >Ver operaciones</a><br/>
        <a href="/cajero/transferencia?id=<%=cliente.getId()%>" >Transferencia</a><br/>
        <a href="/cajero/cambiodivisas?id=<%=cliente.getId()%>" >Cambio de divisas</a><br/>
    <%
        }
    %>

</body>
</html>
