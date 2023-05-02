<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%--
  Hecho al 100% por Francisco Javier Tejada Martín
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% PersonaEntity persona = (PersonaEntity) request.getAttribute("persona");
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
        else if (persona.getCuentaByCuenta().getEstadocuentaByEstado().getId() != 1){ %>
            <p style="color: red;">El estado de la cuenta es <%=persona.getCuentaByCuenta().getEstadocuentaByEstado().getDescripcion()%></p>
            <a href="/cajero/desbloqueo?id=<%= persona.getId() %>"> Realizar desbloqueo de la cuenta </a>
        <% }%>
        <% if (persona.getCuentaByCuenta().getEstadocuentaByEstado().getId() == 1){ %>
        <ul>
            <li><a href="/cajero/modificarcliente?id=<%= persona.getId() %>"> Modificar mis datos </a></li>
            <li><a href="/cajero/transferencia?id=<%= persona.getId() %>"> Realizar Transferencia </a></li>
            <li><a href="/cajero/sacardinero?id=<%= persona.getId() %>"> Sacar dinero </a></li>
            <li><a href="/cajero/cambiodivisas?id=<%= persona.getId() %>"> Realizar cambio de divisas y sacar dinero en esa divisa </a></li>
            <li><a href="/cajero/operaciones?id=<%= persona.getId() %>"> Ver operaciones realizadas </a></li>
            <li><a href="/cajero/desbloqueo?id=<%= persona.getId() %>"> Realizar desbloqueo de la cuenta </a></li>
        </ul>
        <% } %>
    </body>
</html>