<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ page import="es.taw.grupo17.entity.OperacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.dto.Empresa" %>
<%@ page import="es.taw.grupo17.dto.Operacion" %><%--
  Created by IntelliJ IDEA.
  User: aryan
  Date: 01/04/2023
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Empresa e = (Empresa) request.getAttribute("empresa");
    List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Visualizando Empresa </h1>

<%
    if(e != null) {
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
        <td><%=e.getId()%></td>
        <td><%=e.getNombre()%></td>
        <td><%=e.getCp()%></td>
        <td><%=e.getNumero()%></td>
        <td><%=e.getCiudad()%></td>
    </tr>
</table>

<%
    }
%>


<h1>Operaciones de la Empresa:</h1>

<form:form action="/gestor/filtrar" modelAttribute="filtro" method="post">
    Ordenar por: <form:input path="texto"></form:input><br/>
    <form:select multiple="true" path="estados"
                 items="${estadosPersona}" itemValue="descripcion" itemLabel="descripcion"></form:select>
    <form:button>Filtrar</form:button>
</form:form>


<table border="2">
    <tr>
        <th>ID</th>
        <th>Cuenta</th>
        <th>Cantidad</th>
        <th>Cantidad Cambio </th>
        <th>Tipo Operacion</th>
        <th>Moneda</th>
        <th>Fecha</th>
    </tr>


        <%
  if(operaciones != null) {
  for(Operacion op : operaciones) {
%>

    <tr>
        <td><%=op.getId()%></td>
        <td><%=op.getCuentaByCuenta()%></td>
        <td><%=op.getCantidad()%></td>
        <td><%=op.getCantidadCambio()%></td>
        <td><%=op.getTipooperacionByTipo()%></td>
        <td><%=op.getMoneda()%></td>
        <td><%=op.getFechaInstruccion()%></td>
    </tr>

<%
   }
  }
%>
</body>
</html>
