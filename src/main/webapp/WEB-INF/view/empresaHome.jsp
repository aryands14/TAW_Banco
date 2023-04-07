<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ page import="java.awt.*" %>
<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %><%--
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
        <th>ESTADO</th>
    </tr>

    <tr>
        <td><%=empresa.getCif()%></td>
        <td><%=empresa.getNombre()%></td>
        <td><%=empresa.getEstadopersonaByEstado().getDescripcion()%></td>
    </tr>
</table>

<h1>Personas relacionadas a la empresa</h1>
<table border="2">
    <tr>
        <th>NIF</th>
        <th>PRIMER NOMBRE</th>
        <th>PRIMER APELLIDO</th>
        <th>FECHA NACIMIENTO</th>
        <th>PUESTO</th>
        <th>ESTADO</th>
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
    </tr>
    <%
        }
    %>
</table>
<a href="/empresa/nuevo?id=<%=empresa.getId()%>" >Dar de alta</a>
</body>
</html>
