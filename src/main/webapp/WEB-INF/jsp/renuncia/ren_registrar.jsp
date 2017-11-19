<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../../jspf/general.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
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
<link href="<c:url value='/resources/css/renuncias.css'></c:url>" />
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
				<div class="row">
					<div class="container">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s3"></div>
								<div class="input-field col s5">
									<h5>Ingrese DNI del trabajador</h5>
									<input placeholder="DNI" id="dni" type="text" class="validate"
										onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;"
										maxlength=8 autofocus>
								</div>
								<div class="input-field col s4">
									<p>
										<a
											class="btn btn-large waves-effect waves-light yellow darken-4"
											id="buscarDetalle" onclick="buscarDetalle()">Buscar</a>
									</p>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="container" style="margin-bottom: 3em">
				<div id="detalleR">
					<section class="plans-container" id="plans"> <article
						class="col s12 m6 l4 ">
					<div class="card z-depth-2 ">
						<div class="card-image #304ffe indigo accent-4 waves-effect ">
							<div class="card-title">
								<h5>DETALLES DEL TRABAJADOR</h5>
							</div>
							<hr />
							<div class="price flow-text">
								<div class="col m12 l12 s12">
									<sup id="profile-page-content" class="row">
										<div class="row card-panel">
											<div class="col l1 m1 s6">
												<img src="<c:url value="/resources/img/user.png"/>" alt=""
													class="circle responsive-img valign profile-image">
											</div>
											<div class="col l3 m3 s6">
												<button
													class="btn #00bfa5 teal accent-4 waves-effect waves-light">
													<i class="mdi-image-camera-alt left"></i><span
														class="flow-text">Cambiar Foto</span>
												</button>
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
											<h5>Fecha de Inicio:</h5>
										</div>
										<div class="input-field col s4 ">
											<h5>
												<span id="fecha_inicio"></span><br>
											</h5>
										</div>
									</div>
								</form>
								<form class="col s12">
									<div class="row">
										<div class="input-field col s6 ">
											<h5>Dirección:</h5>
										</div>
										<div class="input-field col s4 ">
											<h5>
												<span id="direccion"></span><br>
											</h5>
										</div>
									</div>
								</form>
								<form class="col s12">
									<div class="row">
										<div class="input-field col s6 ">
											<h5>Departamento:</h5>
										</div>
										<div class="input-field col s4 ">
											<h5>
												<span id="departamento"></span><br>
											</h5>
										</div>
									</div>
								</form>
								<form class="col s12">
									<div class="row">
										<div class="input-field col s6 ">
											<h5>Area:</h5>
										</div>
										<div class="input-field col s4 ">
											<h5>
												<span id="area"></span><br>
											</h5>
										</div>
									</div>
								</form>
								<form class="col s12">
									<div class="row">
										<div class="input-field col s6">
											<h5>Sección:</h5>
										</div>
										<div class="input-field col s4 ">
											<h5>
												<span id="seccion"></span><br>
											</h5>
										</div>
									</div>
								</form>
								<form class="col s12">
									<div class="row">
										<div class="input-field col s6 ">
											<h5>Puesto:</h5>
										</div>
										<div class="input-field col s4 ">
											<h5>
												<span id="puesto"></span><br>
											</h5>
										</div>
									</div>
								</form>

								<form class="col s12">
									<div class="row">
										<div class="input-field col s6 ">
											<h5>Tipo de contrato:</h5>
										</div>
										<div class="input-field col s4 ">
											<h5>
												<span id="tipo_contrato"></span><br>
											</h5>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="card-content"></div>
						<div class="card-action center-align">
							<div class="col s12 m8 l9" id="adjuntar">
								<p>
									<a class="waves-effect waves-light btn modal-trigger  teal "
										href="#modal3">Adjuntar Carta de Renuncia</a>
								</p>
								<div id="modal3" class="modal modal modal-fixed-footer">
									<div class="modal-header  ">
										<h4>
											<b>ADJUNTAR CARTA DE RENUNCIA</b>
										</h4>
									</div>
									<div class="modal-content #009688 teal white-text">
										<div class="divider"></div>
										<div class="row section">
											<form class="col s12">
												<div class="row">
													<div id="mot" class="input-field col s6">
														<select id="motivo" multiple>

														</select>
													</div>
													<div class="input-field col s2">
														<h5>Fecha:</h5>
													</div>
													<div class="input-field col s4">
														<input placeholder="seleccione la fecha" id="first_name"
															type="date" class="datepicker">
													</div>
												</div>
											</form>
											<form action="" id="other" class="col s12" style="display:none">

												<div class="row">
													<form class="col s12">
														<div class="row">
															<div class="input-field col s12">
																<textarea id="otros" class="materialize-textarea"></textarea>
																<label for="otros">Ingrese el motivo en particular</label>
															</div>
														</div>
													</form>
												</div>
											</form>

											<div class="col s12 m8 l11">
												<input type="file" class="dropify" data-height="500" />
											</div>
										</div>
									</div>
									<div class="modal-footer #009688 teal ">
										<a href="#"
											class="waves-effect waves-red btn-flat modal-action #1a237e indigo darken-4 modal-close">Enviar</a>
										<a href="#"
											class="waves-effect waves-green btn-flat modal-action red modal-close">Cancelar</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					</article> </section>


				</div>
			</div>
		</div>
		<hr />

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