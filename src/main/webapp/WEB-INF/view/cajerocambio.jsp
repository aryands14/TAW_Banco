<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.sql.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: frantejada
  Date: 20/03/2023
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% PersonaEntity persona = (PersonaEntity) request.getAttribute("persona");
    Date date = (Date) request.getAttribute("fecha");
    String error = (String) request.getAttribute("error");%>
<html>
    <head>
        <title>Cambio de Divisas</title>
    </head>
    <body>
    <%if (error != null) { %>
    <p style="color: red;"> <%= error %></p>
    <% } %>
    <form:form method="post" modelAttribute="cambiardinero" action="/cajero/guardarcambio">
        <form:hidden path="id"/>
        <form:hidden path="fechaInstruccion" value="<%=date%>"/>
        <form:hidden path="tipooperacionByTipo" value="4"/>
        <input type="hidden" name="persona" value="<%= persona.getId()%>"></input>
        <form:hidden path="moneda" value="euros"/>
        <form:select path="monedaCambio">
            <form:option value="libras" label="libras"/>
            <form:option value="dolares" label="dolares"/>
        </form:select> <br>
        <form:hidden path="cuentaByCuenta" value="<%=persona.getCuentaByCuenta().getId()%>"/>
        Cantidad: <form:input path="cantidad" required="true"/> <br>
        <form:button>Guardar</form:button>
    </form:form>
        <a href="/cajero/?id=<%= persona.getId() %>">Volver a atras</a>
    </body>
</html>