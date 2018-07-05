<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="<c:url value='/resources/css/page-center.css" type="text/css'/>"
	rel="stylesheet" media="screen,projection">
<%@include file="../../jspf/general.jspf"%>
</head>
<body class="white">
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel grey darken-4">
			<form class="login-form" action="login" method="POST">
				<div class="row grey darken-4">
				<div class="input-field col s12 center">
						<img src="<c:url value="/resources/img/gth.png"/>" class="col s12" alt="">	
						</div>					
				</div>
				<div class="row">
					<div class="input-field col s12 center">

						<p class="center login-form-text white-text">Sistema de Gestión del
							Talento Humano</p>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-social-person-outline prefix green-text accent-3"></i> <input
							id="txtUsuario" type="text" name="username" class="white-text"> <label
							for="txtUsuario" class="center-align">Usuario</label>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-action-lock-outline prefix green-text accent-3"></i> <input
							id="txtClave" type="password" name="password" class="white-text"> <label
							for="txtClave">Contraseña</label>
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
							<div id="card-alert" class="card grey">
								<div class="card-content white-text thin">
									<p>${msg}</p>
								</div>
								<button type="button" class="close white-text"
									data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
							</div>
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<button type="submit"
							class="btn waves-effect waves-light col s12 green accent-3">Ingresar</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>