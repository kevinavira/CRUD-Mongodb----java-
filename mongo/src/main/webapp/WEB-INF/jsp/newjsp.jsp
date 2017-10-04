<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/js/js.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/JSON-to-table-master/lib/json-table.js" type="text/javascript"></script>
    </head>
    <body onload="cargar()">           
        <form method="POST" action="NewServlet">
            <select id="tipo" name="tipo" onchange="activarBoton()">
                <option value="select">Seleccione</option>
                <option value="insert">insertar</option>
                <option value="update">actualizar</option>
            </select>
            <label>id</label>
            <input id="f1" name="f1"/>
            <label>Nombre</label>
            <input id="nombre" name="nombre"/>           
            <label>Nuevo nombre</label>
            <input id="newname" name="newname" disabled=""/>
            <label>Edad</label>
            <input id="edad" name="edad"/>
            <input type="submit" value="enviar">
        </form>
        <div id="information"></div>

    </body>
</html>

