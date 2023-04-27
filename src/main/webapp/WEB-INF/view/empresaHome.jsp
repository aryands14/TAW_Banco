<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.service.TipopersonaService" %>
<%@ page import="es.taw.grupo17.service.EstadopersonaService" %>
<%@ page import="es.taw.grupo17.dto.*" %>
<%@ page import="es.taw.grupo17.service.CuentaService" %>
<%@ page import="es.taw.grupo17.service.EstadoCuentaService" %><%--
  Created by IntelliJ IDEA.
  User: Alvaro
  Date: 06/04/2023
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EstadopersonaService estadopersonaService = (EstadopersonaService) request.getAttribute("estadoPersonaService");
    TipopersonaService tipopersonaService = (TipopersonaService) request.getAttribute("tipopersonaService");
    CuentaService cuentaService = (CuentaService) request.getAttribute("cuentaService");
    EstadoCuentaService estadoCuentaService = (EstadoCuentaService) request.getAttribute("estadoCuentaService");

    Empresa empresa = (Empresa) request.getAttribute("empresa");
    List<Persona> listaPersonas = (List<Persona>) request.getAttribute("listaPersonas");
    List<Tipopersona> listaTipos = (List<Tipopersona>) request.getAttribute("listaTipos");
    Persona personaEmpresa = (Persona) request.getAttribute("persona");

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Datos de la empresa</h1>
<table border="2">
    <tr>
        <th>CIF</th>
        <th>NOMBRE</th>
        <th>NUMERO</th>
        <th>PAIS</th>
        <th>CIUDAD</th>
        <th>ESTADO</th>
    </tr>

    <tr>
        <td><%=empresa.getCif()%></td>
        <td><%=empresa.getNombre()%></td>
        <td><%=empresa.getNumero()%></td>
        <td><%=empresa.getPais()%></td>
        <td><%=empresa.getCiudad()%></td>
        <%
            Estadopersona estadoEmpresa = estadopersonaService.buscarEstado(empresa.getEstadopersonaByEstado());
        %>
        <td><%=estadoEmpresa.getDescripcion()%></td>
    </tr>
</table>
<%
    if (personaEmpresa==null || personaEmpresa.getTipopersonaByTipo()!=3){
%>
<a href="/empresa/editarEmpresa?id=<%=empresa.getId()%>" >Editar datos de la empresa</a>
<%
    }
%>

<form:form action="/empresa/filtrarPersonas" modelAttribute="filtro" method="post">
    Buscar por: <br/>
        Contiene: <form:input path="texto"/>
        Estado: <form:select multiple="true" path="tipos">
                    <form:option value="" label="------"/>
                    <form:options items="${listaTipos}" itemLabel="descripcion" itemValue="id"/>
                </form:select>
    <button>Filtrar</button>
</form:form>

<h1>Personas relacionadas a la empresa</h1>
<table border="2">
    <tr>
        <th>NIF</th>
        <th>PRIMER NOMBRE</th>
        <th>PRIMER APELLIDO</th>
        <th>FECHA NACIMIENTO</th>
        <th>PUESTO</th>
        <th>ESTADO PERSONA</th>
        <th>ESTADO CUENTA</th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
<%
    for(Persona persona : listaPersonas){
%>
    <tr>
        <td><%=persona.getNif()%></td>
        <td><%=persona.getPrimerNombre()%></td>
        <td><%=persona.getPrimerApellido()%></td>
        <td><%=persona.getFechaNacimiento()%></td>
        <%
            Tipopersona tipopersona = tipopersonaService.buscarTipoPersona(persona.getTipopersonaByTipo());
        %>
        <td><%=tipopersona.getDescripcion()%></td>
        <%
            Estadopersona estadopersona = estadopersonaService.buscarEstado(persona.getEstadopersonaByEstado());
        %>
        <td><%=estadopersona.getDescripcion()%></td>
        <%
            Cuenta cuenta = persona.getCuentaByCuenta()==null? null :cuentaService.buscarCuenta(persona.getCuentaByCuenta());
            Estadocuenta estadocuenta= estadoCuentaService.buscarEstadoCuenta(cuenta.getEstadocuentaByEstado());
        %>
        <td><%=estadocuenta.getDescripcion()%></td>
        <%
            if (personaEmpresa!=null && personaEmpresa.getId()==persona.getId()){
        %>
        <td><a href="/empresa/editarPersonaEmpresa?id=<%=persona.getId()%>" >Editar</a></td>

        <%
            if (cuenta!=null){
                if (cuenta.getEstadocuentaByEstado()==3){
        %>

        <td><a href="/empresa/solicitarDesbloqueo?id=<%=persona.getId()%>" >Solicitar desbloqueo</a></td>

        <%
                    }
                }
            }
            if(personaEmpresa.getTipopersonaByTipo()==1 && persona.getEstadopersonaByEstado()!=5 && persona.getEstadopersonaByEstado()!=3){
        %>
        <td><a href="/empresa/bloquearPersona?id=<%=persona.getId()%>" >Bloquear</a></td>
        <%
            }
        %>





        <td><a href="/gestor/visualizarcliente?id=<%=persona.getId()%>" >Ver operaciones</a></td>
    </tr>
    <%
        }
    %>
</table>
<%
    if(personaEmpresa==null){
%>
<a href="/empresa/nuevaPersona?id=<%=empresa.getId()%>" >Dar de alta</a>
<%
    }
%>
</body>
</html>
