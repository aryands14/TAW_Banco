<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: JRodrigo
  Date: 11/04/2023
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*
@author: Jaime Rodrigo Roldán Corcelles
 */
    String repContraseña = (String) request.getAttribute("repContraseña");
%>
<html>
<head>
    <title>Cliente</title>
</head>
<body>
<h1>Registro del Cliente </h1>
<form:form method="post" modelAttribute="cliente" action="/cliente/añadir">
    <legend>Datos del cliente</legend>
    <form:hidden path="id"/>
    <form:hidden path="estadopersonaByEstado"/>
    <form:hidden path="cuentaByCuenta"/>
    NIF(*) <form:input path="nif" required="true"/><br/>
    Primer nombre(*) <form:input path="primerNombre" required="true"/>
    Segundo nombre <form:input path="segundoNombre" required="false"/><br/>
    Primer apellido(*) <form:input path="primerApellido" required="true"/>
    Segundo apellido <form:input path="segundoApellido" required="false"/><br/>
    Fecha de nacimiento(*) <form:input path="fechaNacimiento" type="date" required="true"/><br/>


    <legend>Dirección</legend>
    Calle(*) <form:input path="calle" required="true"/>
    Número(*) <form:input path="numero" required="true"/><br/>
    Planta/Puerta/Oficina(*) <form:input path="plantaPuertaOficina" required="true"/><br/>
    Ciudad(*) <form:input path="ciudad" required="true"/>
    Región(*) <form:input path="region" required="true"/><br/>
    País(*) <form:input path="pais" required="true"/>
    C.P.(*) <form:input path="cp" required="true"/><br/>
    <form:checkbox path="valida" value="1" required="true"/>Dirección válida<br/>

    Contraseña(*) <form:password path="contraseña" required="true"/><br/>
    Contraseña Repetir(*) <input type="password" name="repetirContraseña" required="true">
    <%
        if(repContraseña!=null){


    %>
        Las contraseñas no coincidían
    <%
        }
    %>
    <br/>
    <form:button>Registrar</form:button>
</form:form>
<form method="post" action="/cliente/cancelarRegistro">
    <input type="submit" value="Cancelar">
</form>

</body>
</html>