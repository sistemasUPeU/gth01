<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="<c:url value='resources/img/favicon/alpha.ico'/>"
	type="image/x-icon">

<title>GTH</title>

<%@include file="../../jspf/general.jspf"%>
</head>
<body onload="nobackbutton()" class="grey">

	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>

	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="login-form">
				<div class="row">
					<div class="input-field col s12 center">
						
						<p class="center login-form-text">Sistema de Gestión del
							Talento Humano</p>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-social-person-outline prefix"></i> <input
							id="txtUsuario" type="text" name="username"> <label for="txtUsuario"
							class="center-align">Usuario</label>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-action-lock-outline prefix"></i> <input
							id="txtClave" type="password" name="clave"> <label for="txtClave">Contraseña</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<a onclick="validarLogin()"
							class="btn waves-effect waves-light col s12">Ingresar</a>
							
							
							<input type="submit" class="btn waves-effect waves-light col s12 pink darken-4" value="Ingresar"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</div>
				</div>

			</form>
		</div>
	</div>
	<script src="<c:url value='resources/js/gthjs/login.js'/>"></script>
	<script>
		function nobackbutton() {
			window.location.hash = "no-back-button";
			window.location.hash = "Again-No-back-button";
			window.onhashchange = function() {
				window.location.hash = "";
			};
		}
	</script>

</body>
</html>