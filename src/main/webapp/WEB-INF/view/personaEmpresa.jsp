<%@ page import="es.taw.grupo17.entity.TipopersonaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo17.entity.EmpresaEntity" %>
<%@ page import="es.taw.grupo17.dto.Empresa" %>
<%@ page import="es.taw.grupo17.dto.Tipopersona" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvaro
  Date: 20/03/2023
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Empresa empresa = (Empresa) request.getAttribute("empresa");
    String repContraseña = (String) request.getAttribute("repContraseña");
    List<Tipopersona> listaTipos = (List<Tipopersona>) request.getAttribute("listaTipos");
    String url = "/empresa/anadirPersona?idEmpresa=" + empresa.getId();
%>
<html>
<head>
    <title>PersonaEmpresa</title>
</head>
<body>
<h1>Gestión de personas relacionadas con la empresa</h1>

<form:form action="<%=url%>" method="post" modelAttribute="persona" >
    <form:hidden path="id"/>
    <legend>Datos de la persona</legend>
    NIF(*) <form:input path="nif" required="true"/><br/>
    Primer Nombre(*) <form:input path="primerNombre" required="true"/><br/>
    Primer Apellido(*) <form:input path="primerApellido" required="true"/><br/>
    Segundo Nombre <form:input path="segundoNombre"/><br/>
    Segundo Apellido <form:input path="segundoApellido"/><br/>
    Fecha nacimiento(*) <form:input path="fechaNacimiento" type="date"/><br/>
    Tipo(*) <form:select path="tipopersonaByTipo" items="${listaTipos}" itemLabel="descripcion" itemValue="id"/><br/>
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
<form method="post" action="/empresa/cancelarRegistro">
    <input type="submit" value="Cancelar">
</form>
</body>
</html>
