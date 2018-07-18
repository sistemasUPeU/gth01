<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">

<head>
<%@include file="../../../jspf/general.jspf"%>

<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/css/responsive.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />

<link href="<c:url value='/resources/css/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond"
	rel="stylesheet">
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal-default-theme.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link
	href="<c:url value='/resources/js/plugins/prism/prism.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/css/materialize-stepper.min.css'></c:url>"
	rel="stylesheet" type="text/css" />

<link href="<c:url value='/resources/js/plugins/sweetalert/sweetalert.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">	

<style>
div.dataTables_wrapper {
	width: auto;
	margin: 0 auto;
}

.dataTables_scroll {
	overflow: auto;
}

.display {
	width: 100%;
}

table.dataTable tbody th, table.dataTable tbody td {
	white-space: nowrap;
}

@media only screen and (min-width: 641px) {
	.remodal {
		max-width: 900px;
	}
}
textarea.materialize-textarea {
    min-height: 10rem;
}

</style>
</head>

<body class="">
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
		<div class="row">
			<div class="col s2">
				<br>
			</div>
			<div class="col s8">
				<section class="plans-container" id="plans">
					<div class="card " style="box-shadow: 0px 0px 20px 20px #888888">
						<div class="card-image #607d8b blue-grey waves-effect ">
							<div class="card-title">
								<h5>DETALLES DEL TRABAJADOR</h5>
							</div>

							<div class="price flow-text ">
								<div class="col m12 l12 s12">
									<div id="profile-page-content" class="row">
										<div class="row card-panel white">
											<div class="col l2 m2 s6">
												<img src="<c:url value="/resources/img/user.png"/>" alt=""
													class="circle responsive-img valign profile-image">
											</div>
											<div class="col l3 m6 s6">
												<input type="hidden" id="idt" /> <input id="idr"
													type="hidden" />
												<h6 class="light italic black-text">
													<strong>Nombres :</strong><span id="nombres">${listaDetalle.NOMBRES}</span><br>
												</h6>
												<h6 class="light italic black-text">
													<strong> Apellido Paterno : </strong><span id="paterno">${listaDetalle.PATERNO}</span><br>
												</h6>
												<h6 class="light italic black-text">
													<strong> Apellido Materno : </strong><span id="materno">${listaDetalle.MATERNO}</span><br>
												</h6>
												<h6 class="light italic black-text">
													<strong> Fecha de Nacimiento : </strong><span
														id="fecha_nac">${listaDetalle.FECHA_NAC}</span>
												</h6>
											</div>
										</div>
									</div>
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
												<span id="fecha_inicio">${listaDetalle.FECHA_CONTRATO}</span><br>
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
												<span id="direccion">${listaDetalle.DOMICILIO}</span><br>
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
												<span id="departamento">${listaDetalle.NOM_DEPA}</span><br>
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
												<span id="area">${listaDetalle.NOM_AREA}</span><br>
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
												<span id="seccion">${listaDetalle.NOM_SECCION}</span><br>
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
												<span id="puesto">${listaDetalle.NOM_PUESTO}</span><br>
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
												<span id="tipo_contrato">${listaDetalle.TIPO_CONTRATO}</span><br>
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
												<span id="ante_poli"> <c:choose>
														<c:when test="${listaDetalle.ANTECEDENTES==1}">
															No
														</c:when>
														<c:otherwise>
															Sí
														</c:otherwise>
													</c:choose>
												</span><br>
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
												<span id="certi_salud"> <c:choose>
														<c:when test="${listaDetalle.CERTI_SALUD==1}">
															No
														</c:when>
														<c:otherwise>
															Sí
														</c:otherwise>
													</c:choose>

												</span><br>
											</h6>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="card-content">
							<form class="col s12">
								<div class="row">
									<input type="hidden" value="${listaDetalle.ARCHIVO}"
										id="archivo" />
									<div class="input-field col s6" style="text-align: center">
										<h6>Evidencia de Abandono:</h6>
									</div>
									<div class="input-field col s6">
										<div class="material-placeholder">
											<!-- 									<img materialboxed class="materialboxed" -->
											<!-- 										data-caption="A picture of some deer and tons of trees" -->
											<!-- 										width="250" " style="z-index: 4" -->
											<%--
									 										src="<c:url value="/resources/img/carta de renuncia.png"/>" /> --%>
											<span id="carta"> <img materialboxed
												class="materialboxed"
												data-caption="A picture of some deer and tons of trees"
												width="250" " style="z-index: 4" id="carta" />
											</span>

										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
		<form action="col s12">
			<div class="row">
				<!-- 				<div class="input-field col s6 center"> -->
				<!-- 					<a -->
				<!-- 						class="btn btn-large waves-effect waves-light light-green darken-4 " -->
				<!-- 						 href="#modal3" >Notificar</a> -->
				<!-- 				</div> -->
				<div class="input-field col s6 center">
					<a class="waves-effect waves-light btn btn-large green darken-4 "
						data-remodal-target="modal" id="notificar">Notificar</a>
				</div>
				<div class="input-field col s6 center">
					<a
						class="waves-effect waves-light btn modal-trigger btn-large red darken-4 "
						href="#modal3">Justificar</a>
				</div>
			</div>
		</form>

		<br> <br> <br>
		<div id="modal3" class="modal modal-fixed-footer"
			style="width: 60%; height: 80%; border: 5px solid black">
			<div class="modal-content lead black-text ">
				<p>
				<h4>JUSTIFICAR ABANDONO</h4>
				</p>
				<hr>
				<form class="col s12">
					<div class="row">
						<div class="col s12">
							<div class="input-field col s7">
								<h5>Descripción de la justificacion:</h5>
								<div class="input-field col s12">
									<textarea id="observaciones" class="materialize-textarea"></textarea>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer   teal lighten-1   darken-2"
				style="z-index: 5; position: fixed">
				<div class="row">
					<div class="col s4">
						<a href="#"
							class="waves-effect waves-light btn modal-action  green modal-close "
							id="RechazarPrimeraCarta">Enviar</a>
					</div>
					<div class="col s4">
						<a href="#"
							class="waves-effect waves-light btn modal-action  red modal-close">Cancelar</a>
					</div>
					<div class="col s4"></div>
				</div>
			</div>
		</div>

		<div id="first">
			<div class="remodal" data-remodal-id="modal"
				style="min-height: 80%; width: 75%">
				<button data-remodal-action="close" class="remodal-close"></button>
				<div class="col s12">
					<h4>Envío de la primera carta notarial</h4>
				</div>
				<br> <label id="idtr" hidden=""></label> <label id="idr"
					hidden=""></label><label id="tipo" hidden=""></label>
				<form method="POST"
					action="<%=request.getContextPath()%>/renaban/SendLetter"
					enctype="multipart/form-data" id="CartaForm">
					<div class="section scrollspy" id="horizontal-stepper">
						<div class="row">
							<div class="section col s12 m12 l12">
								<div class="col s12">
									<div class="card">
										<div class="card-content">
											<ul class="stepper horizontal" id="horizontal" style="min-height:580px">
												<li class="step active">
													<div data-step-label="Mensaje"
														class="step-title waves-effect waves-dark" style="width: 11em">Paso 1</div>
													<div class="step-content">
														<div class="row">
															<div class="input-field col s12">
																<i class="mdi-communication-email prefix"></i>
																<input type="email" value="${listaDetalle.CORREO}" id="correo" name="correo" data-msg="Indique un correo válido" required/>
																<label for="correo" >Correo</label>
															</div>
															<div class="input-field col s12">
															<i class="mdi-action-spellcheck prefix"></i>
																<input type="text" id="asunto" name="asunto" class="validate"
																value="DESPIDO POR ABANDONO DE TRABAJO" data-msg="Es necesario colocar el asunto del mensaje" required/>
																<label for="mensaje"  >Asunto</label>
															</div>
															<div class="input-field col s12">
																<i class="mdi-editor-border-color prefix"></i>
																<textarea id="mensaje" class="materialize-textarea"
																	style="padding: 0.4rem 0; resize: none; max-height: 400px;" name="mensaje"
																	length=500 maxlength=500 data-msg="Redacte el mensaje" class="validate" required>Señor(a) ${listaDetalle.NOMBRES} ${listaDetalle.PATERNO} ${listaDetalle.MATERNO}, se le hace presente el siguiente documento debido a la ausencia en su puesto de trabajo por 4 días seguidos. El plazo máximo vigente es de 6 días. Por favor, justificar su inasistencia. Por favor, comunicarse con el siguiente número: XXXXXXXXX
														</textarea>
																<label for="mensaje" >Mensaje</label>
																<p id="mensaje2" style="margin-left:3em">
																	<br> <span>Atentamente Recursos Humanos - Universidad Peruana Unión,</span><br> <span>Gracias</span>
																</p>
															</div>
														</div>
														<div class="step-actions" style="position:fixed">
															<button class="waves-effect waves-dark btn  next-step" data-feedback=anyThing
															 id="nexto" class="btn">Siguiente</button>
														</div>
													</div>
												</li>
												<li class="step">
													<div data-step-label="Documento"
														class="step-title waves-effect waves-dark"
														style="width: 11em">Paso 2</div>
													<div class="step-content" style="padding: 0">
														<div class="row">
