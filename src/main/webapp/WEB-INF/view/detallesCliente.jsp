<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.entity.OperacionEntity" %>
<%--
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
  String url = "/operaciones/filtrar?id="+c.getId();
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
  }
%>


<h1>Operaciones del Cliente:</h1>

<form:form action="<%=url%>" modelAttribute="filtro" method="post">
  Tipo Operacion:<form:select multiple="true" path="tipos"
               items="${tiposOperacion}" itemValue="id" itemLabel="descripcion"></form:select><br>
  Ordenar por: Cantidad<form:checkbox path="cantidad" value="cantidad"/>
               Fecha<form:checkbox path="fecha" value="fecha"/>
  <form:button>Filtrar</form:button>
</form:form>

<a href="/gestor/visualizarcliente?id=<%=c.getId()%>">Quitar filtro</a>

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
  for(OperacionEntity op : operaciones) {
%>

  <tr>
    <td><%=op.getId()%></td>
    <td><%=op.getCuentaByCuenta().getId()%></td>
    <td><%=op.getCantidad()%></td>
    <td><%=op.getCantidadCambio()%></td>
    <td><%=op.getTipooperacionByTipo().getDescripcion()%></td>
    <td><%=op.getMoneda()%></td>
    <td><%=op.getFechaInstruccion()%></td>
  </tr>

<%
   }
  }
%>

</table>
</body>
</html>
