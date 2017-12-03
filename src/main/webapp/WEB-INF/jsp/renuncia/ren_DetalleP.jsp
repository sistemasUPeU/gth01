<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../../jspf/general.jspf"%>
<html>
<head>
</head>
<body>
	<div class="card " style="width: 100%; margin-left: 5%">
		<section class="plans-container" id="plans">
			<div class="card " style="width: 85%; margin-left: 5%">
				<div class="card-image #424242 grey darken-3 waves-effect ">
					<div class="card-title">
						<h5>DETALLES DEL TRABAJADOR</h5>
					</div>
					<hr />
					<div class="price flow-text ">
						<div class="col m12 l12 s12">
							<sup id="profile-page-content" class="row">
								<div class="row card-panel" style="text-align: center">
									<div class="col l6 m6 s6">
										<input type="hidden" id="idt" />
										<h6 class="light italic black-text">
											<strong><h6>Nombres :</strong><span id="nombres"></span><br>
											<strong>
										</h6>
										<input type="hidden" id="idr" />
										<h6>
											Apellido Paterno : </strong><span id="paterno"></span><br> <strong>
										</h6>
										<h6>
											Apellido Materno : </strong><span id="materno"></span><br> <strong>
										</h6>
										<h6>
											Fecha de Nacimiento : </strong><span id="fecha_nac"></span>
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
								<div class="input-field col s6">
									<h6>Centro costo n°1:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="centro_costo"></span><br>
									</h6>
								</div>
							</div>
						</form>
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<h6>Tipo de contrato:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="tipo_contrato"></span><br>
									</h6>
								</div>
							</div>
						</form>
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<h6>Antecedentes Policiales:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="antecedentes_policiales"></span><br>
									</h6>
								</div>
							</div>
						</form>
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<h6>Certificado de salud:</h6>
								</div>
								<div class="input-field col s4 ">
									<h6>
										<span id="certificado_salud"></span><br>
									</h6>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="card-content">
					<form class="col s12">
						<div class="row">
							<div class="input-field col s6" style="text-align: center">
								<h6>Carta de Renuncia:</h6>
							</div>
							<div class="input-field col s6">
								<div class="material-placeholder">
									<!-- 									<img materialboxed class="materialboxed" -->
									<!-- 										data-caption="A picture of some deer and tons of trees" -->
									<!-- 										width="250" " style="z-index: 4" -->
									<%--
									 										src="<c:url value="/resources/img/carta de renuncia.png"/>" /> --%>
									<span id="carta">
									<img materialboxed class="materialboxed"
										data-caption="A picture of some deer and tons of trees"
										width="250" " style="z-index: 4"
										 id="carta"/>
									</span>
									
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</section>
		<form action="col s12">
			<div class="row">
				<div class="input-field col s6 center">
					<a
						class="btn btn-large waves-effect waves-light light-green darken-4 "
						id="procesarRen">Procesar</a>
				</div>
				<div class="input-field col s6 center">
					<a
						class="waves-effect waves-light btn modal-trigger btn-large red darken-4 "
						href="#modal3">Rechazar</a>
				</div>
			</div>
		</form>
	</div>
	<br>
	<br>
	<br>
	<div id="modal3" class="modal modal-fixed-footer"
		style="width: 60%; height: 80%; border: 5px solid black">
		<div class="modal-content lead black-text ">
			<p>
			<h4>Motivos de Rechazo</h4>
			</p>
			<hr>
			<form class="col s12">
				<div class="row">
					<div class="col s12">
						<div class="input-field col s7">
							<h5>Descripción de rechazo:</h5>
						</div>
						<!-- 						<div class="input-field col s5"> -->
						<!-- 							<h5 style="font-family: 'Dosis', sans-serif;">Fecha:</h5> -->
						<!-- 						</div> -->

					</div>

				</div>
			</form>
			<form action="col s12">
				<div class="row">

					<div class="input-field col s12">
						<textarea id="observacion" class="materialize-textarea"></textarea>
					</div>
					<!-- 					<div class="input-field col s6"> -->
					<!-- 						<input type="text" id="Date" class="datepicker" /> -->
					<!-- 					</div> -->
				</div>
			</form>
		</div>
		<div class="modal-footer  black lighten-1  darken-2"
			style="z-index: 5; position: fixed">
			<div class="row">
				<div class="col s4">
					<a href="#"
						class="waves-effect waves-light btn modal-action  green modal-close "
						id="RechazarRenuncia">Enviar</a>
				</div>
				<div class="col s4">
					<a href="#"
						class="waves-effect waves-light btn modal-action  red modal-close">Cancelar</a>
				</div>
				<div class="col s4"></div>
			</div>


		</div>
	</div>

	<script
		src="<c:url value='/resources/js/businessCore/ProcesarRenuncia.js'></c:url>"
		type="text/javascript"></script>
</body>
</body>
</html>
