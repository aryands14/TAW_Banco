<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.ui.FiltroOperacion2" %>
<%@ page import="es.taw.grupo17.dto.Persona" %>
<%@ page import="es.taw.grupo17.dto.Operacion" %>
<%@ page import="java.util.List" %>
<%--
  Hecho al 100% por Francisco Javier Tejada Martín
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Persona persona = (Persona) request.getAttribute("persona");
    List<Operacion> operaciones = (List<Operacion>) request.getAttribute("operaciones");
    FiltroOperacion2 filtro = (FiltroOperacion2) request.getAttribute("filtro");%>
            <html>
    <head>
        <title>Ver operaciones</title>
    </head>
    <body>
    <h1>Operaciones de la Persona:</h1>

    <form:form action="/cajero/operaciones" method="get" modelAttribute="filtro">
        Filtra por fecha: <form:input type="date" path="fecha" value="<%=filtro.getFecha() != null ? filtro.getFecha() : &quot;1970-01-01&quot; %>"/>
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
    for(Operacion op : operaciones) {
%>

        <tr>
            <td><%=op.getId()%></td>
            <td><%=op.getCuentaByCuenta() != null? op.getCuentaByCuenta() : ""%></td>
            <td><%=op.getPersonaByBeneficiario() != null ? op.getPersonaByBeneficiario() : ""%></td>
            <td><%=op.getCantidad()%></td>
            <td><%=op.getCantidadCambio() != null ? op.getCantidadCambio() : ""%></td>
            <td>
                <% if (op.getTipooperacionByTipo() == 1){ %>
                    Retirada
                <% } else if (op.getTipooperacionByTipo() == 2){ %>
                    Ingreso
                <% } else if (op.getTipooperacionByTipo() == 3){ %>
                    Pago
                <% } else{ %>
                    Cambio divisa
                <% } %>
            </td>
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