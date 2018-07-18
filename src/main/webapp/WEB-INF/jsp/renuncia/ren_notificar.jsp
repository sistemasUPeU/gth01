<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<link href="<c:url value='/resources/css/renuncias.css'></c:url>" />
<link href="https://fonts.googleapis.com/css?family=Poiret+One"
	rel="stylesheet">
<link href="<c:url value='/resources/css/remodal.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal-default-theme.css'/>"
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

<!-- //Nuevo link -->

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="<c:url value='/resources/css/materialize.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/prism.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/style.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/css/materialize-stepper.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link href="js/plugins/dropify/css/dropify.min.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<script src="<c:url  value='/resources/js/materialize.min.js'></c:url>"
	type="text/javascript"></script>
<link
	href="<c:url value='/resources/js/plugins/sweetalert/sweetalert.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<style>
@media only screen and (min-width: 700px) {
	.remodal {
		max-width: 65%;
	}
	h6 {
		font-size: 16px
	}
}

@media only screen and (max-width: 640px) {
	.remodal {
		min-width: 80%;
	}
	h5 {
		font-size: 14px
	}
	h1 {
		font-size: 16px
	}
	h6 {
		font-size: 14px
	}
}

.ajs-message.ajs-custom {
	color: #31708f;
	background-color: #d9edf7;
	border-color: #31708f;
	z-index: 999999
}

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