<!-- 														-----OJO CON ESTOS ID's------- -->
															<input type="hidden" id="idrenaban" name="idrenaban" value="${listaDetalle.ID_RENABAN}"/>
															<input type="hidden" id="idcontrato" name="idcontrato" value="${listaDetalle.ID_CONTRATO}"/>
															<input type="hidden" id="idtrabajador" name="idtrabajador" value="${listaDetalle.ID_TRABAJADOR}"/>
<!-- 														------------------------------ -->
															<div class="input-field col s6">
																<input type="file" name="file" class="dropify"
																	id="pelon1" data-height="362" class="validate" data-msg="Suba la carta notarial escaneada" required />
															</div>
															<div class="input-field col s6" style="padding-top: 3em">
																<span> <input type="date" id="fecha" name="fecha"
																	class="datepicker" class="validate" data-msg="Indique la fecha" required></span> <label for="fecha"
																	>Fecha de entrega de
																	la carta notarial</label>
															</div>

														</div>

														<div class="step-actions" style="position:fixed">
															<button
																class="waves-effect waves-dark btn green"
																data-position="bottom" data-delay="50"
																 data-tooltip="I am tooltip"
																type="submit" id="entredoc">ENVIAR</button>
															<button
																class="waves-effect waves-dark btn-flat previous-step">ATRAS</button>
															<!--                                            <button class="waves-effect waves-dark btn blue" type="submit">cancelar</button> -->

														</div>
													</div>
												</li>
												
