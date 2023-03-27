<%@ page import="es.taw.grupo17.entity.PersonaEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: aryan
  Date: 20/03/2023
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<PersonaEntity> listaClientes = (List<PersonaEntity>) request.getAttribute("clientes");
  List<PersonaEntity> listaEmpresas = (List<PersonaEntity>) request.getAttribute("empresas");

%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>Listado de clientes </h1>

<table border="2">
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Fecha Nacimiento </th>
    <th>CP </th>
  </tr>
  <%
    for(PersonaEntity c : listaClientes) {
  %>
  <tr>
    <td><%=c.getId()%></td>
    <td><%=c.getPrimerNombre()%></td>
    <td><%=c.getPrimerApellido()%></td>
    <td><%=c.getFechaNacimiento()%></td>
    <td><%=c.getCp()%></td>
    <td><a href="/gestor/visualizar?id=<%=c.getId()%>"/>Ver Detalles</td>
  </tr>
  <%
    }
  %>
  <%
    for(PersonaEntity c : listaEmpresas) {
  %>
  <tr>
    <td><%=c.getId()%></td>
    <td><%=c.getPrimerNombre()%></td>
    <td><%=c.getPrimerApellido()%></td>
    <td><%=c.getFechaNacimiento()%></td>
    <td><%=c.getCp()%></td>
    <td><a href="/gestor/visualizar?id=<%=c.getId()%>"/>Ver Detalles</td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>
