<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: aryan
  Date: 11/04/2023
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<PersonaEntity> listaClientes = (List<PersonaEntity>) request.getAttribute("clientes");
  List<EmpresaEntity> listaEmpresas = (List<EmpresaEntity>) request.getAttribute("empresas");
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
    for(PersonaEntity c : listaClientes) {
  %>
  <tr>
    <td><%=c.getId()%></td>
    <td><%=c.getPrimerNombre()%></td>
    <td><%=c.getPrimerApellido()%></td>
    <td><%=c.getFechaNacimiento()%></td>
    <td><%=c.getCp()%></td>
    <td><a href="/gestor/visualizarcliente?id=<%=c.getId()%>"/>Ver Detalles</td>
    <td><a href="/gestor/bloquearCuentaPersona?id=<%=c.getId()%>"/>Bloquear Cuenta</td>
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
    for(EmpresaEntity e : listaEmpresas) {
  %>
  <tr>
    <td><%=e.getId()%></td>
    <td><%=e.getNombre()%></td>
    <td><%=e.getCp()%></td>
    <td><%=e.getNumero()%></td>
    <td><%=e.getCiudad()%></td>
    <td><a href="/gestor/visualizarempresa?id=<%=e.getId()%>"/>Ver Detalles</td>
    <td><a href="/gestor/bloquearCuentaEmpresa?id=<%=e.getId()%>"/>Bloquear Cuenta</td>
  </tr>
  <%
    }
  %>
</table>


</body>

</html>
