<%@ page import="es.taw.grupo17.dto.Persona" %>
<%@ page import="es.taw.grupo17.dto.Estadocuenta" %>
<%--
  Hecho al 100% por Francisco Javier Tejada Martín
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Persona persona = (Persona) request.getAttribute("persona");
    Estadocuenta estado = (Estadocuenta) request.getAttribute("estado");
    String operacion = (String) request.getAttribute("operacion");%>
<html>
    <head>
        <title>Cajero</title>
    </head>
    <body>
        <h1>Funciones del cajero</h1>
        <% if ( operacion != null) { %>
        <p style="color: blue;"><%=operacion%></p>
        <% }
        else if (estado.getId() == 3){ %>
            <p style="color: red;">El estado de la cuenta es <%=estado.getDescripcion()%></p>
            <a href="/cajero/desbloqueo?id=<%= persona.getId() %>"> Realizar desbloqueo de la cuenta </a>
        <% } else if (estado.getId() != 1){%>
            <p style="color: red;">El estado de la cuenta es <%=estado.getDescripcion()%></p>
        <% } %>
        <% if (estado.getId() == 1){ %>
        <ul>
            <li><a href="/cajero/modificarcliente?id=<%= persona.getId() %>"> Modificar mis datos </a></li>
            <li><a href="/cajero/transferencia?id=<%= persona.getId() %>"> Realizar Transferencia </a></li>
            <li><a href="/cajero/sacardinero?id=<%= persona.getId() %>"> Sacar dinero </a></li>
            <li><a href="/cajero/cambiodivisas?id=<%= persona.getId() %>"> Realizar cambio de divisas y sacar dinero en esa divisa </a></li>
            <li><a href="/cajero/operaciones?id=<%= persona.getId() %>"> Ver operaciones realizadas </a></li>
        </ul>
        <% } %>
    </body>
</html>