.btn {
	background: #3AD80C;
}
</style>
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

		<section id="content" class="col m12 l12 s12">
			<div class="center">
				<h1
					style="font-family: 'Cormorant Garamond', serif; font-weight: bold">Gestión
					de Renuncias y Abandonos</h1>
			</div>
			<div class="divider"></div>

		</section>
		<div class="row" style="width: 100%; max-width: 90%">
			<ul class="collapsible popout">
				<li id="autorize" class="active">
					<div class="collapsible-header active">
						<i class="mdi-social-notifications-on"></i> Notificar entrega de
						documentos de Beneficios Sociales
					</div>
					<div class="collapsible-body" style="display: none;">
						<div class="row" style="padding: 1em">
							<div class="contT"></div>
						</div>
					</div>
				</li>
				<li id="autorized" class="">
					<div class="collapsible-header">
						<i class="mdi-action-description"></i> Confirmar entrega de
						documentos - Almacenar al LEGAJO
					</div>
					<div class="card-panel collapsible-body " style="display: none;">
						<div class="row" style="padding: 1em">
							<div class="contP"></div>
						</div>

					</div>

				</li>
				<li id="delivery" class="">
					<div class="collapsible-header">
						<i class="mdi-toggle-check-box"></i> Renuncias o abandonos con
						documentos entregados
					</div>
					<div class="card-panel collapsible-body " style="display: none;">
						<div class="row" style="padding: 1em">
							<div class="contE"></div>
						</div>

					</div>

				</li>
			</ul>
		</div>

	</div>

	<div id="first">
		<div class="remodal" data-remodal-id="modal">
			<button data-remodal-action="close" class="remodal-close"></button>
			<h4 class="light italic black-text">Motivos de Rechazo</h4>
			<div class="col s12">
				<h4>Notificar entrega de documentos</h4>
			</div>
			<div class="col s12">
				<span>Correo:</span> <span id="correo"></span>
			</div>
			<br>
			<div class="col s12">
				<p id="men">
				<p id="mensaje1">
					Señor <span id="nombre"></span> sus documentos ya se encuentran
					listos para ser entregados, por favor se le invita a pasar por la
					oficina de Gestion de Talentos Humanos (GTH) a recoger sus
					documentos de beneficios sociales en la fecha
				</p>
				<span> <input type="date" id="fecha" style="width: 20%"
					class="datepicker"></span>

				<p id="mensaje2">
					<br> <span>Atentamente GTH,</span><br> <span>Gracias</span>
				</p>
			</div>
			<label id="idtr" hidden=""></label> <label id="idr" hidden=""></label><label
				id="tipo" hidden=""></label>
			<button data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
			<button data-remodal-action="confirm" class="remodal-confirm" id=""
				onclick="enviarCorreo()">Enviar</button>
		</div>

	</div>

	<div id="first">
		<div class="remodal" data-remodal-id="modal1">
			<button data-remodal-action="close" class="remodal-close"></button>
			<h4 style="font-family: 'Dosis', sans-serif;">Entregar
				Documentos</h4>

			<form method="POST"
				action="<%=request.getContextPath()%>/renaban/holamundo"
				enctype="multipart/form-data" id="EntregaForm">
				<div class="container black-text text-darken-2">
					<div class="row">
						<div class="section col s12 m12 l12">
							<div class="section scrollspy" id="horizontal-stepper">
								<div class="row">
									<div class="col s12">
										<div class="card">
											<div class="card-content">
												<ul class="stepper horizontal" id="horizontal">
													<li class="step active">
														<div data-step-label="Liquidacion"
															class="step-title waves-effect waves-dark">Doc 1</div>
														<div class="step-content">
															<div class="row">
																<div class="input-field col s12">
																	<div class="container">
																		<p class="center-align">Hoja de liquidación</p>
																		<div id="file-upload" class="section">
																			<div class="row section">
																				<div class="col s9">
																					<input type="file" name="liquidacion"
																						id="input-file-now" id="liquidacion"
																						class="dropify" data-default-file=""
																						data-height="153" />
																					<!--                                                     <input type="file" name="liquidacion" id="input-file-now" class="dropify" data-default-file="" data-height="200" data-witdh="200"/> -->
																				</div>
																				<!--                                                     <div class="col s3"> -->
																				<!--                                                     <br> -->
																				<!--                                                     </div> -->
																				<div class="col s3">
																					<input type="date" class="datepicker" name="fecha1"
																						id="fecha1" />
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="step-actions">
																<button class="waves-effect waves-dark btn  next-step"
																	class="btn">Siguiente</button>
															</div>
														</div>
													</li>
													<li class="step">
														<div data-step-label="Carta CTS"
															class="step-title waves-effect waves-dark">Doc 2</div>
														<div class="step-content">
															<div class="row">
																<div class="input-field col s12">
																	<div class="container">
																		<p>Carta CTS</p>
																		<div id="file-upload" class="section">
																			<div class="row section">
																				<div class="col s9">
																					<input type="file" name="cts" id="input-file-now"
																						id="cts" class="dropify" data-default-file=""
																						data-height="180" data-witdh="150" />
																					<!--                                                      <input type="file" id="input-file-now" class="dropify" data-default-file="" />  -->
																				</div>
																				<div class="col s3">
																					<input type="date" class="datepicker" name="fecha2"
																						id="fecha2">
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="step-actions">
																<button class="waves-effect waves-dark btn  next-step"
																	class="btn">SIGUIENTE</button>
																<button
																	class="waves-effect waves-dark btn-flat previous-step">ATRAS</button>
															</div>
														</div>
													</li>
													<li class="step">
														<div data-step-label="Certificado"
															class="step-title waves-effect waves-dark">Doc 3</div>
														<div class="step-content">
															<div class="row">
																<div class="input-field col s12">
																	<div class="container">
																		<p>Certificado de Trabajo</p>
																		<div id="file-upload" class="section">
																			<div class="row section">
																				<div class="col s9">
																					<input type="file" name="certificado"
																						id="input-file-now" id="certificado"
																						class="dropify" data-default-file=""
																						data-height="180" data-witdh="150" />
																					<!--                                                     <input type="file" id="input-file-now" class="dropify" data-default-file="" /> -->
																				</div>
																				<div class="col s3">
																					<input type="date" class="datepicker" name="fecha3"
																						id="fecha3">
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="step-actions">
																<button class="waves-effect waves-dark btn  next-step"
																	class="btn">SIGUIENTE</button>
																<button
																	class="waves-effect waves-dark btn-flat previous-step">ATRAS</button>
															</div>
														</div>
													</li>
													<li class="step">
														<div data-step-label="Remuneraciones"
															class="step-title waves-effect waves-dark"
															style="width: 11em">Doc 4</div>
														<div class="step-content">
															<div class="row">
																<div class="input-field col s12">
																	<div class="container">
																		<p>Reporte de Remuneraciones</p>
																		<div id="file-upload" class="section">
																			<div class="row section">
																				<div class="col s9">
																					<input type="file" name="remu" id="input-file-now"
																						id="remu" class="dropify" data-default-file=""
																						data-height="180" data-witdh="150" />
																					<!--                                                     <input type="file" id="input-file-now" class="dropify" data-default-file="" /> -->
																				</div>
																				<div class="col s3">
																					<input type="date" class="datepicker" name="fecha4"
																						id="fecha4">
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<input id="idc" type="hidden" name="idc"> <input
																type="hidden" id="idt" name="idt" /> <input
																type="hidden" id="idra" name="idra" /> <input
																type="hidden" id="tipon" name="tipon" />
															<div class="step-actions">
																<button
																	class="waves-effect waves-dark btn green next-step"
																	data-position="bottom" data-delay="50"
																	data-tooltip="I am tooltip" type="submit" id="entredoc"">ENVIAR</button>
																<button
																	class="waves-effect waves-dark btn-flat previous-step">ATRAS</button>
																<!--                                            <button class="waves-effect waves-dark btn blue" type="submit">cancelar</button> -->

															</div>
														</div>
													</li>
												</ul>
												<div>
													<div id="card-alert" class="card green"
														style="display: none;">
														<div class="card-content black-text">
															<p>
																<i class="mdi-naviga20ion-check"></i> Se entregaron los
																documentos exitosamente.
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
						<div class="col hide-on-small-only m3 l2">
							<div class="toc-wrapper" style="top: 0px;"></div>
						</div>

					</div>
				</div>
				<button class="btn btn-large waves-effect waves-light red darken-4"
					data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
				<!-- 				<button data-remodal-action="confirm" class="remodal-confirm" -->
				<!-- 					type="submit" id="entredoc">Enviar</button> -->
				<div class="col s4"></div>
			</form>
		</div>
	</div>

	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>

	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/dataTables.responsive.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
		type="text/javascript"></script>
	<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
		type="text/javascript">
		
	</script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/businessCore/NotificarRenuncia.js'></c:url>"
		type="text/javascript"></script>

	<!-- 	link nuevos  -->

	<script
		src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
	<script
		src="<c:url  value='/resources/js/materialize-stepper.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/css/dropify.min.css'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/sweetalert/sweetalert.min.js'></c:url>"
		type="text/javascript"></script>

</body>
</html>
