<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.dto.Empresa" %>
<%@ page import="es.taw.grupo17.dto.Operacion" %><%--
  Created by IntelliJ IDEA.

  @author: Aryan Dilip Sadhwani Sadhwani

  Date: 01/04/2023
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*
@author: Aryan Dilip Sadhwani Sadhwani
*/
    Empresa e = (Empresa) request.getAttribute("empresa");
    List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");
    String url = "/operaciones/filtrarEmpresa?id="+e.getId();

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
        <th>CIF</th>
        <th>CP</th>
        <th>Numero</th>
        <th>Ciudad</th>
        <th>Planta/Puerta/Oficina</th>
        <th>Calle</th>
        <th>Estado</th>
        <th>Número Cuenta</th>
    </tr>

    <tr>
        <td><%=e.getId()%></td>
        <td><%=e.getNombre()%></td>
        <td><%=e.getCif()%></td>
        <td><%=e.getCp()%></td>
        <td><%=e.getNumero()%></td>
        <td><%=e.getCiudad()%></td>
        <td><%=e.getPlantaPuertaOficina()%></td>
        <td><%=e.getCalle()%></td>
        <td><%=e.getEstadopersonaByEstado()%></td>
        <td><%=e.getCuentaByCuenta()==null?"No tiene cuenta":e.getCuentaByCuenta()%></td>
    </tr>
</table>

<%
    }
%>


<h1>Operaciones de la Empresa:</h1>

<form:form action="<%=url%>" modelAttribute="filtro" method="post">
    Tipo Operacion:<form:select multiple="true" path="tipos"
                                items="${tiposOperacion}" itemValue="id" itemLabel="descripcion"></form:select><br>
    Ordenar por: Cantidad<form:checkbox path="cantidad" value="cantidad"/>
    Fecha<form:checkbox path="fecha" value="fecha"/>
    <form:button>Filtrar</form:button>
</form:form>

<a href="/gestor/visualizarempresa?id=<%=e.getId()%>">Quitar filtro</a>

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
</table>
<br><br>

<%
    if(e.getEstadopersonaByEstado() == 5) {
%>
<a href="/gestor/altaEmpresa?id=<%=e.getId()%>"/>Dar Alta</a><br><br>

<%
    }
%>

<a href="/gestor/"/>Volver</a>

</body>
</html>
