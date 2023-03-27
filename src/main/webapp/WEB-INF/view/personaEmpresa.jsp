<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvaro
  Date: 20/03/2023
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PersonaEmpresa</title>
</head>
<body>
<h1>Gestión de personas relacionadas con la empresa</h1>

<form action="empresa/añadir" method="post" >
    <legend>Datos personales</legend>
    NIF(*): <input type="text" name="NIF" required><br/>
    Primer Nombre(*): <input type="text" name="primerNombre" required><br/>
    Primer Apellido(*): <input type="text" name="segundoNombre" required><br/>
    Segundo Nombre: <input type="text" name="segundoNombre"><br/>
    Segundo Apellido: <input type="text" name="segundoApellido"><br/>
    Fecha nacimiento(*): <input type="date" name="fechaNacimiento" required><br/>
    Tipo(*):
    <select name="tipo" required>
        <option value="Socio">Socio</option>
        <option value="Representante">Representante</option>
    </select><br/>
    <legend>Dirección</legend>
    Calle(*): <input type="text" name="calle" required><br/>
    Número(*): <input type="text" name="numero" required><br/>
    Planta/Puerta/Oficina(*): <input type="text" name="planta/puerta/oficina" required><br/>
    Ciudad(*): <input type="text" name="ciudad" required><br/>
    País(*): <input type="text" name="pais" required><br/>
    Región: <input type="text" name="region"><br/>
    C.P.(*): <input type="text" name="CP" required><br/>
    <input type="radio" name="valida">Dirección válida<br/>
    <button>Añadir</button>
</form>
</body>
</html>
