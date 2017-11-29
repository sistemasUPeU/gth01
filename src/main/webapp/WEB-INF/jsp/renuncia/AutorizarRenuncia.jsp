<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
<%@include file="../../../jspf/general.jspf"%>

<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/dataTables.material.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
</head>

<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>

	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>
		<div class="col s12 m8 l9">
			<div id="data-table-row-grouping col s12 m8 l9"
				class="card-panel display #e3f2fd blue lighten-5"
				style="position: absolute">

				<div class="col s12 m8 l9 contT"></div>
			</div>
		</div>
	</div>

	<div id="modal2" class="modal">
		<div class="modal-header #1de9b6 teal accent-3">
			<div class="center">
				<h4 style="font-family: 'Dosis', sans-serif; color: grey">Autorización
					de renuncia</h4>
			</div>
		</div>
		<div class="modal-content #e0f7fa cyan lighten-5" style="z-index: 0">

			<div class="card z-depth-2" style="width: 80%; margin-left: 5%">
				<div class="card-image #424242 grey darken-3 waves-effect ">

					<hr />
					<div class="price flow-text ">
						<div class="col m12 l12 s12">
							<sup id="profile-page-content" class="row">
								<div class="row card-panel">
									<div class="col l1 m1 s6">
										<img src="<c:url value="/resources/img/user.png"/>" alt=""
											class="circle responsive-img valign profile-image">
									</div>
									<div class="col l3 m3 s6">
										<input type="hidden" id="idt" />
										<h6 class="light italic black-text">
											<strong><h6>Nombres :</strong><span id="nombres"></span><br>
											<strong>
										</h6>
										<h6>
											Apellido Paterno : </strong><span id="paterno"></span><br> <strong>
										</h6>
										<h6>
											Apellido Materno : </strong><span id="materno"></span><br> <strong>
										</h6>
										<h6>
											Fecha de Nacimiento : </strong><span id="fecha_nac"></span>
										</h6>
										</h6>
									</div>
								</div>
						</div>
						</sup>
					</div>
					<div class="price-desc white-text">
						<form class="col s12 ">
							<div class="row">
								<div class="input-field col s6  ">
									<h6>Fecha de Inicio:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="fecha_inicio"></span><br>
									</h6>
								</div>
							</div>
						</form>
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6 ">
									<h6>Dirección:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="direccion"></span><br>
									</h6>
								</div>
							</div>
						</form>
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6 ">
									<h6>Departamento:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="departamento"></span><br>
									</h6>
								</div>
							</div>
						</form>

						<form class="col s12">
							<div class="row">
								<div class="input-field col s6 ">
									<h6>Area:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="area"></span><br>
									</h6>
								</div>
							</div>
						</form>
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<h6>Sección:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="seccion"></span><br>
									</h6>
								</div>
							</div>
						</form>
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6 ">
									<h6>Puesto:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="puesto"></span><br>
									</h6>
								</div>
							</div>
						</form>

						<form class="col s12">
							<div class="row">
								<div class="input-field col s6 ">
									<h6>Tipo de contrato:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="tipo_contrato"></span><br>
									</h6>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="modal-footer #1de9b6 teal accent-3" style="z-index: 2">

				<div class="col s6" style="margin-right: 2em;">
					<button type="submit" class="btn waves-effect waves-light indigo"
						id="RegistrarR">
						Enviar <i class="mdi-content-send right"></i>
					</button>

				</div>
				<div class="col s6" style="margin-right: 2em">
					<a class="btn waves-effect waves-light blue-grey modal-close">
						Cancelar </a>

				</div>
				<!-- 											<a href="#" -->
				<!-- 												class="waves-effect waves-green btn-flat modal-action red modal-close">Cancelar</a> -->




			</div>
		</div>
	</div>

	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
	<%-- 		<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>

	<script
		src="<c:url value='/resources/js/businessCore/AutorizarRenuncia.js'></c:url>"
		type="text/javascript"></script>
<body>
</html>