<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../../jspf/general.jspf"%>

<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/js/materialize.min.js'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/materialize.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
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
		<div id="table-datatables">
			<div class="wrapper">
				<section id="content" class="col m12 l12 s12"> </section>

			</div>

			<div class="container">
				<section id="content" class="col m12 l12 s12">
				<div id="profile-page-content" class="row">
					<div class="row card-panel">
						<div class="col l1 m1 s6">
							<img src="<c:url value="/resources/img/user.png"/>" alt=""
								class="circle responsive-img valign profile-image">
						</div>
						<div class="col l3 m3 s6">
							<button class="btn green accent-3 waves-effect waves-light">
								<i class="mdi-image-camera-alt left"></i>Cambiar Foto
							</button>
							<h6 class="light italic black-text">
								<strong><label>Nombres : </label></strong> Nicole<br> <strong><label>Apellido
										Paterno : </label></strong> Garcia<br> <strong><label>Apellido
										Materno : </label></strong> Guevara<br> <strong><label>Fecha
										de Nacimiento : </label></strong> 05/12/1998
							</h6>
						</div>
					</div>
				</div>
				</section>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Feca de Inicio:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="fecha de inicio" id="first_name" type="date"
								class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Dirección:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="Dirección" id="first_name" type="text"
								class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Departamento:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="Departamento" id="first_name" type="text"
								class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Area:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="Area" id="first_name" type="text"
								class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Sección:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="sección" id="first_name" type="text"
								class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Puesto:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="puesto" id="first_name" type="text"
								class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Centro costo N°1:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="puesto" id="first_name" type="text"
								class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Tipo de contrato:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="titpo de contrato" id="first_name"
								type="text" class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Antecedentes Policiales:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="titpo de contrato" id="first_name"
								type="text" class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Certificado de salud:</h5>
						</div>
						<div class="input-field col s4">
							<input placeholder="titpo de contrato" id="first_name"
								type="text" class="validate">
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s6">
							<h5>Carta de Renuncia:</h5>
						</div>
						<div class="divider"></div>
						<div class="row section">
							<div class="col s12 m8 l5">
								<input type="file" id="input-file-now" class="dropify"
									data-default-file="" /> <a
									class="btn waves-effect waves-light light-blue darken-4">ver
									mas detalles</a>
							</div>
						</div>
					</div>
				</form>
				<form class="col s12">
					<div class="row">
						<div class="input-field col s3"></div>
						<div class="input-field col s3	">
							<a
								class="btn btn-large waves-effect waves-light light-green darken-4">Autorizar</a>
						</div>
						<div class="input-field col s3">
							<div class="col s12 m8 l9">
								<p>
									<a
										class="waves-effect waves-light btn modal-trigger btn-large red darken-4 "
										href="#modal3">Rechazar</a>
								</p>
								<div id="modal3" class="modal">
									<div class="modal-content lead black-text ">
										<p><h4>Motivos de Rechazo</h4>
										</p>
											<hr>
											<form class="col s12">
												<div class="row">
													<div class="input-field col s6">
														<h5>Descripción de rechazo:</h5>
													</div>
													<div class="input-field col s1">
														<h5>Fecha:</h5>
													</div>
													<div class="input-field col s3">												
														<input placeholder="titpo de contrato" id="first_name"
															type="date" class="validate">
													</div>
													<div class="input-field col s1">
														
													</div>
												</div>
											</form>
											<div class="row">
												<form class="col s12">
													<div class="row">
														<div class="input-field col s12">
															<textarea id="textarea1" class="materialize-textarea"></textarea>
															<label for="textarea1" class='active'>Textarea</label>
														</div>
													</div>
												</form>
											</div>
									</div>
									<div class="modal-footer  black lighten-4 black darken-4">
										<a href="#"
											class="waves-effect waves-light btn modal-action  green modal-close ">Enviar</a>
										<a href="#"
											class="waves-effect waves-light btn modal-action  red modal-close">Cancelar</a>
									</div>
								</div>
							</div>
						</div>
						<div class="input-field col s3"></div>
					</div>
				</form>
			</div>
		</div>
		<br> <br> <br>
		<script
			src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
			type="text/javascript"></script>
		<!-- 	<script -->
		<%-- 		src="<c:url value='/resources/js/plugins/jquery-1.11.2.min.js'></c:url>" --%>
		<!-- 		type="text/javascript"></script> -->
		<script src="<c:url  value='/resources/js/carpeta.js'></c:url>"
			type="text/javascript"></script>
		<script
			src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
			type="text/javascript"></script>
		<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%>
</body>
</html>