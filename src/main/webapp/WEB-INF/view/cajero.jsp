<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: frantejada
  Date: 20/03/2023
  Time: 13:58
  To change this template use File | Settings | File Templates.
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
        <% } %>
        <ul>
            <li><a href="/cajero/modificarcliente?id=<%= persona.getId() %>"> Modificar mis datos </a></li>
            <li><a href="/cajero/transferencia?id=<%= persona.getId() %>"> Realizar Transferencia </a></li>
            <li><a href="/cajero/sacardinero?id=<%= persona.getId() %>"> Sacar dinero </a></li>
            <li><a href="/cajero/cambiodivisas?id=<%= persona.getId() %>"> Realizar cambio de divisas y sacar dinero en esa divisa </a></li>
            <li><a href="/cajero/operaciones?id=<%= persona.getId() %>"> Ver operaciones realizadas </a></li>
            <li><a href="/cajero/desbloqueo?id=<%= persona.getId() %>"> Realizar desbloqueo de la cuenta </a></li>
        </ul>
    </body>
</html>