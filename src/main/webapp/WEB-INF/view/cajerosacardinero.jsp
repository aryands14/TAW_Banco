<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.dto.Persona" %>
<%@ page import="java.sql.Date" %>
<%--
  Hecho al 100% por Francisco Javier Tejada Martín
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  Persona persona = (Persona) request.getAttribute("persona");
    Date date = (Date) request.getAttribute("fecha");
    String error = (String) request.getAttribute("error");%>
<html>
    <head>
        <title>Sacar Dinero</title>
    </head>
    <body>
        <%if (error != null) { %>
            <p style="color: red;"> <%= error %></p>
        <% } %>
        <form:form method="post" modelAttribute="sacardinero" action="/cajero/guardardinero">
            <form:hidden path="id"/>
            <form:hidden path="fechaInstruccion" value="<%=date%>"/>
            <form:hidden path="tipooperacionByTipo" value="1"/>
            <form:hidden path="personaByBeneficiario" value="<%=persona.getId()%>"/>
            <input type="hidden" name="persona" value="<%= persona.getId()%>">
            <form:hidden path="moneda" value="euros"/>
            <form:hidden path="cuentaByCuenta" value="<%=persona.getCuentaByCuenta()%>"/>
            Cantidad: <form:input path="cantidad" required="true"/> <br>
            <form:button>Guardar</form:button>
        </form:form>
        <a href="/cajero/?id=<%= persona.getId() %>">Volver a atras</a>
    </body>
</html>