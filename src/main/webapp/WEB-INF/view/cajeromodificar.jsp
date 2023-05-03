<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo17.dto.Persona" %>
<%--
  Hecho al 100% por Francisco Javier Tejada Martín
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Persona persona = (Persona) request.getAttribute("cliente");
    Boolean guardar = (Boolean) request.getAttribute("guardar");
    int error = (int) request.getAttribute("error");
    String passwordant = persona.getContraseña();%>
<html>
    <head>
        <title>Modificar Cliente</title>
    </head>
    <body>
        <%if (error == 2) { %>
        <p style="color: red;"> Las contraseñas no coinciden</p>
        <% } %>
        <% if (error == 1) { %>
        <p style="color: red;"> Contraseña anterior no es correcta</p>
        <% } %>
        <% if (guardar != null) { %>
            <p style="color: blue;"> Se ha guardado correctamente</p>
        <% } %>
        <form:form modelAttribute="cliente" action="/cajero/guardarcliente" method="post">
            <form:hidden path="id"/>
            <form:hidden path="valida" />
            <form:hidden path="conversacionsById"/>
            <form:hidden path="mensajesById"/>
            <form:hidden path="operacionsById"/>
            <form:hidden path="tipopersonaByTipo"/>
            <form:hidden path="cuentaByCuenta"/>
            <form:hidden path="empresaByEmpresa"/>
            <form:hidden path="estadopersonaByEstado"/>
          NIF(*): <form:input path="nif" required="true"/> <br>
          Primer Nombre(*): <form:input path="primerNombre" required="true"/> <br>
          Segundo Nombre: <form:input path="segundoNombre"/> <br>
          Primer Apellido(*): <form:input path="primerApellido" required="true"/> <br>
          Segundo Apellido: <form:input path="segundoApellido"/> <br>
          Fecha Nacimiento(*): <form:input path="fechaNacimiento" required="true"/> <br>
          Calle(*): <form:input path="calle" required="true"/> <br>
          Numero(*): <form:input path="numero" required="true"/> <br>
          Planta-Puerta-Oficina(*): <form:input path="plantaPuertaOficina" required="true"/> <br>
          Ciudad(*): <form:input path="ciudad" required="true"/> <br>
          Region: <form:input path="region"/> <br>
          Pais(*): <form:input path="pais" required="true"/> <br>
          CP(*): <form:input path="cp" required="true"/> <br>
          <input type="hidden" value="<%=passwordant%>" name="ant">
          Contraseña Anterior para poder cambiarla: <input name="anterior2" type="password"/> <br>
          Nueva Contraseña(*): <form:password path="contraseña" name="password" value="<%=persona.getContraseña()%>"/> <br>
          Repetir Contraseña(*): <input type="password" name="repetircontraseña"> <br>
          <form:button>Guardar</form:button>
        </form:form>
        <a href="/cajero/?id=<%= persona.getId() %>">Volver a atras</a>
    </body>
</html>