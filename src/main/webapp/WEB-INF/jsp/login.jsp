<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GTH</title>
<link href="<c:url value='resources/css/layouts/page-center.css" type="text/css'/>" 	rel="stylesheet" media="screen,projection">
<%@include file="../../jspf/general.jspf"%>
</head>
<body class="grey" >
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>

	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="login-form" action="login" method="POST">
				<div class="row">
					<div class="input-field col s12 center">

						<p class="center login-form-text">Sistema de Gestión del
							Talento Humano</p>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-social-person-outline prefix"></i> <input
							id="txtUsuario" type="text" name="username"> <label
							for="txtUsuario" class="center-align">Usuario</label>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-action-lock-outline prefix"></i> <input
							id="txtClave" type="password" name="password"> <label for="txtClave">Contraseña</label>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<c:if test="${not empty error}">
							<div id="card-alert" class="card red lighten-5">
								<div class="card-content red-text">
									<p>${error}</p>
								</div>
								<button type="button" class="close red-text"
									data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
							</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg">${msg}</div>
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<!-- <button onclick="validarLogin()"
							class="btn waves-effect waves-light col s12 green accent-3" type="button">Ingresar</button> -->


						<button type="submit"
							class="btn waves-effect waves-light col s12 green accent-3">Ingresar</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
				</div>
<!-- borrar esto -->
			</form>
		</div>
	</div>
	<script src="<c:url value='resources/js/gthjs/login.js'/>"></script>
</body>
</html>