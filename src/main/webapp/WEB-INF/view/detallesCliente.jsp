<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.entity.OperacionEntity" %>
<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: aryan
  Date: 27/03/2023
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  PersonaEntity c = (PersonaEntity) request.getAttribute("cliente");
  List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operaciones");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Visualizando Cliente </h1>

<%
  if(c != null) {
%>

<table border="2">
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Fecha Nacimiento </th>
    <th>CP </th>
  </tr>

  <tr>
    <td><%=c.getId()%></td>
    <td><%=c.getPrimerNombre()%></td>
    <td><%=c.getPrimerApellido()%></td>
    <td><%=c.getFechaNacimiento()%></td>
    <td><%=c.getCp()%></td>
  </tr>
</table>

<%
  } else {
    EmpresaEntity e = (EmpresaEntity) request.getAttribute("cliente");
%>

<tr>
  <th>ID</th>
  <th>Nombre</th>
  <th>CP</th>
  <th>Numero </th>
  <th>Ciudad </th>
</tr>

<tr>
  <td><%=e.getId()%></td>
  <td><%=e.getNombre()%></td>
  <td><%=e.getCp()%></td>
  <td><%=e.getNumero()%></td>
  <td><%=e.getCiudad()%></td>
</tr>

<%
  }
%>

<h1>Operaciones del Cliente:</h1>

<table border="2">
  <tr>
    <th>ID</th>
    <th>Cuenta</th>
    <th>Cantidad</th>
    <th>Cantidad Cambio </th>
    <th>Tipo Operacion</th>
    <th>Moneda</th>
  </tr>
</table>


<%
  for(OperacionEntity op : operaciones) {
%>

  <tr>
    <td><%=op.getId()%></td>
    <td><%=op.getCuentaByCuenta()%></td>
    <td><%=op.getCantidad()%></td>
    <td><%=op.getCantidadCambio()%></td>
    <td><%=op.getTipooperacionByTipo()%></td>
    <td><%=op.getMoneda()%></td>
  </tr>

<%
  }
%>

</table>


</body>
</html>