<!-- 													<div class="preloader-wrapper" -->
<!-- 														style="display: none; margin-top: -14em; margin-left: 45%"> -->
<!-- 														<div class="spinner-layer spinner-blue-only"> -->
<!-- 															<div class="circle-clipper left"> -->
<!-- 																<div class="circle"></div> -->
<!-- 															</div> -->
<!-- 															<div class="gap-patch"> -->
<!-- 																<div class="circle"></div> -->
<!-- 															</div> -->
<!-- 															<div class="circle-clipper right"> -->
<!-- 																<div class="circle"></div> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->												
											</ul>
												<div>
														<div id="card-alert" class="card green"
															style="display: none; margin-top: -2em">
															<div class="card-content white-text">

																<p>
																	<i class="mdi-navigation-check"></i> Se ha enviado el
																	correo exitosamente.
																</p>
															</div>
														</div>
													</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>

	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/dataTables.responsive.min.js'></c:url>"
		type="text/javascript"></script>

	<%-- 		<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>

	<script
		src="<c:url  value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
		type="text/javascript"></script>
	<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/jquery-validation/jquery.validate.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/materialize-stepper.min.js'></c:url>"
		type="text/javascript"></script>
		<script
		src="<c:url value='/resources/js/plugins/sweetalert/sweetalert.min.js'></c:url>"
		type="text/javascript"></script>
	<script>
		$(document)
				.ready(
						function() {
							var drEvent = $('.dropify').dropify();
							$("#mensaje1").focus();
							$('.stepper').activateStepper({
								
								showFeedbackLoader: true
							});

							var fecha = new Date();
							var dd = fecha.getDate();
							var mm = fecha.getMonth(); //January is 0!

							var yyyy = fecha.getFullYear();
							if (dd < 10) {
								dd = '0' + dd;
							}
							if (mm < 10) {
								mm = '0' + mm;
							}
							var today = dd + '/' + mm + '/' + yyyy;
							$('#fecha').pickadate({
								selectMonths : true, // Creates a dropdown to control month
								selectYears : 15, // Creates a dropdown of 15 years to control
								format : 'dd/mm/yyyy',
								onStart : function() {
									this.set('select', today);
								}
							});

							var archivo = $("#archivo").val();
							console.log(archivo);
							var u = "";
							u += '<div class="container" style="width:80%"><img class="materialboxed responsive-img" '
							u += ''
							u += 'src="' + gth_context_path + '/resources/files/'+ archivo + '" '
							u += 'alt="sample"'
							u += 'data-caption="Esc para volver" ></div>'

							var c = "";
							c = "<embed src='"
									+ gth_context_path
									+ '/renaban/viewdoc?nombre='
									+ archivo
									+ "' style='width: 90%; height: 540px; ' type='application/pdf'>";

							var tipod = archivo.split(".")[1];
							if (tipod == "pdf") {
								$("#carta").html(c);
							} else {
								$("#carta").html(u);
							}
							$('.materialboxed').materialbox();
				
						});
