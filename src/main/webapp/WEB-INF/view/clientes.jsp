<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: aryan
  Date: 20/03/2023
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<form:form action="/gestor/filtrar" modelAttribute="filtro" method="post">
  Buscar por: <form:input path="texto"></form:input><br/>
  <form:select multiple="true" path="estados"
               items="${estadosPersona}" itemValue="descripcion" itemLabel="descripcion"></form:select>
  <form:button>Filtrar</form:button>
</form:form>

<h1>Listado de clientes </h1>

<table border="2">
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Fecha Nacimiento </th>
    <th>CP </th>
    <th>Estado cuenta </th>
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
    <td><%=c.getCuentaByCuenta()==null?"null":c.getCuentaByCuenta().getEstadocuentaByEstado().getDescripcion()%></td>
    <td><a href="/gestor/visualizarcliente?id=<%=c.getId()%>"/>Ver Detalles</td>
  </tr>
  <%
    }
  %>
</table>

<h1>Listado de empresas</h1>

<table border="2">
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>CP</th>
    <th>Numero </th>
    <th>Ciudad </th>
    <th>Estado Cuenta </th>
  </tr>
  <%
    for(EmpresaEntity c : listaEmpresas) {
  %>
  <tr>
    <td><%=c.getId()%></td>
    <td><%=c.getNombre()%></td>
    <td><%=c.getCp()%></td>
    <td><%=c.getNumero()%></td>
    <td><%=c.getCiudad()%></td>
    <td><%=c.getCuentaByCuenta()==null?"null":c.getCuentaByCuenta().getEstadocuentaByEstado().getDescripcion()%></td>
    <td><a href="/gestor/visualizarempresa?id=<%=c.getId()%>"/>Ver Detalles</td>
  </tr>
  <%
    }
  %>
</table><br>
<a href="/gestor/solicitados"/>Ver Clientes que han solicitado la alta.<br>
<a href="/gestor/inactivos"/>Ver Clientes inactivos.
</body>
</html>
