<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ page import="es.taw.grupo17.dto.Persona" %>
<%@ page import="es.taw.grupo17.dto.Empresa" %><%--
  Created by IntelliJ IDEA.

  @author: Aryan Dilip Sadhwani Sadhwani

  Date: 03/04/2023
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  /*
@author: Aryan Dilip Sadhwani Sadhwani
*/
  List<Persona> listaClientes = (List<Persona>) request.getAttribute("clientes");
  List<Empresa> listaEmpresas = (List<Empresa>) request.getAttribute("empresas");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Listado de clientes inactivos</h1>

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
    <td><a href="/gestor/visualizarcliente?id=<%=c.getId()%>"/>Ver Detalles</td>
    <td><a href="/gestor/desactivarPersona?id=<%=c.getId()%>"/>Desactivar Cuenta</td>
  </tr>
  <%
    }
  %>
</table>

<h1>Listado de empresas inactivos</h1>

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
    <td><a href="/gestor/visualizarempresa?id=<%=e.getId()%>"/>Ver Detalles</td>
    <td><a href="/gestor/desactivarEmpresa?id=<%=e.getId()%>"/>Desactivar Cuenta</td>
  </tr>
  <%
    }
  %>
</table>

<br><br>

<a href="/gestor/"/>Volver</a>

</body>
</html>
