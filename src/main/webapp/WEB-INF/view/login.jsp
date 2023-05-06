<%--
  Created by IntelliJ IDEA.
  User: aryan
  Date: 20/03/2023
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*
   @author: Álvaro Bermúdez Gámez 33%
   @author: Aryan Dilip Sadhwani Sadhwani 33%
   @author: Jaime Rodrigo Roldán Corcelles 33%
    */
%>
<%
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>Login</title>
</head>
<body>

<%
    if(error != null) {
%>
    <p style="color:red;">
            <%=error.toString()%>
    </p>
<%
    }
%>

<form action="/" method="post">
    <table>
        <tr>
            <td>Usuario:</td><td><input type="text" name="usuario"></td>
        </tr>
        <tr>
            <td>Contraseña:</td><td><input type="password" name="clave"></td>
        </tr>

    </table>
    <button>Login</button>
</form>

<form action="/registrarCliente" method="post">
    <button>Registar Cliente</button>
</form>
<form action="/registrarEmpresa" method="post">
    <button>Registar Empresa</button>
</form>


</body>
</html>
