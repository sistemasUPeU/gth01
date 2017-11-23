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
<body class="#ede7f6 deep-purple lighten-5">
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
				<div>
					<object data="" id="hola" type="application/pdf" width="300"
						height="200">
						alt : <a href="test.pdf">test.pdf</a>
					</object>
				</div>
				<form method="post" action="form" enctype="multipart/form-data">
					<input type="file" name="file"
						accept="image/jpeg,image/png,image/gif,application/pdf" /> <input
						type="text" name="idtr" id="idtr" value=""> <input
						type="submit" value="Subir archivo" />
				</form>

				<!-- 				<a href="#" onclick="Prueba()">ver</a> -->
			</div>
			<div class="container" style="margin-bottom: 3em">
				<div id="detalleR">
					<section class="plans-container" id="plans"> <article
						class="col s12 m6 l4 ">
					<div class="card z-depth-2" style="width: 75%; margin-left: 5%">
						<div class="card-image #424242 grey darken-3 waves-effect ">
							<div class="card-title">
								<h5>DETALLES DEL TRABAJADOR</h5>
							</div>
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
												<input type="hidden"  id="idt"/>
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
						<div class="card-content"></div>
						<div class="card-action center-align">
							<div class="col s12 m8 l9" id="adjuntar">
								<p>
									<a class="waves-effect waves-light btn modal-trigger  teal "
										href="#modal3">Adjuntar Carta de Renuncia</a>
								</p>
								<div id="modal3" class="modal modal modal-fixed-footer">
									<div class="modal-header  ">
										<h4>Registrar renuncia</h4>
									</div>
									<div class="modal-content #00695c teal darken-3 white-text"
										style="z-index: 0">

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
														<input placeholder="seleccione la fecha" type="date"
															id="pelon" class="datepicker">
													</div>
												</div>
											</form>
											<form action="" id="other" class="col s12"
												style="display: none">

												<div class="row">
													<form class="col s12">
														<div class="row">
															<div class="input-field col s12">
																<textarea id="otros" class="materialize-textarea"></textarea>
																<label for="otros">Ingrese el motivo en
																	particular</label>
															</div>
														</div>
													</form>
												</div>
											</form>
											<form method="post" action="form"
												enctype="multipart/form-data" class="col s12 m8 l11" >
												<input type="file" name="file" class="dropify" id="pelon1"
													data-height="500"
													accept="image/jpeg,image/png,image/gif,application/pdf" />
<!-- 												<input type="hidden" name="idtr" id="idtr" value=""> <input -->
<!-- 													type="submit" value="Subir archivo" /> -->
											</form>
										</div>
									</div>

									<div class="modal-footer #00695c teal darken-3 "
										style="z-index: 2">
										<a href="#" onclick="Pruebita()"
											class="waves-effect waves-red btn-flat modal-action #1a237e 
											indigo darken-4 modal-close">Enviar</a>
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