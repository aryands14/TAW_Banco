<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.sql.Date" %>
<%@ page import="es.taw.grupo17.entity.OperacionEntity" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.taw.grupo17.ui.FiltroOperacion" %>
<%--
  Created by IntelliJ IDEA.
  User: frantejada
  Date: 20/03/2023
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% PersonaEntity persona = (PersonaEntity) request.getAttribute("persona");
    Collection<OperacionEntity> operaciones = (Collection<OperacionEntity>) request.getAttribute("operaciones");
    FiltroOperacion filtro = (FiltroOperacion) request.getAttribute("filtro");%>
            <html>
    <head>
        <title>Ver operaciones</title>
    </head>
    <body>
    <h1>Operaciones de la Persona:</h1>

    <form:form action="/cajero/operaciones" method="get" modelAttribute="filtro">
        Filtra por fecha: <form:input type="date" path="fecha" value="1970-01-01"/>
        <input type="hidden" name="id" value="<%=persona.getId()%>">
        <form:select path="tipo">
            <form:option label="----" selected="true" value="0"/>
            <form:option value="1" label="Retirada"/>
            <form:option value="2" label="Ingreso"/>
            <form:option value="3" label="Pago" />
            <form:option value="4" label="Cambio divisa"/>
        </form:select>
        <form:button/>
    </form:form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Cuenta</th>
            <th>Persona Beneficiaria</th>
            <th>Cantidad</th>
            <th>Cantidad Cambio </th>
            <th>Tipo Operacion</th>
            <th>Moneda</th>
            <th>Moneda Cambio</th>
            <th>Fecha</th>
        </tr>


            <%
  if(operaciones != null) {
    for(OperacionEntity op : operaciones) {
%>

        <tr>
            <td><%=op.getId()%></td>
            <td><%=op.getCuentaByCuenta() != null? op.getCuentaByCuenta().getId() : ""%></td>
            <td><%=op.getPersonaByBeneficiario() != null ? op.getCuentaByCuenta().getId() : ""%></td>
            <td><%=op.getCantidad()%></td>
            <td><%=op.getCantidadCambio() != null ? op.getCantidadCambio() : ""%></td>
            <td><%=op.getTipooperacionByTipo().getDescripcion()%></td>
            <td><%=op.getMoneda()%></td>
            <td><%=op.getMonedaCambio() != null ? op.getMonedaCambio() : ""%></td>
            <td><%=op.getFechaInstruccion()%></td>

        </tr>

            <%
        }
    }
    %>
    </table>
        <a href="/cajero/?id=<%= persona.getId() %>">Volver a atras</a>
    </body>

</html>