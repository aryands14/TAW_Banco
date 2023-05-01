<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="es.taw.grupo17.entity.CuentaEntity" %>
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
        <title>Transferencia</title>
    </head>
    <body>
        <%if (error != null) { %>
        <p style="color: red;"> <%= error %></p>
        <% } %>
        <form:form method="post" modelAttribute="transferencia" action="/cajero/guardartransferencia">
            <form:hidden path="id"/>
            <form:hidden path="fechaInstruccion" value="<%=date%>"/>
            <form:hidden path="tipooperacionByTipo" value="3"/>
            <form:hidden path="cuentaByCuenta" value="<%=persona.getCuentaByCuenta().getId()%>"/> <br>
            <input type="hidden" name="persona" value="<%= persona.getId()%>"></input>
            <form:hidden path="moneda" value="euros"/>
            Cuenta de la Persona: <form:input path="personaByBeneficiario" />
            Cantidad: <form:input path="cantidad" required="true"/> <br>

            <form:button>Guardar</form:button>
        </form:form>
        <a href="/cajero/?id=<%= persona.getId() %>">Volver a atras</a>
    </body>
</html>