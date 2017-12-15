<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../../jspf/general.jspf"%>
<link href="https://fonts.googleapis.com/css?family=Dosis"
	rel="stylesheet">
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

<link href="<c:url value='/resources/css/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="<c:url value='/resources/css/renuncias.css'></c:url>" />

</head>
<body class="#e8f5e9 green lighten-5">
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
						<div id="fo" class="col s12" hidden>
							<div id="msj" class="card-panel teal lighten-2"></div>
						</div>



					</div>
				</div>
				<!-- AQUÍ SE INSERTA A TRAVÉS DE FORM PERO DAAHHH -->
				<!-- 				<div> -->
				<!-- 					<object data="" id="hola" type="application/pdf" width="300" -->
				<!-- 						height="200"> -->
				<!-- 						alt : <a href="test.pdf">test.pdf</a> -->
				<!-- 					</object> -->
				<!-- 				</div> -->
				<!-- 				<form method="post" action="form" enctype="multipart/form-data"> -->
				<!-- 					<input type="file" name="file"  class="dropify" -->
				<!-- 						accept="image/jpeg,image/png,image/gif,application/pdf" /> <input -->
				<!-- 						type="text" name="idtr" id="idtr" value=""> <input -->
				<!-- 						type="submit" value="Subir archivo" /> -->
				<!-- 				</form> -->

				<!-- 				<a href="#" onclick="Prueba()">ver</a> -->
			</div>
			<div class="container" style="margin-bottom: 3em">
				<div id="detalleR" style="display: none">
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
												<input type="hidden" id="idt" />
												<h6 class="light italic black-text">
													<strong>Nombres :</strong><span id="nombres"></span><br>
												</h6>
												<h6 class="light italic black-text">
													<strong> Apellido Paterno : </strong><span id="paternos"></span><br>
												</h6>
												<h6 class="light italic black-text">
													<strong> Apellido Materno : </strong><span id="maternos"></span><br>
												</h6>
												<h6 class="light italic black-text">
													<strong> Fecha de Nacimiento : </strong><span
														id="fecha_nacs"></span>
												</h6>
											</div>
										</div>
									</sup>
								</div>
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
							<form method="post" action="reg_aban" enctype="multipart/form-data"
								class="col s12 m8 l11" id="RenunciaForm">
								<input type="hidden" name="idcontrato" id="idcontrato" value="">
								<div class="col s12 m8 l9" id="adjuntar">
									<p class="center  m,">
										<a class="waves-effect waves-light btn modal-trigger  teal "
											href="#modal3">Adjuntar Carta de Renuncia</a>
									</p>

									<div id="modal3" class="modal modal-fixed-footer"
										style="width: 80%; height: 80%; border: 5px solid black">
										<div class="modal-header #1de9b6 teal lighten-1">
											<div class="center">
												<h4 style="font-family: 'Dosis', sans-serif;">Registrar
													renuncia</h4>
											</div>
										</div>
										<div class="modal-content #e0f7fa cyan lighten-5"
											style="z-index: 0">

											<div class="row section">
												<div class="col s12">
													<div class="row">
														<div class="input-field col s5">
															<h5 style="font-family: 'Dosis', sans-serif;">Ingrese
																el motivo de la renuncia:</h5>
														</div>
														<div class="input-field col s2">
															<h5 style="font-family: 'Dosis', sans-serif;">
																Fecha de entrega <br>de carta de renuncia:
															</h5>
														</div>
														<div class="input-field col s5">
															<h5 style="font-family: 'Dosis', sans-serif;">Adjunte
																la carta de renuncia:</h5>
														</div>

													</div>
												</div>
												<div class="col s12">
													<div id="mot" class="input-field col s5">
														<select id="motivo" multiple>

														</select>
													</div>
													<div class="input-field col s2">
														<label for=""></label> <input type="text" name="fecha"
															id="fecha" class="datepicker">
														<!-- 														 <input type="text"  id="fechap"> -->

													</div>
													<div class="input-field col s5">

														<input type="file" name="file" class="dropify" id="pelon1"
															data-height="300" />
													</div>
													<div class="row" id="otrosdiv">
														<div class="col s12">
															<div class="row">
																<div class="input-field col s6">
																	<textarea id="otros" class="materialize-textarea"></textarea>
																	<label for="otros">Ingrese el motivo en
																		particular</label>
																</div>
															</div>
														</div>
													</div>
													<input type="hidden" id="array_motivos" />
												</div>
											</div>
										</div>

										<div class="modal-footer  teal lighten-1  darken-2"
											style="z-index: 5; position: fixed">
											<div class="row">

												<div class="col s4" style="margin-right: 2em;">
													<button type="submit"
														class="btn waves-effect waves-light green indigo"
														id="RegistrarR">
														Enviar <i class="mdi-content-send right"></i>
													</button>

												</div>
												<div class="col s4" style="margin-right: 2em">
													<a class="btn waves-effect waves-light red modal-close">
														Cancelar </a>

												</div>
												<div class="col s4"></div>
												<input type="hidden" id="array_motivos" />
											</div>
										</div>

									</div>
							</form>
						</div>

					</div>
					</article> </section>
				</div>
			</div>
		</div>
		<hr />

	</div>


	<!-- 	<script -->
	<%-- 		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->

	<!-- 	<script -->
	<%-- 		src="<c:url value='/resources/js/plugins/jquery-1.11.2.min.js'></c:url>" --%>
	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>
	<!-- 		type="text/javascript"></script> -->

	<script
		src="<c:url  value='/resources/js/RegistrarAbandono.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
		type="text/javascript"></script>

	<%-- 	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>
</body>
</html>