// 		function someFunction() {
// 			setTimeout(function() {
// 				$('#feedbacker').nextStep();
// 			}, 1000);
// 		}

// 		function someOtherFunction() {
// 			return true;
// 		}

		function anyThing() {
			setTimeout(function() {
				$('.stepper').nextStep();
			}, 1000);
		}

// 		$(function() {
// 			$('.stepper').activateStepper({
// 				autoFocusInput: false
// 			});
// 		});
		
// 		$('.stepper').submitStepper();

		$("#CartaForm").on('submit',function(event) {
							//PREVENGAMOS LA ACCIÓN DEL SUBMIT EN EL CONTROLLER
							event.preventDefault();
							//CAPTUREMOS LOS DATOS DEL FORMULARIO
							var form = $('#CartaForm')[0];
							//Y ENCERREMOSLO EN UNA VARIABLE
						    var data = new FormData(form);
							//ACTIVA EL EFECTO DEL LOADING...
							$('.stepper').activateFeedback();
							
							$.ajax({
				                type: "POST",
				                enctype: 'multipart/form-data',
				                url: gth_context_path+"/renaban/SendLetter",
				                data: data,
				                processData: false,
				                contentType: false,
				                cache: false,
				                timeout: 600000,
				                success: function (data) {
				                	alert(data);
				                },
				                error: function (e) {
				                	alert("Ha ocurrido un problema, comuníquese con el administradord el sistema.");
				                }
				                });
							
							//DESPUÉS DE 2.5 SEGUNDOS SE EJECUTA EL MENSAJE
							setTimeout(function() {
								
								$('.stepper').nextStep();
								$("#card-alert")
								.fadeTo(
										3000,
										1,
										function() {
											// Animation complete.

											$(
													"#card-alert")
													.fadeTo(
															1000,
															0,
															function() {
																$(
																		this)
																		.hide();
																window.location.href = gth_context_path
																		+ '/renaban/PrimerEnvio';
															});

										});
								
							}, 2500);
// 							$('.stepper').nextStep();
								
							
							

						});
	</script>
	<!-- 		<script -->
	<%-- 		src="<c:url value='/resources/js/businessCore/Procesado.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->
</body>
</html>

