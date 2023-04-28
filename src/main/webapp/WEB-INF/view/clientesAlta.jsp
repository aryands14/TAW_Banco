<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ page import="es.taw.grupo17.dto.Persona" %>
<%@ page import="es.taw.grupo17.dto.Empresa" %>
<%@ page import="es.taw.grupo17.service.CuentaService" %><%--
  Created by IntelliJ IDEA.
  User: aryan
  Date: 27/03/2023
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Persona> listaClientes = (List<Persona>) request.getAttribute("clientes");
  List<Empresa> listaEmpresas = (List<Empresa>) request.getAttribute("empresas");
  CuentaService cuentaService = (CuentaService) request.getAttribute("cuentaService");

%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>Listado de clientes que han solicitado la alta</h1>

<table border="2">
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Fecha Nacimiento </th>
    <th>CP </th>
  </tr>
  <%
    for(Persona c : listaClientes) {
  %>
  <tr>
    <td><%=c.getId()%></td>
    <td><%=c.getPrimerNombre()%></td>
    <td><%=c.getPrimerApellido()%></td>
    <td><%=c.getFechaNacimiento()%></td>
    <td><%=c.getCp()%></td>
    <td><a href="/gestor/altaPersona?id=<%=c.getId()%>"/>Dar Alta</td>
    <td><a href="/gestor/visualizarcliente?id=<%=c.getId()%>"/>Ver Detalles</td>
  </tr>
  <%
    }
  %>
</table>

<h1>Listado de empresas que han solicitado la alta</h1>

<table border="2">
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>CP</th>
    <th>Numero </th>
    <th>Ciudad </th>
  </tr>
  <%
    for(Empresa e : listaEmpresas) {
  %>
  <tr>
    <td><%=e.getId()%></td>
    <td><%=e.getNombre()%></td>
    <td><%=e.getCp()%></td>
    <td><%=e.getNumero()%></td>
    <td><%=e.getCiudad()%></td>
    <td><a href="/gestor/altaEmpresa?id=<%=e.getId()%>"/>Dar Alta</td>
    <td><a href="/gestor/visualizarempresa?id=<%=e.getId()%>"/>Ver Detalles</td>
  </tr>
  <%
    }
  %>
</table>


</body>
</html>
