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
<form:form method="post" modelAttribute="empresa" action="empresa/añadir">
    <legend>Datos de la empresa</legend>
    CIF(*) <form:input path="cif" /><br/>
    Nombre de la empresa(*) <form:input path="nombre"/><br/>
    <legend>Dirección</legend>
    Calle(*) <form:input path="calle" required="true" />
    Número(*) <form:input path="numero"/><br/>
    Planta/Puerta/Oficina(*) <form:input path="plantaPuertaOficina"/><br/>
    Ciudad(*) <form:input path="ciudad"/>
    Región(*) <form:input path="region"/><br/>
    País(*) <form:input path="pais"/>
    C.P.(*) <form:input path="cp"/><br/>
    <form:input path="valida" type=""/>Dirección válida/><br/>
    Contraseña (*) <form:input path="contraseña"/><br/>
    Contraseña Repetir(*) <input type="text" name="repetirContraseña"><br/>
    <form:button>Registra</form:button>
</form:form>

</body>
</html>
