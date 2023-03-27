<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvaro
  Date: 27/03/2023
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Empresa</title>
</head>
<body>
<h1>Registro de la empresa</h1>
<form:form method="post" modelAttribute="empresa" action="/empresa/añadir">
    <legend>Datos de la empresa</legend>
    CIF(*) <form:input path="cif" required="true"/><br/>
    Nombre de la empresa(*) <form:input path="nombre" required="true"/><br/>
    <legend>Dirección</legend>
    Calle(*) <form:input path="calle" required="true"/>
    Número(*) <form:input path="numero" required="true"/><br/>
    Planta/Puerta/Oficina(*) <form:input path="plantaPuertaOficina" required="true"/><br/>
    Ciudad(*) <form:input path="ciudad" required="true"/>
    Región(*) <form:input path="region" required="true"/><br/>
    País(*) <form:input path="pais" required="true"/>
    C.P.(*) <form:input path="cp" required="true"/><br/>
    <form:checkbox path="valida" value="Válida"/>Dirección válida<br/>
    Contraseña (*) <form:input path="contraseña" required="true"/><br/>
    Contraseña Repetir(*) <input type="text" name="repetirContraseña" required="true"><br/>
    <form:button>Registrar</form:button>
</form:form>
<form method="post" action="/empresa/cancelarRegistro">
    <input type="submit" value="Cancelar">
</form>

</body>
</html>
