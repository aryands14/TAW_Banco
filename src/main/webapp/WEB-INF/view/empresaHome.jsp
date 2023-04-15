<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ page import="java.awt.*" %>
<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.entity.TipopersonaEntity" %><%--
  Created by IntelliJ IDEA.
  User: Alvaro
  Date: 06/04/2023
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EmpresaEntity empresa = (EmpresaEntity) request.getAttribute("empresa");
    List<PersonaEntity> listaPersonas = (List<PersonaEntity>) request.getAttribute("listaPersonas");
    List<TipopersonaEntity> listaTipos = (List<TipopersonaEntity>) request.getAttribute("listaTipos");
    PersonaEntity personaEmpresa = (PersonaEntity) request.getAttribute("persona");
    String url = "/empresa/filtrarPersonas?id=" + empresa.getId();
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
        <td><%=empresa.getEstadopersonaByEstado().getDescripcion()%></td>
    </tr>
</table>

<a href="</empresa/editarEmpresa?id=<%=empresa.getId()%>>" >Editar datos de la empresa</a>

<form:form action="<%=url%>" modelAttribute="filtro" method="post">
    Buscar por: <br/>
        Contiene: <form:input path="texto"/>
        Estado: <form:select multiple="true" path="estados">
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
        <th>ESTADO</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
<%
    for(PersonaEntity persona : listaPersonas){
%>
    <tr>
        <td><%=persona.getNif()%></td>
        <td><%=persona.getPrimerNombre()%></td>
        <td><%=persona.getPrimerApellido()%></td>
        <td><%=persona.getFechaNacimiento()%></td>
        <td><%=persona.getTipopersonaByTipo().getDescripcion()%></td>
        <td><%=persona.getEstadopersonaByEstado().getDescripcion()%></td>
        <td><a href="/empresa/editarPersonaEmpresa?id=<%=persona.getId()%>" >Editar</a></td>
        <td><a href="/empresa/bloquearPersona?id=<%=persona.getId()%>" >Bloquear</a></td>
        <td><a href="/gestor/visualizarcliente?id=<%=persona.getId()%>" >Ver operaciones</a></td>
    </tr>
    <%
        }
    %>
</table>
<a href="/empresa/nuevo?id=<%=empresa.getId()%>" >Dar de alta</a>
</body>
</html>
