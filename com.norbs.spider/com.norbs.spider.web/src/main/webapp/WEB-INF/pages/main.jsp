<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Styles -->
        <link rel="stylesheet" href="../assets/css/style.css"

              <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container" style=" margin: 200px auto 0 auto;">
            <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
            <c:if test="${not empty mensaje}"><div class="alert alert-success">${mensaje}</div></c:if>
            <a href="${contextPath}/catalogo" class="btn btn-default" role="button"><i class="fa fa-sign-out" aria-hidden="true"></i> Cargar Catalogos</a>
            <a href="${contextPath}/spider" class="btn btn-primary" role="button"><i class="fa fa-sign-out" aria-hidden="true"></i> Descargar Portadas</a>
            <a href="${contextPath}/logout" class="btn btn-danger" role="button"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a>
        </div>
</html>
